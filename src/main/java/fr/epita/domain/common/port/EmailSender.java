package fr.epita.domain.common.port;

import java.util.List;

public interface EmailSender {
    void sendEmail(String email, String subject, String message);
    void sendEmailToMultipleRecipients(List<String> emails, String subject, String message);
}
