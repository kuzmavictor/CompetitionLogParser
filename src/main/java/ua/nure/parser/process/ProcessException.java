package ua.nure.parser.process;

import java.util.function.Supplier;

/**
 * This exception is thrown if operation of handling log data is failed.
 */
public final class ProcessException extends RuntimeException {

    private static final long serialVersionUID = 0L;

    /**
     * Creates a {@code ReaderOperationException} instance.
     *
     * @param message
     *         additional details to describe the problem properly
     */
    public ProcessException(String message) {
        super(message);
    }
}
