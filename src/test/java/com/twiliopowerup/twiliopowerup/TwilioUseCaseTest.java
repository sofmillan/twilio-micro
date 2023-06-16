package com.twiliopowerup.twiliopowerup;

import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.model.CancelModel;
import com.twiliopowerup.twiliopowerup.domain.model.MessageModel;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;
import com.twiliopowerup.twiliopowerup.domain.usecase.TwilioUseCase;
import com.twiliopowerup.twiliopowerup.infraestructure.out.twilio.adapter.TwilioAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TwilioUseCaseTest {
     ITwilioPersistencePort twilioPersistencePort;
     ITwilioServicePort twilioServicePort;
     MessageModel messageModel;
     CancelModel cancelModel;

     @BeforeEach
    void setUp(){
         twilioPersistencePort = mock(TwilioAdapter.class);
         twilioServicePort = new TwilioUseCase(twilioPersistencePort);
     }

     @Test
    void SendMessage_Should_ReturnFalse_When_MessageNotSent(){
         String message = "Your order is ready. Get it with the following code: %s";
         messageModel = new MessageModel();
         messageModel.setPhoneNumber("+589985965");
         messageModel.setSecurityCode("a7c5b");

         when(twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                 String.format(message, messageModel.getSecurityCode()))).thenReturn(false);

         assertFalse(twilioServicePort.sendMessage(messageModel));
     }



    @Test
    void SendMessage_Should_ReturnTrue_When_MessageSent(){
        String message = "Your order is ready. Get it with the following code: %s";
        messageModel = new MessageModel();
        messageModel.setPhoneNumber("+589985965");
        messageModel.setSecurityCode("a7c5b");

        when(twilioPersistencePort.sendMessage(messageModel.getPhoneNumber(),
                String.format(message, messageModel.getSecurityCode()))).thenReturn(true);

        assertTrue(twilioServicePort.sendMessage(messageModel));
    }

    @Test
    void Cancel_Should_ReturnFalse_When_MessageNotSent(){
        String message = "You cannot cancel this order, it was already taken by one of our chefs";
        cancelModel = new CancelModel();
        cancelModel.setPhoneNumber("+57845826");

        when(twilioPersistencePort.cancel(cancelModel.getPhoneNumber(), message)).thenReturn(false);

        assertFalse(twilioServicePort.cancel(cancelModel));
    }

    @Test
    void Cancel_Should_ReturnTrue_When_MessageSent(){
        String message = "You cannot cancel this order, it was already taken by one of our chefs";
        cancelModel = new CancelModel();
        cancelModel.setPhoneNumber("+57845826");

        when(twilioPersistencePort.cancel(cancelModel.getPhoneNumber(), message)).thenReturn(true);

        assertTrue(twilioServicePort.cancel(cancelModel));
    }

    @Test
    void SendMessage_Should_ThrowException_When_PhoneNumberIsNull(){
        messageModel = new MessageModel();
        messageModel.setPhoneNumber(null);
        messageModel.setSecurityCode("a7c5b");

        assertThrows(RuntimeException.class, ()-> twilioServicePort.sendMessage(messageModel));
    }

    @Test
    void SendMessage_Should_ThrowException_When_SecurityCodeIsNull(){
        messageModel = new MessageModel();
        messageModel.setPhoneNumber("58956895");
        messageModel.setSecurityCode(null);

        assertThrows(RuntimeException.class, ()-> twilioServicePort.sendMessage(messageModel));
    }

    @Test
    void SendMessage_Should_ThrowException_When_SecurityCodeIsEmpty(){
        messageModel = new MessageModel();
        messageModel.setPhoneNumber("+9856985");
        messageModel.setSecurityCode("");

        assertThrows(RuntimeException.class, ()-> twilioServicePort.sendMessage(messageModel));
    }

    @Test
    void SendMessage_Should_ThrowException_When_PhoneNumberIsEmpty(){
        messageModel = new MessageModel();
        messageModel.setPhoneNumber("");
        messageModel.setSecurityCode("ac5f34");

        assertThrows(RuntimeException.class, ()-> twilioServicePort.sendMessage(messageModel));
    }

    @Test
    void Cancel_Should_ThrowException_When_PhoneNumberIsEmpty(){
        cancelModel = new CancelModel();
        cancelModel.setPhoneNumber("");

        assertThrows(RuntimeException.class, ()-> twilioServicePort.cancel(cancelModel));
    }

    @Test
    void Cancel_Should_ThrowException_When_PhoneNumberIsNull(){
        cancelModel = new CancelModel();
        cancelModel.setPhoneNumber(null);

        assertThrows(RuntimeException.class, ()-> twilioServicePort.cancel(cancelModel));
    }
}
