package ua.nure.parser.process.handler;

import com.google.common.base.Function;
import ua.nure.parser.Predicates;
import ua.nure.parser.model.CompetitionHandlerParameters;
import ua.nure.parser.model.LogData;
import ua.nure.parser.model.tiny.SpentTime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A competition handler context. Provides the necessary environment
 * that is using by {@link CompetitionUsersHandler} in runtime.
 *
 * @apiNote After creating instance of this class you must follow the sequence of actions:
 *         <ol>
 *         <li>1.configuration of the context using the method:
 *         {@link #addStartLogToContext(CompetitionHandlerParameters)};
 *          <li>2.context configuration with promotion method:
 *          {@link #addFinishLogToContext(CompetitionHandlerParameters)}
 *          <li>3.configuration of the context using the method:
 *          {@link #addDurationToContext(CompetitionHandlerParameters)}
 *         <li>4.getting data from context for future work.
 *         <ol/>
 */

public final class CompetitionHandlerContext {

    private Map<String, String> startLogData;
    private Map<String, String> endLogData;
    private Map<String, SpentTime> duration;

    /**
     * Creates a {@code}
     */
    public CompetitionHandlerContext() {
        this.startLogData = new HashMap<>();
        this.endLogData = new HashMap<>();
        this.duration = new HashMap<>();
    }

    /**
     * Obtains all data from the log about start competition.
     */
    public Map<String, String> getStartLogData() {
        return startLogData;
    }

    /**
     * Obtains all data from the log about finish competition.
     */
    public Map<String, String> getEndLogData() {
        return endLogData;
    }

    /**
     * Obtains a time that the users spent on the competition.
     */
    public Map<String, SpentTime> getDuration() {
        return duration;
    }

    /**
     * Adds the data about start the competition (from the start log file).
     *
     * @param parameters
     *         a necessary parameter with data.
     * @see Predicates#distinctByFirstOccurrence(Function)
     */
    public void addStartLogToContext(CompetitionHandlerParameters parameters) {
        this.startLogData = parameters.getStartLogDataList()
                                      .stream()
                                      .filter(Predicates.distinctByFirstOccurrence(
                                              LogData::getUserId))
                                      .collect(Collectors.toMap(LogData::getUserId,
                                                                LogData::getCompetitionDate));

    }

    /**
     * Adds the data about ending the competition (from the finish log file).
     *
     * @param parameters
     *         a necessary parameter with data.
     * @see Predicates#distinctByLastOccurrence(Function)
     */
    public void addFinishLogToContext(CompetitionHandlerParameters parameters) {

        this.endLogData = parameters.getEndLogDataList()
                                    .stream()
                                    .filter(Predicates.distinctByLastOccurrence(LogData::getUserId))
                                    .collect(Collectors.toMap(LogData::getUserId,
                                                              LogData::getCompetitionDate));

    }

    /**
     * Adds the duration of the user competition.
     *
     * @param parameters
     *         a necessary parameter with data.
     */
    public void addDurationToContext(CompetitionHandlerParameters parameters) {

        this.duration = this.endLogData.entrySet()
                                       .stream()
                                       .collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
                                           Duration duration = getSpentTime(entry.getKey());
                                           SpentTime spentTime = new SpentTime(duration);
                                           return spentTime;
                                       }));

    }

    private Duration getSpentTime(String keyValue) {
        checkNotNull(keyValue);
        long start = 0;
        long finish = 0;

        if (endLogData.get(keyValue) != null) {
            finish = Long.parseLong(endLogData.get(keyValue));
        }

        if (startLogData.get(keyValue) != null) {
             start = Long.parseLong(startLogData.get(keyValue));
        }

        LocalDateTime startTime = getDLocalDateTime(start);
        LocalDateTime finishTime = getDLocalDateTime(finish);
        return Duration.between(startTime, finishTime);
    }

    private LocalDateTime getDLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault()
                                                                                .toZoneId());
    }
}
