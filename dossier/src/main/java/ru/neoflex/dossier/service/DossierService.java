package ru.neoflex.dossier.service;

public interface DossierService {
    void finishRegistrationByApplicationId(String emailMessage);

    void createDocumentByApplicationId(String emailMessage);

    void sendDocumentByApplicationId(String emailMessage);

    void signDocumentByApplicationId(String emailMessage);

    void codeDocumentByApplicationId(String emailMessage);

    void applicationDeniedByApplicationId(String emailMessage);
}
