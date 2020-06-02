package ua.nure.parser.model;

import jdk.nashorn.internal.ir.annotations.Immutable;
import ua.nure.parser.model.LogData;
import ua.nure.parser.model.tiny.QuantityParameter;
import ua.nure.parser.Query;
import ua.nure.parser.process.handler.ProcessHandler;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parameters that is necessary to work a {@link ProcessHandler}.
 */

public final class CompetitionHandlerParameters implements Query {

    private final Collection<LogData> startLogDataList;
    private final Collection<LogData> endLogDataList;
    private final QuantityParameter quantityParameter;

    /**
     * Creates a {@code CompetitionHandlerParameters} parameters.
     *
     * @param startLogDataList
     *         a time of start competition
     * @param endLogDataList
     *         a time of end competition
     * @param quantityParameter
     *         a quantity item to show
     */
    public CompetitionHandlerParameters(Collection<LogData> startLogDataList,
                                        Collection<LogData> endLogDataList,
                                        QuantityParameter quantityParameter) {

        checkNotNull(startLogDataList);
        checkNotNull(endLogDataList);
        checkNotNull(quantityParameter);

        this.startLogDataList = startLogDataList;
        this.endLogDataList = endLogDataList;
        this.quantityParameter = quantityParameter;
    }

    public Collection<LogData> getStartLogDataList() {
        return startLogDataList;
    }

    public Collection<LogData> getEndLogDataList() {
        return endLogDataList;
    }

    public QuantityParameter getQuantityParameter() {
        return quantityParameter;
    }

    @Override
    public String toString() {
        return "CompetitionHandlerParameters{" +
                "startLogDataList=" + startLogDataList +
                ", endLogDataList=" + endLogDataList +
                ", quantityParameter=" + quantityParameter +
                '}';
    }
}
