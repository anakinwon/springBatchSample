package com.pisien.batchSample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Dept extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptNo;
    private String dName;
    private String address;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;                // 생성자ID
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;       // 생성일시

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;           // 수정자ID
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;  // 수정일시

}
