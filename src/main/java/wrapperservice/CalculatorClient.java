package wrapperservice;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import wrapperservice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CalculatorClient extends WebServiceGatewaySupport {


    public AddResponse getAdd(Add request) {
        getWebServiceTemplate().setInterceptors(
                new ClientInterceptor[]{new ClientInterceptor() {

                    @Override
                    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
                        System.out.println("message from handleRequest");
                        return true;
                    }

                    @Override
                    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
                        return true;
                    }

                    @Override
                    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
                        return true;
                    }

                    @Override
                    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
                    }
                }}
        );
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
