package ru.neoflex.application.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.application.dto.LoanOfferDTO;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;

import java.util.List;

@FeignClient(name="deal", url = "${internal.server.url}")
public interface DealServiceApplication {
    @PostMapping(value = "/deal/application")
    ResponseEntity<List<LoanOfferDTO>> calculationPossibleCreditConditions(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping(value = "/deal/offer")
    void calculationCreditParameters(@RequestBody LoanOfferDTO loanOfferDTO);
}
