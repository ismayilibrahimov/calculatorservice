package wrapperservice.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wrapperservice.CalculatorClient;
import wrapperservice.wsdl.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int logNum;

    private CalculatorClient calculatorClient;

    public HomeController(CalculatorClient calculatorClient) {
        this.calculatorClient = calculatorClient;
    }


    @PostMapping("/add")
    public AddResponse home(@RequestBody Add request) {
        logger.info("Call " + ++logNum + ". Request to JSON: {operation: \""
                + request.getClass().getSimpleName()
                + "\", values: "
                + request.getIntA() + " and " + request.getIntB() + "}"
        );
        return calculatorClient.getAdd(request);
    }


    @PostMapping("/subtract")
    public SubtractResponse home(@RequestBody Subtract request) {
        logger.info("Call " + ++logNum + ". Request to JSON: {operation: \""
                + request.getClass().getSimpleName()
                + "\", values: "
                + request.getIntA() + " and " + request.getIntB() + "}"
        );
        return calculatorClient.getSubtract(request);
    }


    @PostMapping("/multiply")
    public MultiplyResponse home(@RequestBody Multiply request) {
        logger.info("Call " + ++logNum + ". Request to JSON: {operation: \""
                + request.getClass().getSimpleName()
                + "\", values: "
                + request.getIntA() + " and " + request.getIntB() + "}"
        );
        return calculatorClient.getMultiply(request);
    }


    @PostMapping("/divide")
    public DivideResponse home(@RequestBody Divide request) {
        logger.info("Call " + ++logNum + ". Request to JSON: {operation: \""
                + request.getClass().getSimpleName()
                + "\", values: "
                + request.getIntA() + " and " + request.getIntB() + "}"
        );
        return calculatorClient.getDivide(request);
    }
}
