package com.academo.util.QuartzSchedule.notification;

import com.academo.repository.ActivityRepository;
import com.academo.util.notification.SendNotifications;
import jakarta.mail.MessagingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationJob implements Job {

    @Autowired
    SendNotifications sendNotifications;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            sendNotifications.sendEmails(activityRepository.searchNotificationByDate(LocalDate.now()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
