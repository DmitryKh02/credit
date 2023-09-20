package ru.neoflex.application.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.application.dto.LoanOfferDTO;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.application.exception.BadRequestToServer;
import ru.neoflex.application.external.DealServiceApplication;
import ru.neoflex.application.service.ApplicationService;
import ru.neoflex.application.utils.PreScoring;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final PreScoring preScoring;

    private final DealServiceApplication dealServiceApplication;

    @Override
    public List<LoanOfferDTO> calculationPossibleCreditConditions(LoanApplicationRequestDTO loanApplicationRequestDTO) throws  BadRequestToServer {
        log.trace("ApplicationServiceImpl.calculationPossibleCreditConditions - internal data: loanApplicationRequestDTO {}", loanApplicationRequestDTO);

        preScoring.isInformationCorrect(loanApplicationRequestDTO);

        List<LoanOfferDTO> responseList;
        try {
            responseList = dealServiceApplication.calculationPossibleCreditConditions(loanApplicationRequestDTO).getBody();
        } catch (FeignException ex) {
            log.warn("ApplicationServiceImpl.calculationPossibleCreditConditions - bad request to deal server");
            throw new BadRequestToServer(ex.responseBody());
        }

        log.trace("ApplicationServiceImpl.calculationPossibleCreditConditions - internal data: List<LoanOfferDTO> {}", responseList);
        return responseList;
    }

    @Override
    public void savingCreditApplication(LoanOfferDTO loanOfferDTO) throws BadRequestToServer {
        log.trace("ApplicationServiceImpl.savingCreditApplication - internal data: LoanOfferDTO {}", loanOfferDTO);

        try {
            dealServiceApplication.calculationCreditParameters(loanOfferDTO);
        }
        catch (FeignException ex){
            log.warn("ApplicationServiceImpl.savingCreditApplication - bad request to deal server");
            throw new BadRequestToServer(ex.responseBody());
        }
    }
}
