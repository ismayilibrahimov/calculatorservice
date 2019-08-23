package wrapperservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SoapRequestResponseInterceptor implements ClientInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int logNum;

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            messageContext.getRequest().writeTo(buffer);
            String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
            logger.info("Call " + ++logNum + ". Request to SOAP: " + payload);
        } catch (IOException e) {
            throw new WebServiceClientException("Can not write the SOAP request into the out stream", e) {
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
            logger.info("Call " + logNum + ". Response from SOAP: " + payload);
        } catch (IOException e) {
            throw new WebServiceClientException("Can not write the SOAP response into the out stream", e) {
                private static final long serialVersionUID = -7118480620416458069L;
            };
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException { }
}
