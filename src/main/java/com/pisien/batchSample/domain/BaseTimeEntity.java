package com.pisien.batchSample.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *  <Auditing>
 * 엔티티를 생성, 변경할 때 변경한 사람과 시간을 추적하고 싶으면?
 *    - 등록일
 *    - 등록자
 *    - 수정일
 *    - 수정자
 *
 * */

/**
 *  <스프링 데이터 JPA 사용 방법>
 * */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // 스프링 데이터 JPA 사용 어노테이션 // 해당 어노테이션을 일일이 엔티티에 입력하기 귀찮을 때는 "META-INF/orm.xml" 등록하면 된다.
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)               // 등록 후에 변경되지 못하도록 처리
    private LocalDateTime createdDate;       // 등록일시
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;       // 수정일시

//    @CreatedBy
//    @Column(updatable = false)               // 등록 후에 변경되지 못하도록 처리
//    private String createdBy ;               // 등록자ID
//    @LastModifiedBy
//    private String lastModifiedBy ;  // 수정자ID
//    //private String lastModifiedBy ="UP_ADMIN";  // 수정자ID

}
