package ru.neoflex.gateway.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MaterialStatus {
    SINGLE("Single"),
    DIVORCED("Divorced"),
    MARRIED("Married");

    private final String value;
}
