package ua.nure.parser.process;

import com.google.common.annotations.VisibleForTesting;
import ua.nure.parser.model.LogData;
import ua.nure.parser.model.tiny.QuantityParameter;
import ua.nure.parser.model.ReadDataCompetition;
import ua.nure.parser.model.UserData;
import ua.nure.parser.model.CompetitionHandlerParameters;
import ua.nure.parser.process.handler.ProcessHandler;
import ua.nure.parser.reader.Reader;
import ua.nure.parser.reader.ReaderOperationException;
import ua.nure.parser.model.tiny.ReaderQuery;

import java.util.Collection;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A process to obtain information about competition from logs.
 */
public final class CompetitionUsersProcess extends ParserProcess<UserData, ReadDataCompetition> {

    private static final String ERROR_MESSAGE = "Cannot fetch data about competitions";

    private final ProcessHandler processHandler;
    private final Reader reader;

    /**
     * Creates a {@code CompetitionUsersProcess} instance.
     *
     * @param handler
     *         an instance of {@link ProcessHandler}
     * @param reader
     *         an instance of {@link Reader}
     */
    public CompetitionUsersProcess(ProcessHandler handler, Reader reader) {
        checkNotNull(handler);
        checkNotNull(reader);

        processHandler = handler;
        this.reader = reader;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Collection<UserData> process(ReadDataCompetition query) throws ProcessException {

        Collection<LogData> startLogData = getLogData(query.getStartLogLocation());
        Collection<LogData> endLogData = getLogData(query.getEndLogLocation());
        QuantityParameter quantityParameter = query.getQuantityParameter();

        CompetitionHandlerParameters parameters =
                new CompetitionHandlerParameters(startLogData,
                                                 endLogData,
                                                 quantityParameter);

        Optional<Collection<UserData>> userDataList = processHandler.doHandle(parameters);

        if (!userDataList.isPresent()) {
            throw new ProcessException(ERROR_MESSAGE);
        }

        return userDataList.get();
    }

    @VisibleForTesting
    protected Collection<LogData> getLogData(String path) {
        ReaderQuery query = new ReaderQuery(path);
        Collection<LogData> logData = null;
        try {
            logData = reader.fetchLogData(query);
        } catch (ReaderOperationException ex) {
            ex.printStackTrace();
            throw new ProcessException(ERROR_MESSAGE);
        }
        return logData;
    }
}
