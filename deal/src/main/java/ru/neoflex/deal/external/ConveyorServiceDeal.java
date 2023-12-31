package ru.neoflex.deal.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.deal.dto.LoanOfferDTO;
import ru.neoflex.deal.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.deal.dto.response.CreditDTO;
import ru.neoflex.deal.dto.response.ScoringDataDTO;

import java.util.List;

@FeignClient(name="conveyor", url = "${internal.server.url}")
public interface ConveyorServiceDeal {

    @PostMapping(value = "/conveyor/offers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<List<LoanOfferDTO>> calculationPossibleCreditConditions(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PostMapping(value = "/conveyor/calculation")
    ResponseEntity<CreditDTO> calculationCreditParameters(@RequestBody ScoringDataDTO scoringDataDTO);
}
