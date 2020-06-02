package ua.nure.parser.process.handler;

import ua.nure.parser.Query;
import ua.nure.parser.Result;

import java.util.Collection;
import java.util.Optional;

/**
 * A base for processes that can be executed in `UsersStorage` application.
 * <p> It allows you to correctly divide business logic into independent aspects.
 * Each a {@code ProcessHandler} is a separate aspect of business logic.
 *
 * @param <R>
 *         a type of process execution result
 * @param <Q>
 *         a type of process parameter
 */
public interface ProcessHandler<R extends Result, Q extends Query> {

    /**
     * Handles the parameters using necessary business logic.
     *
     * @param parameter
     *         a parameter to be handled
     * @return the process execution result
     */
    Optional<Collection<R>> doHandle(Q parameter);
}
