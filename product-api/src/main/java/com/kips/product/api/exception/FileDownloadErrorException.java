package com.kips.product.api.exception;

public class FileDownloadErrorException extends RuntimeException {
    public FileDownloadErrorException(String message) {
        super(message);
    }
}
