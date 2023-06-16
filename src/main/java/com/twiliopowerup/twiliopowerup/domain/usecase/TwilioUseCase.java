package com.twiliopowerup.twiliopowerup.domain.usecase;

import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.model.CancelModel;
import com.twiliopowerup.twiliopowerup.domain.model.MessageModel;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwilioUseCase implements ITwilioServicePort {

    public final Logger log = LoggerFactory.getLogger(TwilioUseCase.class);

    private final String message = "Your order is ready. Get it with the following code: %s";

    private final String cancel = "You cannot cancel this order, it was already taken by one of our chefs";
    private final ITwilioPersistencePort twilioPersistencePort;

    public TwilioUseCase(ITwilioPersistencePort twilioPersistencePort) {
        this.twilioPersistencePort = twilioPersistencePort;
    }

    @Override
    public Boolean sendMessage(MessageModel messageModel) {
        validateMessage(messageModel);

        return twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                String.format(message, messageModel.getSecurityCode()));
    }

    @Override
    public Boolean cancel(CancelModel cancelModel) {
        validateCancelModel(cancelModel);
        return twilioPersistencePort.cancel(cancelModel.getPhoneNumber(), cancel);
    }

    public void validateMessage (MessageModel messageModel){
        if(messageModel.getPhoneNumber() == null || messageModel.getPhoneNumber().isEmpty()){
            log.error("Invalid phone number");
            throw new RuntimeException("Invalid phone number");
        }

        if(messageModel.getSecurityCode() == null || messageModel.getSecurityCode().isEmpty()){
            log.error("Invalid security code");
            throw new RuntimeException("Invalid security code");
        }
    }

    public void validateCancelModel (CancelModel cancelModel){
        if(cancelModel.getPhoneNumber() == null || cancelModel.getPhoneNumber().isEmpty()){
            throw new RuntimeException("Invalid phone number");
        }
    }
}
