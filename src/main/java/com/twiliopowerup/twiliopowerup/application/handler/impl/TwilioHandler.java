package com.twiliopowerup.twiliopowerup.application.handler.impl;

import com.twiliopowerup.twiliopowerup.application.dto.request.CancelRequestDto;
import com.twiliopowerup.twiliopowerup.application.dto.request.MessageRequestDto;
import com.twiliopowerup.twiliopowerup.application.handler.ITwilioHandler;
import com.twiliopowerup.twiliopowerup.application.mapper.IMessageRequestMapper;
import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwilioHandler implements ITwilioHandler {

    private final ITwilioServicePort twilioServicePort;
    private final IMessageRequestMapper messageRequestMapper;

    @Override
    public Boolean sendMessage(MessageRequestDto messageRequestDto) {
        return twilioServicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto));
    }

    @Override
    public Boolean cancel(CancelRequestDto cancelRequestDto) {
        return twilioServicePort.cancel(messageRequestMapper.toCancelModel(cancelRequestDto));

    }
}
