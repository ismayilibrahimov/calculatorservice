package wrapperservice.service;

import org.springframework.stereotype.Service;
import wrapperservice.model.Log;
import wrapperservice.model.Payload;
import wrapperservice.repository.LogRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private LogRepository logRepo;

    public LogService(LogRepository logRepo) {
        this.logRepo = logRepo;
    }


    public void logToDB(String request, String service) {
        Log log;
        if("soap".equalsIgnoreCase(service)) {
            log = logRepo.findTopByOrderByIdDesc();
        } else {
            log = new Log();
        }

        Payload payload = new Payload();
        payload.setValue(request);
        List<Payload> payloads = new ArrayList<>();
        payloads.add(payload);
        log.setPayloads(payloads);
        logRepo.save(log);
    }

}
