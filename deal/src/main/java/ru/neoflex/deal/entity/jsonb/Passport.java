package ru.neoflex.deal.entity.jsonb;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Passport{
        private Long passportId;

        private String series;

        private String number;

        private String issueBranch;

        private LocalDate issueDate;
}
