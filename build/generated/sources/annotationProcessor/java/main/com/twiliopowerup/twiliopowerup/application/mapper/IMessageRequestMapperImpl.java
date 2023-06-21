package com.twiliopowerup.twiliopowerup.application.mapper;

import com.twiliopowerup.twiliopowerup.application.dto.request.CancelRequestDto;
import com.twiliopowerup.twiliopowerup.application.dto.request.MessageRequestDto;
import com.twiliopowerup.twiliopowerup.domain.model.CancelModel;
import com.twiliopowerup.twiliopowerup.domain.model.MessageModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-16T17:41:23-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11 (Oracle Corporation)"
)
@Component
public class IMessageRequestMapperImpl implements IMessageRequestMapper {

    @Override
    public MessageModel toModel(MessageRequestDto messageRequestDto) {
        if ( messageRequestDto == null ) {
            return null;
        }

        MessageModel messageModel = new MessageModel();

        messageModel.setPhoneNumber( messageRequestDto.getPhoneNumber() );
        messageModel.setSecurityCode( messageRequestDto.getSecurityCode() );

        return messageModel;
    }

    @Override
    public MessageRequestDto toRequestDto(MessageModel messageModel) {
        if ( messageModel == null ) {
            return null;
        }

        MessageRequestDto messageRequestDto = new MessageRequestDto();

        messageRequestDto.setPhoneNumber( messageModel.getPhoneNumber() );
        messageRequestDto.setSecurityCode( messageModel.getSecurityCode() );

        return messageRequestDto;
    }

    @Override
    public CancelModel toCancelModel(CancelRequestDto cancelRequestDto) {
        if ( cancelRequestDto == null ) {
            return null;
        }

        CancelModel cancelModel = new CancelModel();

        cancelModel.setPhoneNumber( cancelRequestDto.getPhoneNumber() );

        return cancelModel;
    }

    @Override
    public CancelRequestDto toCancelDto(CancelModel cancelModel) {
        if ( cancelModel == null ) {
            return null;
        }

        CancelRequestDto cancelRequestDto = new CancelRequestDto();

        cancelRequestDto.setPhoneNumber( cancelModel.getPhoneNumber() );

        return cancelRequestDto;
    }
}