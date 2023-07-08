-----------------------------------------------------------------------------------------------------------------------
 /*  배치프레임워크 관련 테이블 */
-----------------------------------------------------------------------------------------------------------------------
drop table BATCH_STEP_EXECUTION_SEQ ;
drop table BATCH_JOB_EXECUTION_SEQ ;
drop table BATCH_JOB_SEQ ;
drop table batch_job_execution_context  ;
drop table batch_job_execution_params   ;
drop table batch_step_execution_context ;
drop table batch_step_execution         ;
drop table batch_job_execution          ;
drop table batch_job_instance           ;

CREATE TABLE BATCH_JOB_INSTANCE  (
	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT ,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT  ,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME(6) NOT NULL,
	START_TIME DATETIME(6) DEFAULT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	LAST_UPDATED DATETIME(6),
	JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL ,
	TYPE_CD VARCHAR(6) NOT NULL ,
	KEY_NAME VARCHAR(100) NOT NULL ,
	STRING_VAL VARCHAR(250) ,
	DATE_VAL DATETIME(6) DEFAULT NULL ,
	LONG_VAL BIGINT ,
	DOUBLE_VAL DOUBLE PRECISION ,
	IDENTIFYING CHAR(1) NOT NULL ,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	START_TIME DATETIME(6) NOT NULL ,
	END_TIME DATETIME(6) DEFAULT NULL ,
	STATUS VARCHAR(10) ,
	EXIT_CODE VARCHAR(2500) ,
	EXIT_MESSAGE VARCHAR(2500) ,
	COMMIT_COUNT BIGINT ,
	READ_COUNT BIGINT ,
	FILTER_COUNT BIGINT ,
	WRITE_COUNT BIGINT ,
	READ_SKIP_COUNT BIGINT ,
	WRITE_SKIP_COUNT BIGINT ,
	PROCESS_SKIP_COUNT BIGINT ,
	ROLLBACK_COUNT BIGINT ,
	LAST_UPDATED DATETIME(6),
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT TEXT ,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;



CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
	ID BIGINT NOT NULL,
	UNIQUE_KEY CHAR(1) NOT NULL,
	constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);
===================================================================================
===================================================================================
===================================================================================
;




===================================================================================
drop table member;
create table member (
    username int auto_increment primary key
  , password varchar(50)
  , created_dt timestamp default current_timestamp
);
-----------------------------------------------------------------------------------
PROCEDURE : insertMember
    PARAM : 입력하고자 하는 건수 건수 = PARAM_PASSWORD

CALL insertMember(10000);  -- 1만건 입력 시
-----------------------------------------------------------------------------------

CREATE DEFINER=`root`@`localhost` PROCEDURE `springbatch`.`insertMember`(
     PARAM_PASSWORD INTEGER
)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= PARAM_PASSWORD DO
        INSERT INTO member (password) VALUES (SUBSTR(MD5(RAND()),1,20));
        SET i = i + 1;
    END WHILE;
end
===================================================================================


===================================================================================
drop TABLE `Dept`;
CREATE TABLE `Dept` (
  `dept_no`    bigint       NOT NULL AUTO_INCREMENT,
  `d_name`     varchar(50)  NOT NULL,
  `address`    varchar(100) NOT NULL,
  `created_by` varchar(50)  NOT NULL,
  `created_date` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by`   varchar(50) ,
  `last_modified_date` timestamp  DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (`dept_no`),
  KEY `Dept_x01` (`dept_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2024281 DEFAULT CHARSET=utf8;

select count(*) from dept;
select * from dept;

-----------------------------------------------------------------------------------
PROCEDURE : insertDept
    PARAM : 입력하고자 하는 건수 건수 = PARAM_PASSWORD

CALL insertDept(10000);  -- 1만건 입력 시
-----------------------------------------------------------------------------------
CREATE DEFINER=`root`@`localhost` PROCEDURE `springbatch`.`insertDept`(
     PARAM_PASSWORD INTEGER
)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= PARAM_PASSWORD DO
        INSERT INTO Dept (d_name, address, created_by, last_modified_by) VALUES (SUBSTR(MD5(RAND()),1,20), SUBSTR(MD5(RAND()),1,30), 'ADMIN', 'ADMIN');
        SET i = i + 1;
    END WHILE;
end
===================================================================================


===================================================================================
drop table Customer;
create table Customer (
    id int auto_increment primary key
  , firstname varchar(255)
  , lastname varchar(255)
  , birthdate timestamp default current_timestamp
);
-----------------------------------------------------------------------------------
PROCEDURE : insertCustomer
    PARAM : 입력하고자 하는 건수 건수 = PARAM_CNT

CALL insertCustomer(10000);  -- 1만건 입력 시
-----------------------------------------------------------------------------------
CREATE DEFINER=`root`@`localhost` PROCEDURE `springbatch`.`insertCustomer`(
     PARAM_CNT INTEGER
)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= PARAM_CNT DO
        INSERT INTO Customer (firstname, lastname) VALUES
            ( SUBSTR(MD5(RAND()),1,30)
            , SUBSTR(MD5(RAND()),1,30)
            );
        SET i = i + 1;
    END WHILE;
end