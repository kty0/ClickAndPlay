package fr.epita.domain.seance.port;

import java.util.List;

public interface EmailSender {
    void sendEmail(String email, String subject, String message);
    void sendEmailToMultipleRecipients(List<String> emails, String subject, String message);
}
