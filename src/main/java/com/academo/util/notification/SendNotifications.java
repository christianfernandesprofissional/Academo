package com.academo.util.notification;

import com.academo.controller.dtos.activity.ActivityNotificationDTO;
import com.academo.controller.dtos.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendNotifications {

    @Autowired
    private JavaMailSender mailSender;

    private List<NotificationDTO> notificationDTOList;

    public SendNotifications() {
    }

    public SendNotifications(List<NotificationDTO> notificationDTOList) {
        this.notificationDTOList = notificationDTOList;
        sendEmails();
    }

    private void sendEmails(){
        // Para cada notificação
        for (NotificationDTO n : this.notificationDTOList){

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(n.getEmail());
            email.setSubject("ACADEMO - Atividades Próximas");

            email.setText("Olá, segue atividades próximas:\n\n");
            email.setText(email.getText() + "Atividade\tDescrição\tMatéria\tData");
            // Constroe tabela de atividades pendentes
            for (ActivityNotificationDTO a : n.getActivityNotificationDTOS()){
                email.setText(email.getText() + String.format("%s\t%S\t%s\t%s\n", a.name(), a.description(), a.subject(), a.activityDate()));
            }
            email.setFrom("Equipe ACADEMO <" + System.getenv("MAIL_USERNAME").trim() + ">");

            mailSender.send(email);
        }

    }
}
