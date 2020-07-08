package edu.aam.app.service.log;

import edu.aam.app.model.Log;
import edu.aam.app.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements ILogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Log createLog(Log createLog) {
        return logRepository.save(createLog);
    }

    @Override
    public List<Log> listAll() {
        return logRepository.findAll();
    }

    @Override
    public Page<Log> listAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }
}
