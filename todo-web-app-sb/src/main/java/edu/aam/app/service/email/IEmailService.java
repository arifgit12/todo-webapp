package edu.aam.app.service.email;

import edu.aam.app.model.User;

public interface IEmailService {
    void sendForgetPasswordEmail(User seller);
}
