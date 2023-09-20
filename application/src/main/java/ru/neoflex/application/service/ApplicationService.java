package ru.neoflex.application.service;

import ru.neoflex.application.dto.LoanOfferDTO;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;

import java.util.List;

public interface ApplicationService {

    List<LoanOfferDTO> calculationPossibleCreditConditions(LoanApplicationRequestDTO loanApplicationRequestDTO);

    void savingCreditApplication(LoanOfferDTO loanOfferDTO);
}
