package ru.neoflex.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.application.dto.LoanOfferDTO;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.application.service.ApplicationService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@Tag(name = "Заявка")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Расчёт возможных условий кредита", description = "Прескоринг + запрос на расчёт возможных условий кредита")
    public ResponseEntity<List<LoanOfferDTO>> calculationPossibleCreditConditions(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.trace("/application Request: LoanApplicationRequestDTO  {} ", loanApplicationRequestDTO);

        List<LoanOfferDTO> loanOfferDTOS = applicationService.calculationPossibleCreditConditions(loanApplicationRequestDTO);

        log.trace("/application Response: LoanOfferDTO {} ", loanOfferDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(loanOfferDTOS);
    }

    @PutMapping(value = "/offer")
    @Operation(summary = "Выбор одного из предложений", description = "Получение итогового предложения и занесение его в заявку")
    public void savingCreditApplication(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.trace("/application/offer Request: LoanOfferDTO {} ", loanOfferDTO);

        applicationService.savingCreditApplication(loanOfferDTO);
    }
}
