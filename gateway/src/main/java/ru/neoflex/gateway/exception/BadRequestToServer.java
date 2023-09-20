package ru.neoflex.gateway.exception;

import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.Optional;

@Getter
public class BadRequestToServer extends RuntimeException{
    private byte[] response;

    public BadRequestToServer(Optional<ByteBuffer> response){
        super();
        response.ifPresent(byteBuffer -> this.response = byteBuffer.array());
    }
}
