package com.pisien.batchSample.job.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity @Getter @Setter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int username;
    private String password;
    private String createdDt;

    public Member(int username, String password, String createdDt) {
        this.username = username;
        this.password = password;
        this.createdDt = createdDt;
    }
}
