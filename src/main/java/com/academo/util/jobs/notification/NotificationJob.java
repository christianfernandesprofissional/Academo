package com.academo.util.jobs.notification;

import com.academo.repository.ActivityRepository;
import com.academo.util.notification.SendNotifications;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationJob extends QuartzJobBean {

    @Autowired
    SendNotifications sendNotifications;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        sendNotifications.sendEmails(activityRepository.searchNotificationByDate(LocalDate.now()));
    }

}
