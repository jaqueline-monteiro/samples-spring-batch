---------------------------------CREATE---------------------------------
CREATE SCHEMA IF NOT EXISTS TESTE;

CREATE SEQUENCE IF NOT EXISTS TESTE.BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE IF NOT EXISTS TESTE.BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE IF NOT EXISTS TESTE.BATCH_JOB_SEQ;

--BATCH_JOB_INSTANCE
CREATE TABLE IF NOT EXISTS TESTE.BATCH_JOB_INSTANCE (
	JOB_INSTANCE_ID BIGINT PRIMARY KEY,
	VERSION BIGINT,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(32) NOT NULL
);

--BATCH_JOB_EXECUTION
CREATE TABLE IF NOT EXISTS TESTE.BATCH_JOB_EXECUTION (
	JOB_EXECUTION_ID BIGINT PRIMARY KEY,
	VERSION BIGINT,
	JOB_INSTANCE_ID BIGINT NOT NULL,
	CREATE_TIME TIMESTAMP NOT NULL,
	START_TIME TIMESTAMP DEFAULT NULL,
	END_TIME TIMESTAMP DEFAULT NULL,
	STATUS VARCHAR(10),
	EXIT_CODE VARCHAR(20),
	EXIT_MESSAGE VARCHAR(2500),
	LAST_UPDATED TIMESTAMP,
	JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
	constraint JOB_INSTANCE_EXECUTION_FK foreign key (JOB_INSTANCE_ID)
	references TESTE.BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
);

--BATCH_JOB_EXECUTION_CONTEXT
CREATE TABLE IF NOT EXISTS TESTE.BATCH_JOB_EXECUTION_CONTEXT  (
	JOB_EXECUTION_ID BIGINT PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT CLOB,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references TESTE.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)

); 

--BATCH_JOB_EXECUTION_PARAMS
CREATE TABLE IF NOT EXISTS TESTE.BATCH_JOB_EXECUTION_PARAMS  (
	JOB_EXECUTION_ID BIGINT NOT NULL,
	TYPE_CD VARCHAR(6) NOT NULL,
	KEY_NAME VARCHAR(100) NOT NULL,
	STRING_VAL VARCHAR(250),
	DATE_VAL DATETIME DEFAULT NULL,
	LONG_VAL BIGINT,
	DOUBLE_VAL DOUBLE PRECISION,
	IDENTIFYING CHAR(1) NOT NULL,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references TESTE.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
); 

--BATCH_STEP_EXECUTION
CREATE TABLE IF NOT EXISTS TESTE.BATCH_STEP_EXECUTION  (
	STEP_EXECUTION_ID BIGINT  PRIMARY KEY ,
	VERSION BIGINT NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID BIGINT NOT NULL,
	START_TIME TIMESTAMP NOT NULL,
	END_TIME TIMESTAMP DEFAULT NULL,
	STATUS VARCHAR(10),
	COMMIT_COUNT BIGINT,
	READ_COUNT BIGINT,
	FILTER_COUNT BIGINT,
	WRITE_COUNT BIGINT,
	READ_SKIP_COUNT BIGINT,
	WRITE_SKIP_COUNT BIGINT,
	PROCESS_SKIP_COUNT BIGINT,
	ROLLBACK_COUNT BIGINT,
	EXIT_CODE VARCHAR(20),
	EXIT_MESSAGE VARCHAR(2500),
	LAST_UPDATED TIMESTAMP,
	constraint JOB_EXECUTION_STEP_FK foreign key (JOB_EXECUTION_ID)
	references TESTE.BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

--BATCH_STEP_EXECUTION_CONTEXT
CREATE TABLE IF NOT EXISTS TESTE.BATCH_STEP_EXECUTION_CONTEXT  (
   STEP_EXECUTION_ID BIGINT PRIMARY KEY,
   SHORT_CONTEXT VARCHAR(2500) NOT NULL,
   SERIALIZED_CONTEXT CLOB,
   constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
   references TESTE.BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;