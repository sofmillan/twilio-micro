package com.twiliopowerup.twiliopowerup.infraestructure.configuration;

import com.twiliopowerup.twiliopowerup.domain.api.ITwilioServicePort;
import com.twiliopowerup.twiliopowerup.domain.spi.ITwilioPersistencePort;
import com.twiliopowerup.twiliopowerup.domain.usecase.TwilioUseCase;
import com.twiliopowerup.twiliopowerup.infraestructure.out.twilio.adapter.TwilioAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Value("${twilio.accountSID}")
    private  String  ACCOUNT_SID;
    @Value("${twilio.accountToken}")
    private String AUTH_TOKEN;
    @Value("${twilio.fromNumber}")
    private  String FROM_NUMBER;

    @Bean
    public ITwilioPersistencePort twilioPersistencePort() {
        return new TwilioAdapter(ACCOUNT_SID, AUTH_TOKEN, FROM_NUMBER);
    }

    @Bean
    public ITwilioServicePort twilioServicePort(){
        return new TwilioUseCase(twilioPersistencePort());
    }
}
