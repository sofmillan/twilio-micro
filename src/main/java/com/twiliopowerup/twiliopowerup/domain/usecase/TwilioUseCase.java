package com.twiliopowerup.twiliopowerup.domain.usecase;

import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.model.CancelModel;
import com.twiliopowerup.twiliopowerup.domain.model.MessageModel;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;

public class TwilioUseCase implements ITwilioServicePort {

    private static final  String MESSAGE = "Your order is ready. Get it with the following code: %s";

    private static final  String CANCEL = "You cannot cancel this order, it was already taken by one of our chefs";
    private final ITwilioPersistencePort twilioPersistencePort;

    public TwilioUseCase(ITwilioPersistencePort twilioPersistencePort) {
        this.twilioPersistencePort = twilioPersistencePort;
    }

    @Override
    public Boolean sendMessage(MessageModel messageModel) {
        validateMessage(messageModel);

        return twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                String.format(MESSAGE, messageModel.getSecurityCode()));
    }

    @Override
    public Boolean cancel(CancelModel cancelModel) {
        validateCancelModel(cancelModel);
        return twilioPersistencePort.cancel(cancelModel.getPhoneNumber(), CANCEL);
    }

    public void validateMessage (MessageModel messageModel){
        if(messageModel.getPhoneNumber() == null || messageModel.getPhoneNumber().isEmpty()){
            throw new RuntimeException("Invalid phone number");
        }

        if(messageModel.getSecurityCode() == null || messageModel.getSecurityCode().isEmpty()){
            throw new RuntimeException("Invalid security code");
        }
    }

    public void validateCancelModel (CancelModel cancelModel){
        if(cancelModel.getPhoneNumber() == null || cancelModel.getPhoneNumber().isEmpty()){
            throw new RuntimeException("Invalid phone number");
        }
    }
}
