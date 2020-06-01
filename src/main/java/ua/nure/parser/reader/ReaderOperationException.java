package ua.nure.parser.reader;

/**
 * This exception is thrown if operation of the reading log file is failed.
 */
public final class ReaderOperationException extends RuntimeException {

    private static final long serialVersionUID = 0L;

    /**
     * Creates a {@code ReaderOperationException} instance.
     *
     * @param message
     *         additional details to describe the problem properly
     */
    public ReaderOperationException(String message) {
        super(message);
    }
}
