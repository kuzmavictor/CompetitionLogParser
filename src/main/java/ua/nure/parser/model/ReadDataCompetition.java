package ua.nure.parser.model;

import jdk.nashorn.internal.ir.annotations.Immutable;
import ua.nure.parser.Query;
import ua.nure.parser.model.tiny.QuantityParameter;
import ua.nure.parser.model.TypeReader;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Query to obtain the data about competition.
 */
@Immutable
public final class ReadDataCompetition implements Query {

    private final String startLogLocation;
    private final String endLogLocation;
    private final TypeReader typeReader;
    private final QuantityParameter quantityParameter;

    /**
     * @param startLogLocation
     * @param endLogLocation
     * @param typeReader
     * @param quantityParameter
     */
    public ReadDataCompetition(String startLogLocation, String endLogLocation,
                               TypeReader typeReader, QuantityParameter quantityParameter) {
        checkNotNull(startLogLocation);
        checkNotNull(endLogLocation);
        checkNotNull(typeReader);
        checkNotNull(quantityParameter);

        this.startLogLocation = startLogLocation;
        this.endLogLocation = endLogLocation;
        this.typeReader = typeReader;
        this.quantityParameter = quantityParameter;

    }

    public String getStartLogLocation() {
        return startLogLocation;
    }

    public String getEndLogLocation() {
        return endLogLocation;
    }

    public TypeReader getTypeReader() {
        return typeReader;
    }

    public QuantityParameter getQuantityParameter() {
        return quantityParameter;
    }
}
