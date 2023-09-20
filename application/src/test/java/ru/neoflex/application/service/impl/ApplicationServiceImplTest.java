package ru.neoflex.application.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.neoflex.application.dto.LoanOfferDTO;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.application.external.DealServiceApplication;
import ru.neoflex.application.utils.PreScoring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
class ApplicationServiceImplTest {
    @Mock
    private PreScoring preScoring;
    @Mock
    private DealServiceApplication dealServiceApplication;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    void ApplicationService_CalculationPossibleCreditConditions_ReturnListLoanOfferDTO(){
        LoanOfferDTO loanOffer1 = new LoanOfferDTO(0L, BigDecimal.valueOf(150000), BigDecimal.valueOf(151500.00),
            24, BigDecimal.valueOf(6547.17), BigDecimal.valueOf(4.5), true, true);

        LoanOfferDTO loanOffer2 = new LoanOfferDTO(1L, BigDecimal.valueOf(150000), BigDecimal.valueOf(151500.00),
                24, BigDecimal.valueOf(6614.35), BigDecimal.valueOf(5.5), true, false);

        LoanOfferDTO loanOffer3 = new LoanOfferDTO(2L, BigDecimal.valueOf(150000), BigDecimal.valueOf(150000),
                24, BigDecimal.valueOf(6749.94), BigDecimal.valueOf(7.5), false, true);

        LoanOfferDTO loanOffer4 = new LoanOfferDTO(3L, BigDecimal.valueOf(150000), BigDecimal.valueOf(150000),
                24, BigDecimal.valueOf(6818.35), BigDecimal.valueOf(8.5), false, false);
        List<LoanOfferDTO> expectedList = new ArrayList<>(Arrays.asList(loanOffer1,loanOffer2,loanOffer3,loanOffer4));

        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO(
                BigDecimal.valueOf(150000),
                24,
                "Ivan",
                "Petrov",
                null,
                "ivan@example.com",
                LocalDate.of(1990, 8, 10),
                "5678",
                "123456"
        );

        List<LoanOfferDTO> resultList = new ArrayList<>(Arrays.asList(loanOffer1,loanOffer2,loanOffer3,loanOffer4));
        when(dealServiceApplication.calculationPossibleCreditConditions(loanApplicationRequestDTO)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(resultList));

        List<LoanOfferDTO> actualList = applicationService.calculationPossibleCreditConditions(loanApplicationRequestDTO);

        Assertions.assertEquals(expectedList,actualList);
    }
}
