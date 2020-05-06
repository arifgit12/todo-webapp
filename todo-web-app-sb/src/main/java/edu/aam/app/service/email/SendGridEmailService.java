package edu.aam.app.service.email;

import com.sendgrid.*;
import edu.aam.app.model.User;
import edu.aam.app.util.EmailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier("sendGridEmailService")
public class SendGridEmailService implements IEmailService {

    private final Logger log = LoggerFactory.getLogger(SendGridEmailService.class);

    @Autowired
    private SendGrid sendGridAPI;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.from.name}")
    private String emailSender;

    @Override
    public void sendForgetPasswordEmail(User user) {
        String subject = EmailConstants.EMAIL_FORGET_SUBJECT;
        String text = EmailConstants.EMAIL_FORGET_TEXT_START + user.getFirstName() + EmailConstants.EMAIL_FORGET_TEXT_END;

        Mail mail = getMail(subject, text, user.getEmail());
        sendMail(mail);
    }

    @Override
    public void sendForgetPasswordEmail(String email, String forgetPasswordUrl) {

        String subject = EmailConstants.EMAIL_FORGET_SUBJECT;
        String text = EmailConstants.EMAIL_FORGET_TEXT_START2  + forgetPasswordUrl + EmailConstants.EMAIL_FORGET_TEXT_END;

        Mail mail = getMail(subject, text, email);
        sendMail(mail);
    }

    private Mail getMail(String emailSubject, String emailContent, String toEmail) {
        Email to = new Email(toEmail);
        Email from = new Email(emailFrom, emailSender);
        Content content = new Content("text/plain", emailContent);
        Mail mail = new Mail(from, emailSubject, to, content);
        return mail;
    }

    private void sendMail(Mail mail) {
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridAPI.api(request);
            log.info("Send Mail Response Code: " + response.getStatusCode());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
