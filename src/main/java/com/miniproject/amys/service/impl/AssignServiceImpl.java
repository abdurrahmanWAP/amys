package com.miniproject.amys.service.impl;

import com.miniproject.amys.config.RabbitMQConfig;
import com.miniproject.amys.constant.Message;
import com.miniproject.amys.dto.CreateResDto;
import com.miniproject.amys.dto.UpdateResDto;
import com.miniproject.amys.dto.assign.AssignResponseDto;
import com.miniproject.amys.dto.assign.CreateAssignRequestDto;
import com.miniproject.amys.exception.DataIntegrityException;
import com.miniproject.amys.exception.NotFoundException;
import com.miniproject.amys.model.*;
import com.miniproject.amys.pojo.MailPojo;
import com.miniproject.amys.repository.*;
import com.miniproject.amys.service.AssignService;
import com.miniproject.amys.service.BaseModelServicer;
import com.miniproject.amys.util.EmailUtil;
import com.miniproject.amys.util.GenerateAlpaNum;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Profile("jpa")
@Service
public class AssignServiceImpl extends BaseModelServicer implements AssignService {
    private final AssignRepo assignRepo;
    private final AssignDetailRepo assignDetailRepo;
    private final AssetRepo assetRepo;
    private final LocationRepo locationRepo;
    private final EmployeeRepo employeeRepo;
    private final RabbitTemplate rabbitTemplate;
    private final JavaMailSender mailSender;
    private final EmailUtil emailUtil;

    public AssignServiceImpl(
            AssignRepo assignRepo,
            AssignDetailRepo assignDetailRepo,
            AssetRepo assetRepo,
            LocationRepo locationRepo,
            EmployeeRepo employeeRepo,
            RabbitTemplate rabbitTemplate,
            JavaMailSender mailSender,
            EmailUtil emailUtil) {
        this.assignRepo = assignRepo;
        this.assignDetailRepo = assignDetailRepo;
        this.assetRepo = assetRepo;
        this.locationRepo = locationRepo;
        this.employeeRepo = employeeRepo;
        this.rabbitTemplate=rabbitTemplate;
        this.mailSender= mailSender;
        this.emailUtil=emailUtil;
    }

    @Transactional
    @Override
    public List<AssignResponseDto> getAll() {
        return assignRepo.findAll().stream()
                .map(assign -> responDto(assign))
                .toList();
    }

    @Transactional
    @Override
    public AssignResponseDto getById(String id) {
        Assign assign = assignRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Assign Found"));
        return responDto(assign);
    }

    @Transactional
    @Override
    public UpdateResDto returnAsset(String id, List<String> assignDetailIdList) {
        Assign assign = assignRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No Assign Found"));

        updated(assign);

        LocalDateTime now = LocalDateTime.now();
        for (String assignDetailId : assignDetailIdList) {
            AssignDetail assignDetail = assignDetailRepo.findById(UUID.fromString(assignDetailId))
                    .orElseThrow(() -> new NotFoundException("No AssignDetail Found"));

            assignDetail.setReturnDate(now);
            updated(assignDetail);
            AssignDetail assignDetail1 = assignDetailRepo.saveAndFlush(assignDetail);
        }

        Assign assign1 = assignRepo.saveAndFlush(assign);

        MailPojo data = new MailPojo("timpehkkn@gmail.com",assign1.getAssignCode());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EX_CI,
                RabbitMQConfig.EMAIL_KEY_CI,
                data
        );

        return new UpdateResDto(assign1.getVersion(), Message.UPDATE.getMessage());
    }

    @Transactional
    @Override
    public CreateResDto insert(CreateAssignRequestDto requestDto) {

        String assignCode = GenerateAlpaNum.randomGenerate();
        if(assignRepo.findByAssignCode(assignCode).isPresent()){
            throw new DataIntegrityException("Assign Code Already exist");
        }
        Assign assignInsert = new Assign();

        assignInsert.setAssignCode(assignCode);
        assignInsert.setAssignDate(LocalDateTime.now());
        setTarget(requestDto, assignInsert);

        createAssignDetail(requestDto, assignInsert);

        createdAndId(assignInsert);
        Assign assign = assignRepo.save(assignInsert);

        MailPojo data = new MailPojo("timpehkkn@gmail.com",assign.getAssignCode());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EX,
                RabbitMQConfig.EMAIL_KEY,
                data
        );

        return new CreateResDto(assign.getId(), Message.CREATE.getMessage());
    }


    private void createAssignDetail(CreateAssignRequestDto requestDto, Assign assign) {
        for (String id : requestDto.getAssetIdList()) {
            Asset asset = assetRepo.findById(UUID.fromString(id)).orElseThrow(
                    () -> new NotFoundException("No Asset Found")
            );

            AssignDetail assignDetail = new AssignDetail();


            assignDetail.setAssign(assign);
            assignDetail.setAsset(asset);

            createdAndId(assignDetail);
            AssignDetail assignDetail1 = assignDetailRepo.save(assignDetail);
        }
    }

    private AssignResponseDto responDto(Assign assign) {
        return new AssignResponseDto(
                assign.getId().toString(),
                assign.getAssignDate().toString(),
                getTargetName(assign));
    }

    private String getTargetName(Assign assign) {
        if (assign.getAsset() != null) {
            return assign.getAsset().getAssetName();
        }
        if (assign.getEmployee() != null) {
            return assign.getEmployee().getEmployeeName();
        }
        if (assign.getLocation() != null) {
            return assign.getLocation().getLocationName();
        }
        return "";
    }

    private void setTarget(CreateAssignRequestDto requestDto, Assign assign) {
        if (requestDto.getAssetTargetId() == null && requestDto.getLocationTargetId() == null) {
            Employee employee = employeeRepo.findById(UUID.fromString(requestDto.getEmployeeTargetId()))
                    .orElseThrow(() -> new NotFoundException("No Employee Found"));

            assign.setEmployee(employee);
        }
        if (requestDto.getAssetTargetId() == null && requestDto.getEmployeeTargetId() == null) {
            Location location = locationRepo.findById(UUID.fromString(requestDto.getLocationTargetId()))
                    .orElseThrow(() -> new NotFoundException("No Location Found"));
            assign.setLocation(location);
        }
        if (requestDto.getEmployeeTargetId() == null && requestDto.getLocationTargetId() == null) {
            Asset asset = assetRepo.findById(UUID.fromString(requestDto.getAssetTargetId()))
                    .orElseThrow(() -> new NotFoundException("No Asset Found"));
            assign.setAsset(asset);
        }

    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receiveEmailNotification(MailPojo data){
        emailUtil.sendEmail(
                mailSender,
                data.getEmail(),
                "CheckOut Baraaang",
                "Barang Code '" +data.getAssignCode()+"' telah di Assign"
                );
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE_CI)
    public void receiveEmailNotificationCi(MailPojo data){
        emailUtil.sendEmail(
                mailSender,
                data.getEmail(),
                "CheckIn Baraaang",
                "Barang Code '" +data.getAssignCode()+"' telah di Return"
        );
    }
}
