package ru.neoflex.gateway.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.gateway.dto.request.FinishRegistrationRequestDTO;


@FeignClient(name = "deal", url = "${internal.server.url.deal}")
public interface DealServerGateway {
    @PutMapping(value = "/calculate/{applicationId}")
    void finishRegistration(@RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable Long applicationId);
    @PostMapping(value = "/document/{applicationId}/send")
    void sendDocumentByApplicationId(@PathVariable Long applicationId);
    @PostMapping(value = "/document/{applicationId}/sign")
    void signDocumentByApplicationId(@PathVariable Long applicationId);
    @PostMapping(value = "/document/{applicationId}/code")
    void codeDocumentByApplicationId(@PathVariable Long applicationId);
}
