package edu.aam.app.service.email;

import edu.aam.app.model.User;
import edu.aam.app.util.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailService implements IEmailService {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public void sendForgetPasswordEmail(User user) {
        String to = user.getEmail();
        String subject = EmailConstants.EMAIL_FORGET_SUBJECT;
        String text = EmailConstants.EMAIL_FORGET_TEXT_START + user.getFirstName() + EmailConstants.EMAIL_FORGET_TEXT_END;
        sendSimpleMessage(to,subject,text);
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
