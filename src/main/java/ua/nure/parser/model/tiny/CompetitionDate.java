package ua.nure.parser.model.tiny;

import com.google.errorprone.annotations.Immutable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A wrapper of date competition (start or end).
 */

@Immutable
public final class CompetitionDate {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    private final LocalDateTime dateTime;

    /**
     * Creates a {@code DateCompetition} instance.
     *
     * @param dateTime
     *         a time of competition
     */
    public CompetitionDate(String dateTime){
        checkNotNull(dateTime);

        this.dateTime = LocalDateTime.parse(dateTime, dateFormatter);
    }

    /**
     * The static factory method to create a {@code CompetitionDate} form timestamp.
     *
     * @param longEpochTime
     *         a timestamp
     */
    public static CompetitionDate fromLong(long longEpochTime){
        String date = String.valueOf(longEpochTime);
        return new CompetitionDate(date);
    }

    public String getFormatedDate() {
        return dateTime.toString();
    }

    /**
     * Obtains the time of competition
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
