package com.twiliopowerup.twiliopowerup.infraestructure.out.twilio.adapter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TwilioAdapter implements ITwilioPersistencePort {

    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;
    private final String FROM_NUMBER ;

    @Override
    public Boolean sendMessage(String phoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message messageTwilio = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(FROM_NUMBER),
                        message)
                .create();

        return true;
    }

    @Override
    public Boolean cancel(String phoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message messageTwilio = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(FROM_NUMBER),
                        message)
                .create();
        return true;
    }
}
