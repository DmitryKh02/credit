package ru.neoflex.dossier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.dto.EmailMessage;
import ru.neoflex.dossier.service.MessageSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {
    @Value(value = "${spring.mail.username}")
    private String username;
    private final JavaMailSender mailSender;

    @Override
    public void sendMessage(EmailMessage emailMessage) {
        log.debug("KafkaMessageServiceImpl.sendMessage - EmailMessage {}", emailMessage);
        String text = emailMessage.getTheme().getMessageInfo();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailMessage.getAddress());
        mailMessage.setSubject(emailMessage.getTheme().getThemeName());
        mailMessage.setText(text);

        try {
            mailSender.send(mailMessage);
        }
        catch (MailException ex){
            log.error(ex.getLocalizedMessage());
        }


        log.debug("KafkaMessageServiceImpl.sendMessage - Message {}", mailMessage);
    }
}
