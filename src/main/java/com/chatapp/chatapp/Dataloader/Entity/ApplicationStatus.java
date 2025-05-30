package com.chatapp.chatapp.Dataloader.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_application_status", schema = "chatapp")
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false, unique = true)
    Integer applicationId;

    @Column(name = "name", length = 50)
    String name;

    @Column(name = "description" , length = 80)
    String description;

    @Column(name = "status" , length = 20)
    String status;

    @Column(name = "type" , length = 20)
    String type;

    @CreationTimestamp
    @Column(name = "created_date")
    LocalDateTime  created;

    @UpdateTimestamp
    @Column(name = "updated_date")
    LocalDateTime updated;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer id) {
        this.applicationId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
