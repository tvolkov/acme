package org.tvolkov.service;

/**
 * Thrown to indicate that no address with a given id was found
 */
public class InvalidAddressIdException extends Exception {
    public InvalidAddressIdException(String s) {
        super(s);
    }
}
