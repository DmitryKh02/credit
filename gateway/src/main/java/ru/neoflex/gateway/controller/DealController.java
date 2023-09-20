package ru.neoflex.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.gateway.dto.request.FinishRegistrationRequestDTO;
import ru.neoflex.gateway.enums.Theme;
import ru.neoflex.gateway.service.GatewayService;


@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name = "Контроллер переадресации на микросервис deal")
public class DealController {
    private final GatewayService gatewayService;
    @PostMapping(value = "/application/registration/{applicationId}")
    @Operation(summary = "Завершение регистрации + полный подсчёт кредита", description = "Создание сущности кредита и занесение в базу данных")
    public void finishRegistration(@RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable Long applicationId) {
        log.trace("/deal/offer Request: FinishRegistrationRequestDTO {}, applicationId {}", finishRegistrationRequestDTO, applicationId);

        gatewayService.finishRegistration(finishRegistrationRequestDTO,applicationId);
    }

    @PostMapping(value = "/document/{applicationId}")
    @Operation(summary = "Запрос на отправку документов", description = "Отправляется запрос на dossier и выполняется отправка письма на почту клиенту")
    public void sendDocumentByApplicationId(@PathVariable Long applicationId) {
        log.trace("/document/{applicationId}/send Request: ApplicationId  {} ", applicationId);

        gatewayService.send(Theme.SEND_DOCUMENTS, applicationId);
    }

    @PostMapping(value = "/document/{applicationId}/sign")
    @Operation(summary = "Запрос на подписание документов", description = "Отправляется запрос на dossier и выполняется отправка письма на почту клиенту")
    public void signDocumentByApplicationId(@PathVariable Long applicationId) {
        log.trace("/document/{applicationId}/sign Request: ApplicationId  {} ", applicationId);

        gatewayService.send(Theme.SEND_SES, applicationId);
    }
    @PostMapping(value = "/document/{applicationId}/sign/code")
    @Operation(summary = "Подписание документов", description = "Отправляется запрос на dossier и выполняется отправка письма на почту клиенту")
    public void codeDocumentByApplicationId(@PathVariable Long applicationId) {
        log.trace("/document/{applicationId}/code Request: ApplicationId  {} ", applicationId);

        gatewayService.send(Theme.CREDIT_ISSUED, applicationId);
    }
}
