package ru.neoflex.gateway.service;

import ru.neoflex.gateway.dto.LoanOfferDTO;
import ru.neoflex.gateway.dto.request.FinishRegistrationRequestDTO;
import ru.neoflex.gateway.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.gateway.enums.Theme;

import java.util.List;

public interface GatewayService {
    List<LoanOfferDTO> calculationPossibleCreditConditions(LoanApplicationRequestDTO loanApplicationRequestDTO);

    void savingCreditApplication(LoanOfferDTO loanOfferDTO);

    void finishRegistration(FinishRegistrationRequestDTO finishRegistrationRequestDTO, Long applicationId);

    void send(Theme theme, Long applicationId);
}
