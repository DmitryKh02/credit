package ru.neoflex.deal.entity.jsonb;

import ru.neoflex.deal.enums.EmploymentStatus;
import ru.neoflex.deal.enums.WorkPosition;

import java.math.BigDecimal;

public record Employment(
        Long employmentId,
        EmploymentStatus status,
        String employmentINN,
        BigDecimal salary,
        WorkPosition position,
        Integer workExperienceTotal,
        Integer workExperienceCurrent
) {
}
