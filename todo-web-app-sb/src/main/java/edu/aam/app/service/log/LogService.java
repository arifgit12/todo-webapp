package edu.aam.app.service.log;

import edu.aam.app.model.Log;
import edu.aam.app.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService implements ILogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Log createLog(Log createLog) {
        return logRepository.save(createLog);
    }
}
