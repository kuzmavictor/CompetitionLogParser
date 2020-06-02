package ua.nure.parser.process;

import ua.nure.parser.Query;
import ua.nure.parser.Result;
import ua.nure.parser.process.handler.ProcessHandler;

import java.util.Collection;

/**
 * Base class to all processes in this application.
 * <p> The process is a bounded context of business logic. Processes use a {@link
 * ProcessHandler} which helps to separate aspects of one business rule.
 *
 * @param <R>
 *         a wrapper of result process work
 * @param <Q>
 *         a query that contain necessary information to work process.
 */
public abstract class ParserProcess<R extends Result, Q extends Query> {

    /**
     * Processes a necessary {@link Query}.
     *
     * @param query
     *         a query to be processed
     * @return the process execution reuslt
     * @throws ProcessException
     *         if the process is failed
     */
    public abstract Collection<R> process(Q query) throws ProcessException;
}
