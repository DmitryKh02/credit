package ru.neoflex.application.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.neoflex.application.dto.request.LoanApplicationRequestDTO;
import ru.neoflex.application.enums.ValidationMessages;
import ru.neoflex.application.exception.InvalidDataException;
import ru.neoflex.application.exception.ErrorMessage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class PreScoring {
    private static final String PATTERN_NAME = "^[A-Za-z]{2,30}$";
    private static final String PATTERN_EMAIL = "[\\w\\.]{2,50}@[\\w\\.]{2,20}";
    private static final String PATTERN_SERIES = "\\d{4}";
    private static final String PATTERN_NUMBER = "\\d{6}";
    private static final BigDecimal MINIMAL_AMOUNT = BigDecimal.valueOf(10000);
    private static final int MINIMAL_TERM = 6;
    private final List<ErrorMessage> invalidInformation = new ArrayList<>();

    /**
     * Прескоринг данных для дальнейшей выдачи кредита
     * <p>
     * @param loanApplicationRequestDTO основная информация о клиенте
     * @throws InvalidDataException ошибка полученных данных
     */
    public void isInformationCorrect(LoanApplicationRequestDTO loanApplicationRequestDTO) throws InvalidDataException {
        log.debug("PreScoring internal data: {}", loanApplicationRequestDTO);
        invalidInformation.clear();

        checkInvalidInformation(isValidSum(loanApplicationRequestDTO.amount()), ValidationMessages.AMOUNT.getField(), ValidationMessages.AMOUNT.getErrorMessage());
        checkInvalidInformation(isValidTerm(loanApplicationRequestDTO.term()), ValidationMessages.TERM.getField(), ValidationMessages.TERM.getErrorMessage());

        checkInvalidInformation(isValidName(loanApplicationRequestDTO.firstName()), ValidationMessages.FIRST_NAME.getField(), ValidationMessages.FIRST_NAME.getErrorMessage());
        checkInvalidInformation(isValidName(loanApplicationRequestDTO.lastName()), ValidationMessages.LAST_NAME.getField(), ValidationMessages.LAST_NAME.getErrorMessage());

        if (loanApplicationRequestDTO.middleName() != null )
            checkInvalidInformation(isValidName(loanApplicationRequestDTO.middleName()), ValidationMessages.MIDDLE_NAME.getField(), ValidationMessages.MIDDLE_NAME.getErrorMessage());

        checkInvalidInformation(isValidEmail(loanApplicationRequestDTO.email()), ValidationMessages.EMAIL.getField(), ValidationMessages.EMAIL.getErrorMessage());
        checkInvalidInformation(isValidBirthday(loanApplicationRequestDTO.birthdate()), ValidationMessages.BIRTHDATE.getField(), ValidationMessages.BIRTHDATE.getErrorMessage());

        checkInvalidInformation(isValidPassportSeries(loanApplicationRequestDTO.passportSeries()), ValidationMessages.PASSPORT_SERIES.getField(), ValidationMessages.PASSPORT_SERIES.getErrorMessage());
        checkInvalidInformation(isValidPassportNumber(loanApplicationRequestDTO.passportNumber()), ValidationMessages.PASSPORT_NUMBER.getField(), ValidationMessages.PASSPORT_NUMBER.getErrorMessage());

        if(!invalidInformation.isEmpty()) {
            log.warn("PreScorning invalid internal data: {}", invalidInformation);
            throw new InvalidDataException(invalidInformation);
        }

        log.debug("PreScoring all data is valid");
    }

    /**
     * Добавление неверных полей в ошибку
     * <p>
     * @param isValid прошло ли проверку поле
     * @param fieldName имя поля
     * @param message сообщение для клиента
     */
    private void checkInvalidInformation(boolean isValid, String fieldName, String message){
        if(!isValid){
            ErrorMessage ex = new ErrorMessage(fieldName, message);
            int endOfList = invalidInformation.size();
            invalidInformation.add(endOfList, ex);
        }
    }

    /**
     * Имя, Фамилия - от 2 до 30 латинских букв.<p>
     * Отчество, при наличии - от 2 до 30 латинских букв.
     * <p>
     * @param name любое имя
     * @return true - подходит под паттерн, false - нет
     */
    private boolean isValidName(String name) {
        return name.matches(PATTERN_NAME);
    }

    /**
     * Сумма кредита - действительно число, большее или равное 10000.
     * <p>
     * @param sum сумма кредита
     * @return  true - подходит под условие, false - нет
     */
    private boolean isValidSum(BigDecimal sum){
        return sum.compareTo(MINIMAL_AMOUNT) >= 0;
    }

    /**
     * Срок кредита - целое число, большее или равное 6.
     * <p>
     * @param term количество месяцев платежа
     * @return true - подходит под условие, false - нет
     */
    private boolean isValidTerm(int term){
        return term >= MINIMAL_TERM;
    }

    /**
     * Дата рождения - число в формате гггг-мм-дд, не позднее 18 лет с текущего дня.
     * <p>
     * @param birthday дата рождения
     * @return true - подходит под условие, false - нет
     */
    private boolean isValidBirthday(LocalDate birthday){
        return calculateAge(birthday) > 18;
    }

    /**
     * Email адрес - строка, подходящая под паттерн [\w\.]{2,50}@[\w\.]{2,20}
     * <p>
     * @param email электронная почта пользователя
     * @return true - подходит под условие, false - нет
     */
    private boolean isValidEmail(String email) {
        return email.matches(PATTERN_EMAIL);
    }

    /**
     * Серия паспорта - 4 цифры
     * <p>
     * @param series серия паспорта
     * @return true - подходит под условие, false - нет
     */
    private boolean isValidPassportSeries(String series) {
       return series.matches(PATTERN_SERIES);
    }

    /**
     * Номер паспорта - 6 цифр
     * <p>
     * @param number номер паспорта
     * @return true - подходит под условие, false - нет
     */
    private boolean isValidPassportNumber(String number) {
        return number.matches(PATTERN_NUMBER);
    }

    /**
     * Подсчет возраста из даты
     * <p>
     * @param birthdate день рождения
     * @return возраст
     */
    public int calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }
}
