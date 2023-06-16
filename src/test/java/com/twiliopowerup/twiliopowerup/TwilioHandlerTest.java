package com.twiliopowerup.twiliopowerup;

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

    @BeforeEach
    void setUp(){
        servicePort = mock(TwilioUseCase.class);
        messageRequestMapper = mock(IMessageRequestMapper.class);
        twilioHandler = new TwilioHandler(servicePort, messageRequestMapper);
        messageRequestDto = new MessageRequestDto();
        messageRequestDto.setPhoneNumber("+578458958");
        messageRequestDto.setSecurityCode("a4c3a2");
    }

    @Test
    void Should_ReturnFalse_When_ServiceReturnsFalse(){
        when(servicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto))).thenReturn(false);
        assertFalse(twilioHandler.sendMessage(messageRequestDto));
    }

    @Test
    void Should_ReturnTrue_When_ServiceReturnsTrue(){
        when(servicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto))).thenReturn(true);
        assertTrue(twilioHandler.sendMessage(messageRequestDto));
    }


}
