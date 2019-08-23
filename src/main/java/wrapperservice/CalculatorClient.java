package wrapperservice;

import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import wrapperservice.config.SoapRequestResponseInterceptor;
import wrapperservice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;



public class CalculatorClient extends WebServiceGatewaySupport {

    public CalculatorClient() {
        getWebServiceTemplate()
                .setInterceptors(new ClientInterceptor[]{new SoapRequestResponseInterceptor()});
    }


    public AddResponse getAdd(Add request) {
        return (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback("http://tempuri.org/Add"));
    }


    public SubtractResponse getSubtract(Subtract request) {
        return (SubtractResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback("http://tempuri.org/Subtract"));
    }


    public MultiplyResponse getMultiply(Multiply request) {
        return (MultiplyResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback("http://tempuri.org/Multiply"));
    }


    public DivideResponse getDivide(Divide request) {
        return (DivideResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback("http://tempuri.org/Divide"));
    }
}
