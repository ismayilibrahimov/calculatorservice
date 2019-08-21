package wrapperservice.contoller;

import wrapperservice.CalculatorClient;
import wrapperservice.wsdl.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private CalculatorClient calculatorClient;

    public HomeController(CalculatorClient calculatorClient) {
        this.calculatorClient = calculatorClient;
    }


    @PostMapping("/add")
    public AddResponse home(@RequestBody Add request) {
        return calculatorClient.getAdd(request);
    }


    @PostMapping("/subtract")
    public SubtractResponse home(@RequestBody Subtract request) {
        return calculatorClient.getSubtract(request);
    }


    @PostMapping("/multiply")
    public MultiplyResponse home(@RequestBody Multiply request) {
        return calculatorClient.getMultiply(request);
    }


    @PostMapping("/divide")
    public DivideResponse home(@RequestBody Divide request) {
        return calculatorClient.getDivide(request);
    }
}
