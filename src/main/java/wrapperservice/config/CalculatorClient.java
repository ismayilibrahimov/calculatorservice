package wrapperservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import wrapperservice.service.LogService;
import wrapperservice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class CalculatorClient extends WebServiceGatewaySupport {

    @Autowired
    private LogService logService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int logNum;


    public CalculatorClient() {
        getWebServiceTemplate()
                .setInterceptors(new ClientInterceptor[]{
                        new ClientInterceptor() {
                            @Override
                            public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
                                try {
                                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                                    messageContext.getRequest().writeTo(buffer);
                                    String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
                                    String request = "Call " + ++logNum + ". Request to SOAP: " + payload;
                                    logger.info(request);
                                    logService.logToDB(request, "soap");


                                } catch (IOException e) {
                                    throw new WebServiceClientException("Can not write into the out stream", e) {
                                        private static final long serialVersionUID = -7118480620416458069L;
                                    };
                                }
                                return true;
                            }

                            @Override
                            public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
                                try {
                                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                                    messageContext.getResponse().writeTo(buffer);
                                    String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
                                    String request = "Call " + logNum + ". Response from SOAP: " + payload;
                                    logger.info(request);
                                    logService.logToDB(request, "soap");
                                } catch (IOException e) {
                                    throw new WebServiceClientException("Can not write into the out stream", e) {
                                        private static final long serialVersionUID = -7118480620416458069L;
                                    };
                                }
                                return true;
                            }

                            @Override
                            public boolean handleFault(MessageContext messageContext) throws WebServiceClientException { return true; }

                            @Override
                            public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException { }
                        }
                });
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
