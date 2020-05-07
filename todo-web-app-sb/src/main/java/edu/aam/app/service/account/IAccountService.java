package edu.aam.app.service.account;

import edu.aam.app.model.ConfirmationToken;
import edu.aam.app.model.User;

public interface IAccountService {
    ConfirmationToken getConfirmationToken(String confirmationToken);
    ConfirmationToken saveConfirmationToken(User user);
}
