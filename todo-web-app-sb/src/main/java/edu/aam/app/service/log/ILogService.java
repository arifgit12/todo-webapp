package edu.aam.app.service.log;

import edu.aam.app.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILogService {

    Log createLog(Log createLog);

    List<Log> listAll();

    Page<Log> listAll(Pageable pageable);
}
