package ru.neoflex.dossier.service;

import ru.neoflex.dossier.dto.EmailMessage;

public interface MessageSender {
    void sendMessage(EmailMessage emailMessage);
}
