package com.xmen.domain.exceptions;

/**
 * Error processing DNA verification
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class InvalidDnaException extends RuntimeException{
    public InvalidDnaException(final String message){super(message);}
}

