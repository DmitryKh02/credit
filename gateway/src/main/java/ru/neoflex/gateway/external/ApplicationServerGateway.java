package ru.neoflex.gateway.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.gateway.dto.LoanOfferDTO;
import ru.neoflex.gateway.dto.request.LoanApplicationRequestDTO;

import java.util.List;

@FeignClient(name = "application", url = "${internal.server.url.application}")
public interface ApplicationServerGateway {
    @PostMapping
    ResponseEntity<List<LoanOfferDTO>> calculationPossibleCreditConditions(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping(value = "/offer")
    void calculationCreditParameters(@RequestBody LoanOfferDTO loanOfferDTO);
}
