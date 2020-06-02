package ua.nure.parser.model;

import ua.nure.parser.Result;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A data that read from the log file.
 */
public final class LogData implements Result {

    private final String userId;
    private final String competitionDate;

    /**
     * Creates a {@code LogData} instance.
     *
     * @param userId
     *         a unique user identifier
     * @param competitionDate
     *         a time of competition
     */
    public LogData(String userId, String competitionDate) {
        checkNotNull(userId);
        checkNotNull(competitionDate);

        this.userId = userId;
        this.competitionDate = competitionDate;
    }

    /**
     * Obtains the user unique identifier.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Obtains the time of competition
     */
    public String getCompetitionDate() {
        return competitionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogData logData = (LogData) o;

        return userId != null ? userId.equals(logData.userId) : logData.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "userId='" + userId + '\'' +
                ", competitionDate='" + competitionDate + '\'' +
                '}';
    }
}
