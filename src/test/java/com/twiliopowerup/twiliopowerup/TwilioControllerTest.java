package com.twiliopowerup.twiliopowerup;

import com.twiliopowerup.twiliopowerup.application.dto.request.CancelRequestDto;
import com.twiliopowerup.twiliopowerup.application.dto.request.MessageRequestDto;
import com.twiliopowerup.twiliopowerup.application.handler.ITwilioHandler;
import com.twiliopowerup.twiliopowerup.application.handler.impl.TwilioHandler;
import com.twiliopowerup.twiliopowerup.infraestructure.input.TwilioController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TwilioControllerTest {
    ITwilioHandler twilioHandler;
    TwilioController twilioController;
    MessageRequestDto messageRequestDto;
    CancelRequestDto cancelRequestDto;

    @BeforeEach
    void setUp(){
        twilioHandler = mock(TwilioHandler.class);
        twilioController = new TwilioController(twilioHandler);
    }

    @Test
    void SendMessage_Should_ReturnResponseEntityOk_WhenHandlerReturnsTrue(){
        messageRequestDto = new MessageRequestDto();
        messageRequestDto.setPhoneNumber("+578458958");
        messageRequestDto.setSecurityCode("a4c3a2");
        boolean response = true;
        when(twilioHandler.sendMessage(messageRequestDto)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = twilioController.sendMessage(messageRequestDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(response);
    }

    @Test
    void Cancel_Should_ReturnResponseEntityOk_WhenHandlerReturnsTrue(){
        cancelRequestDto = new CancelRequestDto();
        cancelRequestDto.setPhoneNumber("+578458958");
        boolean response = true;

        when(twilioHandler.cancel(cancelRequestDto)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = twilioController.cancel(cancelRequestDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(response);
    }
}
