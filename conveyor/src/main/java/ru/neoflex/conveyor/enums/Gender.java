package ru.neoflex.conveyor.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    NOT_BINARY("Not Binary");

    private final String gender;
}
