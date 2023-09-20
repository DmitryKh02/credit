package ru.neoflex.dossier.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.dossier.exception.NoSuchThemeException;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum Theme {
    FINISH_REGISTRATION("Finish_registration", "Please, Finish-registration"),
    CREATE_DOCUMENTS("Create_documents", "Please, Create-documents"),
    SEND_DOCUMENTS("Send_documents", "Please, Send-documents"),
    SEND_SES("Send_ses", "Please, Send-ses"),
    CREDIT_ISSUED("Credit_issued", "Congratulations! Your credit is issued!"),
    APPLICATION_DENIED("Application_denied", "We are sorry, because your application is denied");

    private final String themeName;
    private final String messageInfo;

    @JsonValue
    public String getThemeName() {
        return themeName;
    }

    public static Theme fromString(String inputTheme) throws NoSuchThemeException {
        for (Theme theme : Theme.values()) {
            if (theme.themeName.equalsIgnoreCase(inputTheme)) {
                return theme;
            }
        }
        throw new NoSuchThemeException("Error convert input string to theme: ", inputTheme);
    }
}
