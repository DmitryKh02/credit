package ru.neoflex.application.enums;

import lombok.Getter;
@Getter
public enum ValidationMessages {
    AMOUNT("Amount", "Amount must be greater than or equal to 10000"),
    TERM("Term", "Term must be greater than or equal to 6"),

    FIRST_NAME("First Name", "Invalid first name format"),
    LAST_NAME ("Last Name", "Invalid last name format"),
    MIDDLE_NAME("Middle Name", "Invalid middle name format"),

    EMAIL("Email", "Invalid email format"),
    BIRTHDATE("Birthdate", "Birthdate must be a past date and more then 18 years old"),

    PASSPORT_SERIES("Passport Series", "Invalid passport series format"),
    PASSPORT_NUMBER ("Passport Number", "Invalid passport number format");


    final String field;
    final String errorMessage;

    ValidationMessages(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }
}
