package com.academo.util.notification;

import com.academo.controller.dtos.activity.ActivityNotificationDTO;
import com.academo.controller.dtos.notification.NotificationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SendNotifications {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmails(List<NotificationDTO> notificationDTOList) throws MessagingException {
        for (NotificationDTO n : notificationDTOList){

            n.setActivityNotificationDTOS(
                    n.getActivityNotificationDTOS()
                            .stream()
                            .sorted(Comparator.comparing(ActivityNotificationDTO::activityDate))
                            .toList());

            Context context = new Context();
            context.setVariable("atividades", n.getActivityNotificationDTOS());

            String htmlContent = templateEngine.process("NotificationTemplate", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setSubject("ACADEMO - Atividades Próximas");
            helper.setFrom("Equipe ACADEMO <" + System.getenv("MAIL_USERNAME").trim() + ">");
            helper.setTo(n.getEmail());
            helper.setText(htmlContent, true);

            mailSender.send(message);
        }
    }


}
