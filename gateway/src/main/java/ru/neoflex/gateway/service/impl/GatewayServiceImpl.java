package ru.neoflex.gateway.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.gateway.dto.LoanOfferDTO;
import ru.neoflex.gateway.dto.request.FinishRegistrationRequestDTO;
import ru.neoflex.gateway.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.gateway.enums.Theme;
import ru.neoflex.gateway.exception.BadRequestToServer;
import ru.neoflex.gateway.external.ApplicationServerGateway;
import ru.neoflex.gateway.external.DealServerGateway;
import ru.neoflex.gateway.service.GatewayService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {
    private final  ApplicationServerGateway applicationServerGateway;
    private final DealServerGateway dealServerGateway;

    @Override
    public List<LoanOfferDTO> calculationPossibleCreditConditions(LoanApplicationRequestDTO loanApplicationRequestDTO) throws RuntimeException {
        log.trace("GatewayServiceImpl.calculationPossibleCreditConditions - internal data: LoanApplicationRequestDTO {}", loanApplicationRequestDTO);

        List<LoanOfferDTO> response;
        try {
            response = applicationServerGateway.calculationPossibleCreditConditions(loanApplicationRequestDTO).getBody();
        }
        catch (FeignException ex){
            log.warn("GatewayServiceImpl.calculationPossibleCreditConditions - bad request to deal service");
            throw new BadRequestToServer(ex.responseBody());
        }

        log.trace("GatewayServiceImpl.calculationPossibleCreditConditions - List<LoanOfferDTO> {}", response);
        return response;
    }

    @Override
    public void savingCreditApplication(LoanOfferDTO loanOfferDTO) throws BadRequestToServer{
        log.trace("GatewayServiceImpl.savingCreditApplication - internal data: LoanOfferDTO {}", loanOfferDTO);

        try {
            applicationServerGateway.calculationCreditParameters(loanOfferDTO);
        }
        catch (FeignException ex){
            log.warn("GatewayServiceImpl.savingCreditApplication - bad request to deal service");
            throw new BadRequestToServer(ex.responseBody());
        }
    }

    @Override
    public void finishRegistration(FinishRegistrationRequestDTO finishRegistrationRequestDTO, Long applicationId) throws BadRequestToServer {
        log.trace("GatewayServiceImpl.finishRegistration - internal data: FinishRegistrationRequestDTO {}, ApplicationID {}", finishRegistrationRequestDTO, applicationId);

        try {
            dealServerGateway.finishRegistration(finishRegistrationRequestDTO,applicationId);
        }
        catch (FeignException ex){
            log.warn("GatewayServiceImpl.finishRegistration - bad request to deal service");
            throw new BadRequestToServer(ex.responseBody());
        }
    }

    @Override
    public void send(Theme theme, Long applicationId) throws BadRequestToServer {
        log.trace("GatewayServiceImpl.send - internal data: Theme {}, ApplicationID {}", theme, applicationId);

        try {
            switch (theme) {
                case SEND_DOCUMENTS -> dealServerGateway.sendDocumentByApplicationId(applicationId);
                case CREDIT_ISSUED -> dealServerGateway.signDocumentByApplicationId(applicationId);
                case SEND_SES -> dealServerGateway.codeDocumentByApplicationId(applicationId);
                default -> log.warn("GatewayServiceImpl.send - Illegal theme");
            }
        }
        catch (FeignException ex){
            log.warn("GatewayServiceImpl.finishRegistration - bad request to deal service");
            throw new BadRequestToServer(ex.responseBody());
        }
    }
}
