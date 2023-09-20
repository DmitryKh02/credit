package ru.neoflex.gateway.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmploymentStatus {
    UNEMPLOYED("Unemployed"),
    SELF_EMPLOYED("Self-employed"),
    BUSINESS_OWNER("Business owner");

    private final String value;
}
