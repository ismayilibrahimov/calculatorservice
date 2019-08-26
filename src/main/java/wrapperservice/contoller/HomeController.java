package wrapperservice.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wrapperservice.config.CalculatorClient;
import wrapperservice.service.LogService;
import wrapperservice.wsdl.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private LogService logService;
    private CalculatorClient calculatorClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int logNum;


    public HomeController(CalculatorClient calculatorClient, LogService logService) {
        this.calculatorClient = calculatorClient;
        this.logService = logService;
    }


    @PostMapping("/add")
    public AddResponse add(@RequestBody Add request) {
        logRestRequest(request.getClass().getSimpleName(), request.getIntA(), request.getIntB());
        return calculatorClient.getAdd(request);
    }


    @PostMapping("/subtract")
    public SubtractResponse subtract(@RequestBody Subtract request) {
        logRestRequest(request.getClass().getSimpleName(), request.getIntA(), request.getIntB());
        return calculatorClient.getSubtract(request);
    }


    @PostMapping("/multiply")
    public MultiplyResponse multiply(@RequestBody Multiply request) {
        logRestRequest(request.getClass().getSimpleName(), request.getIntA(), request.getIntB());

        return calculatorClient.getMultiply(request);
    }


    @PostMapping("/divide")
    public DivideResponse divide(@RequestBody Divide request) {
        logRestRequest(request.getClass().getSimpleName(), request.getIntA(), request.getIntB());
        return calculatorClient.getDivide(request);
    }


    private void logRestRequest(String operation, int intA, int intB) {
        String payload = "Call " + ++logNum + ". Request to JSON: {operation: \""
                + operation
                + "\", values: "
                + intA + " and " + intB + "}";
        logger.info(payload);
        logService.logToDB(payload, "rest");
    }
}
