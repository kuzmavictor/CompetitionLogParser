package ua.nure.parser.model.tiny;

import jdk.nashorn.internal.ir.annotations.Immutable;
import ua.nure.parser.Query;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A query to read the log file.
 */
@Immutable
public final class ReaderQuery implements Query {

    private final String filePath;

    /**
     * Creates a {@code ReaderQuery} instance.
     *
     * @param path
     *         a path to the log file
     */
    public ReaderQuery(String path) {
        checkNotNull(path);
        filePath = path;
    }

    /**
     * Obtains the path to the log file.
     */
    public String getFilePath() {
        return filePath;
    }
}
