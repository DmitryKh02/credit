package ru.neoflex.dossier.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.dto.EmailMessage;
import ru.neoflex.dossier.exception.EmailMessageConverterException;
import ru.neoflex.dossier.serializer.EmailMessageConverter;
import ru.neoflex.dossier.service.DossierService;
import ru.neoflex.dossier.service.MessageSender;


@Slf4j
@Service
@RequiredArgsConstructor
public class DossierServiceImpl implements DossierService {
    private final EmailMessageConverter converter;
    private final MessageSender messageSender;

    @Override
    @KafkaListener(topics = {"${application.kafka.topic.finish-registration}"})
    public void finishRegistrationByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.finishRegistrationByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.finishRegistrationByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.finishRegistrationByApplicationId - {}", e.getError());
        }
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topic.create-documents}"})
    public void createDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.createDocumentByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.createDocumentByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.createDocumentByApplicationId - {}", e.getError());
        }
    }


    @Override
    @KafkaListener(topics = {"${application.kafka.topic.send-documents}"})
    public void sendDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.sendDocumentByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.sendDocumentByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.sendDocumentByApplicationId - {}", e.getError());
        }
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topic.send-ses}"})
    public void codeDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.codeDocumentByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.codeDocumentByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.codeDocumentByApplicationId - {}", e.getError());
        }
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topic.credit-issued}"})
    public void signDocumentByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.signDocumentByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.createDocumentByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.createDocumentByApplicationId - {}", e.getError());
        }
    }

    @Override
    @KafkaListener(topics = {"${application.kafka.topic.application-denied}"})
    public void applicationDeniedByApplicationId(String emailMessage) {
        log.info("DossierServiceImpl.applicationDeniedByApplicationId - consumed {}", emailMessage);

        try {
            EmailMessage message = converter.convert(emailMessage);
            messageSender.sendMessage(message);

            log.info("DossierServiceImpl.applicationDeniedByApplicationId - EmailMessage - {}", message);
        } catch (EmailMessageConverterException e) {
            log.error("DossierServiceImpl.applicationDeniedByApplicationId - {}", e.getError());
        }
    }



}
