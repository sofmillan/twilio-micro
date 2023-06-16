package com.twiliopowerup.twiliopowerup;

import com.twiliopowerup.twiliopowerup.application.dto.request.CancelRequestDto;
import com.twiliopowerup.twiliopowerup.application.dto.request.MessageRequestDto;
import com.twiliopowerup.twiliopowerup.application.handler.ITwilioHandler;
import com.twiliopowerup.twiliopowerup.application.handler.impl.TwilioHandler;
import com.twiliopowerup.twiliopowerup.application.mapper.IMessageRequestMapper;
import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.usecase.TwilioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwilioHandlerTest {

    ITwilioServicePort servicePort;
    ITwilioHandler twilioHandler;
    IMessageRequestMapper messageRequestMapper;
    MessageRequestDto messageRequestDto;
    CancelRequestDto cancelRequestDto;

    @BeforeEach
    void setUp(){
        servicePort = mock(TwilioUseCase.class);
        messageRequestMapper = mock(IMessageRequestMapper.class);
        twilioHandler = new TwilioHandler(servicePort, messageRequestMapper);

    }

    @Test
    void SendMessage_Should_ReturnFalse_When_ServiceReturnsFalse(){
        messageRequestDto = new MessageRequestDto();
        messageRequestDto.setPhoneNumber("+578458958");
        messageRequestDto.setSecurityCode("a4c3a2");

        when(servicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto))).thenReturn(false);

        assertFalse(twilioHandler.sendMessage(messageRequestDto));
    }

    @Test
    void SendMessage_Should_ReturnTrue_When_ServiceReturnsTrue_SendMessage(){
        messageRequestDto = new MessageRequestDto();
        messageRequestDto.setPhoneNumber("+578458958");
        messageRequestDto.setSecurityCode("a4c3a2");

        when(servicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto))).thenReturn(true);

        assertTrue(twilioHandler.sendMessage(messageRequestDto));
    }


    @Test
    void Cancel_Should_ReturnTrue_When_ServiceReturnsTrue(){
        cancelRequestDto = new CancelRequestDto();
        cancelRequestDto.setPhoneNumber("+584584645526");

        when(servicePort.cancel(messageRequestMapper.toCancelModel(cancelRequestDto))).thenReturn(true);

        assertTrue(twilioHandler.cancel(cancelRequestDto));
    }

    @Test
    void Cancel_Should_ReturnFalse_When_ServiceReturnsFalse(){
        cancelRequestDto = new CancelRequestDto();
        cancelRequestDto.setPhoneNumber("+584584645526");

        when(servicePort.cancel(messageRequestMapper.toCancelModel(cancelRequestDto))).thenReturn(false);

        assertFalse(twilioHandler.cancel(cancelRequestDto));
    }


}
