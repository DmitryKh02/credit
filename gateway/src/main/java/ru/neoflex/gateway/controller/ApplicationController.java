package ru.neoflex.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.dto.LoanOfferDTO;
import ru.neoflex.gateway.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.gateway.service.GatewayService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@Tag(name = "Контроллер переадресации на микросервис application")
public class ApplicationController {
    private final GatewayService gatewayService;

    @PostMapping
    @Operation(summary = "Расчёт возможных условий кредита", description = "Прескоринг + запрос на расчёт возможных условий кредита")
    public ResponseEntity<List<LoanOfferDTO>> calculationPossibleCreditConditions(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.trace("/application Request: LoanApplicationRequestDTO  {} ", loanApplicationRequestDTO);

        List<LoanOfferDTO> loanOfferDTOS = gatewayService.calculationPossibleCreditConditions(loanApplicationRequestDTO);

        log.trace("/application Response: LoanOfferDTO {} ", loanOfferDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(loanOfferDTOS);
    }

    @PostMapping(value = "/apply")
    @Operation(summary = "Выбор одного из предложений", description = "Получение итогового предложения и занесение его в заявку")
    public void calculationCreditParameters(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.trace("/application/offer Request: LoanOfferDTO {} ", loanOfferDTO);

        gatewayService.savingCreditApplication(loanOfferDTO);
    }
}
