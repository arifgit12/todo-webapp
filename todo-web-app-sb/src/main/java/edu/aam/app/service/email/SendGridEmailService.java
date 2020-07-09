package edu.aam.app.service.email;

import com.sendgrid.*;
import edu.aam.app.model.ConfirmationToken;
import edu.aam.app.model.Log;
import edu.aam.app.model.User;
import edu.aam.app.model.enums.LogType;
import edu.aam.app.service.account.IAccountService;
import edu.aam.app.service.log.ILogService;
import edu.aam.app.util.EmailConstants;
import edu.aam.app.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier("sendGridEmailService")
public class SendGridEmailService implements IEmailService {

    private final Logger log = LoggerFactory.getLogger(SendGridEmailService.class);

    @Autowired
    private SendGrid sendGridAPI;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ILogService logService;

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

    @Override
    public void sendRegisterEmail(User user, String confirmUrl) {
        ConfirmationToken confirmationToken = accountService.saveConfirmationToken(user);

        String subject = EmailConstants.EMAIL_REGISTER_SUBJECT;
        String text = EmailConstants.EMAIL_FORGET_TEXT_START + user.getFirstName() + "\n\n" +
                "To confirm your account, please click here : " +
                confirmUrl +
                "/confirm-account?token=" +
                confirmationToken.getConfirmationToken();
        Mail mail = getMail(subject, text, user.getEmail());
        sendMail(mail);
    }

    private Mail getMail(String emailSubject, String emailContent, String toEmail) {
        Email to = new Email(toEmail);
        Email from = new Email(emailFrom, emailSender);
        Content content = new Content("text/plain", emailContent);
        Mail mail = new Mail(from, emailSubject, to, content);
        return mail;
    }

    @Async
    public void sendMail(Mail mail) {
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridAPI.api(request);
            log.info("Send Mail Response Code: " + response.getStatusCode());
        } catch (IOException ex) {
            Log log = new Log();
            log.setLogKey(mail.getFrom().getName());
            log.setType(LogType.SEND_GRID_MAIL_FAILED);
            log.setContent(StringUtils.substring(ex.getMessage(), 0, 100));
            log.setIpAddress(ServletUtils.getRequestIp());
            logService.createLog(log);
        }
    }
}
