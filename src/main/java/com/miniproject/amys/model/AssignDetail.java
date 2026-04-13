package com.miniproject.amys.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_assign_detail",
        uniqueConstraints = @UniqueConstraint(columnNames = {"assign_id", "asset_id"}))
public class AssignDetail extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "assign_id")
    private Assign assign;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column()
    private LocalDateTime returnDate;

    public Assign getAssign() {
        return assign;
    }

    public void setAssign(Assign assign) {
        this.assign = assign;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
