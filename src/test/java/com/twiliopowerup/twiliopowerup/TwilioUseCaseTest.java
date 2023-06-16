package com.twiliopowerup.twiliopowerup;

import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.model.MessageModel;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;
import com.twiliopowerup.twiliopowerup.domain.usecase.TwilioUseCase;
import com.twiliopowerup.twiliopowerup.infraestructure.out.twilio.adapter.TwilioAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TwilioUseCaseTest {
     ITwilioPersistencePort twilioPersistencePort;
     ITwilioServicePort twilioServicePort;
     MessageModel messageModel;

     @BeforeEach
    void setUp(){
         twilioPersistencePort = mock(TwilioAdapter.class);
         twilioServicePort = new TwilioUseCase(twilioPersistencePort);
         messageModel = new MessageModel();
         messageModel.setPhoneNumber("+589985965");
         messageModel.setSecurityCode("a7c5b");
     }

     @Test
    void Should_ReturnFalse_When_MessageNotSent(){
         String message = "Your order is ready. Get it with the following code: %s";
         when(twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                 String.format(message, messageModel.getSecurityCode()))).thenReturn(false);

         assertFalse(twilioServicePort.sendMessage(messageModel));
     }

    @Test
    void Should_ReturnTrue_When_MessageNotSent(){
        String message = "Your order is ready. Get it with the following code: %s";
        when(twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                String.format(message, messageModel.getSecurityCode()))).thenReturn(true);

        assertTrue(twilioServicePort.sendMessage(messageModel));
    }
}
