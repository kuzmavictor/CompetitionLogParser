package ua.nure.parser.model.tiny;

import jdk.nashorn.internal.ir.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class QuantityParameter {

    private final Integer outputQuantity;

    public QuantityParameter(Integer quantity) {
        checkNotNull(quantity);
        outputQuantity = quantity;
    }

    public Integer getOutputQuantity() {
        return outputQuantity;
    }

    @Override
    public String toString() {
        return "QuantityParameter{" +
                "outputQuantity=" + outputQuantity +
                '}';
    }
}
