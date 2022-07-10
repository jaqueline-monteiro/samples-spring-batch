package br.com.jaqueline.sample.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class JobListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // no need to implement
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LocalDateTime startTime = LocalDateTime.ofInstant(jobExecution.getStartTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(jobExecution.getEndTime().toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(startTime, endTime);
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            this.log.info("Job process START: " + startTime
                    + " | " + "Job process END: " + endTime
                    + " | " + "Elapsed time: " + "Horas: " + duration.toHours() + "Minutos: " + duration.toMinutes() + "Segundos: " + duration.toSeconds());
        }
    }

}
