package ua.nure.parser.model.tiny;

import com.google.errorprone.annotations.Immutable;

import java.time.Duration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A time that the user spent on the competition.
 */
@Immutable
public final class SpentTime {

    private final Duration period;

    /**
     * Creates a {@code SpentTime} instance.
     *
     * @param period
     *         a period that spent on competition
     * @throws IllegalArgumentException
     *         if the value is negative
     */
    public SpentTime(Duration period) {
        checkNotNull(period);
        this.period = period;
    }

    /**
     * Obtains the period of spent time on competition.
     */
    public Duration getPeriod() {
        return period;
    }
}