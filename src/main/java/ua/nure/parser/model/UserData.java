package ua.nure.parser.model;

import com.google.errorprone.annotations.Immutable;
import ua.nure.parser.model.tiny.CompetitionDate;
import ua.nure.parser.Result;
import ua.nure.parser.model.tiny.SpentTime;
import ua.nure.parser.model.tiny.UserId;

/**
 * All data about user competition.
 */
@Immutable
public final class UserData implements Result {

    private final UserId userId;
    private final CompetitionDate startDate;
    private final CompetitionDate endDate;
    private final SpentTime spentTime;

    /**
     * Creates a {@code UserData} instance.
     *
     * @param builder
     *         a builder of {@code UserData}
     * @see UserDataBuilder
     */
    private UserData(UserDataBuilder builder) {
        this.userId = builder.userId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.spentTime = builder.spentTime;
    }

    /**
     * Obtains the user identifier
     */
    public UserId getUserId() {
        return userId;
    }

    /**
     * Obtains the date of start competition.
     */
    public CompetitionDate getStartDate() {
        return startDate;
    }

    /**
     * Obtains the date of finish competition.
     */
    public CompetitionDate getEndDate() {
        return endDate;
    }

    /**
     * Obtains a time that the user spent for competition.
     */
    public SpentTime getSpentTime() {
        return spentTime;
    }

    public String getFormattedData() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ");
        sb.append(userId.getId());
        sb.append(" startDate: ");
        sb.append(startDate.getFormatedDate());
        sb.append(" endDate :");
        sb.append(endDate.getFormatedDate());
        sb.append(" spentTime: ");
        sb.append(spentTime.getPeriod().getSeconds());
        sb.append(" seconds; ");
        return sb.toString();
    }

    /**
     * A builder for the {@link UserData} instances.
     */
    public static class UserDataBuilder {

        private UserId userId;
        private CompetitionDate startDate;
        private CompetitionDate endDate;
        private SpentTime spentTime;

        public UserDataBuilder withUseId(UserId useId) {
            this.userId = useId;
            return this;
        }

        public UserDataBuilder withStartCompetitionDate(CompetitionDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public UserDataBuilder withEndCompetitionDate(CompetitionDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public UserDataBuilder withSpentTime(SpentTime spentTime) {
            this.spentTime = spentTime;
            return this;
        }

        public UserData build() {
            return new UserData(this);
        }
    }
}
