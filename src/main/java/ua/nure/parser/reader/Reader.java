package ua.nure.parser.reader;

import ua.nure.parser.Query;
import ua.nure.parser.Result;

import java.util.Collection;

/**
 * Base interface for reader implementations.
 *
 * @param <R>
 *         a result of reader work
 * @param <Q>
 *         a query that contain necessary information to read.
 */

public interface Reader<R extends Result, Q extends Query> {

    /**
     * Reads data from log file.
     *
     * @param query
     *         a object that wrap query parametrs
     * @return the {@code Collection} that contains reded data
     * @throws ReaderOperationException
     *         if the reading file is failed
     */
    Collection<R> fetchLogData(Q query) throws ReaderOperationException;
}
