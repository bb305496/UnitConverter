package Exceptions;

/**
 * Custom exception class to handle scenarios where a negative value is not acceptable.
 * This exception should be thrown when a method or operation receives a negative value 
 * that it cannot process.
 * 
 * 
 * @author Bartek Bielak
 * @version 1.0
 */
public class NegativeValueException extends Exception {
    
    /**
     * Constructs a new NegativeValueException with the specified detail message.
     * 
     * @param message the detail message, saved for later retrieval by the 
     *                {@link Throwable#getMessage()} method
     */
    public NegativeValueException(String message) {
        super(message);
    }
}
