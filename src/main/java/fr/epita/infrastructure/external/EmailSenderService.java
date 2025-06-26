package fr.epita.infrastructure.external;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.epita.domain.common.port.EmailSender;;

@Service
public class EmailSenderService implements EmailSender {

    @Override
    public void sendEmail(String email, String subject, String message) {
        System.out.println("--- Sending email ---");
        System.out.println("To : " + email);
        System.out.println("Subject : " + subject);
        System.out.println("Message : " + message);
        System.out.println("--- Email sent ---");
    }

    @Override
    public void sendEmailToMultipleRecipients(List<String> emails, String subject, String message) {
        for (String email : emails) {
            sendEmail(email, subject, message);
        }
    }
    
}