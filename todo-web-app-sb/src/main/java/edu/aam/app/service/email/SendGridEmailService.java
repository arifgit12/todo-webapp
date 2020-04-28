package edu.aam.app.service.email;

import edu.aam.app.model.User;
import org.springframework.stereotype.Service;

@Service
public class SendGridEmailService implements IEmailService {

    @Override
    public void sendForgetPasswordEmail(User user) {

    }
}
