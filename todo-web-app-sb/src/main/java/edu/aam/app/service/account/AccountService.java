package edu.aam.app.service.account;

import edu.aam.app.model.ConfirmationToken;
import edu.aam.app.model.User;
import edu.aam.app.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken getConfirmationToken(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public ConfirmationToken saveConfirmationToken(User user) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.save(new ConfirmationToken(user));
        return confirmationToken;
    }
}
