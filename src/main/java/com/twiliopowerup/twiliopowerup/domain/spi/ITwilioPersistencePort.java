package com.twiliopowerup.twiliopowerup.domain.spi;

public interface ITwilioPersistencePort {
    Boolean sendMessage (String phoneNumber, String Message);

    Boolean cancel (String phoneNumber, String Message);
}
