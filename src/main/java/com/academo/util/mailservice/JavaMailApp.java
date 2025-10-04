package com.academo.util.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailApp {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailBoasVindas(String destinatario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject("Bem-vindo ao ACADEMO!");
        email.setText("""
                Olá,

                Sua conta foi ativada com sucesso!
                Agora você já pode acessar o sistema normalmente.

                """);
        email.setFrom("Equipe ACADEMO <" + System.getenv("MAIL_USERNAME").trim() + ">");

        mailSender.send(email);
    }

    public void enviarEmailDeAtivacao(String destinatario, String token){
        String urlDeAtivacao = "http://localhost:8080/auth/activate?value="+token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject("Ative sua conta!");
        email.setText("""
                Olá,

                Ative sua conta para poder utilizar o Academo.
                Acesse o link abaixo para ativação:
                
                """ + urlDeAtivacao + " \n");

        email.setFrom("Equipe ACADEMO <" + System.getenv("MAIL_USERNAME").trim() + ">");
        mailSender.send(email);
    }
}
