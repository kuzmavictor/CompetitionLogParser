package ua.nure.parser.process.handler;

import ua.nure.parser.model.CompetitionHandlerParameters;
import ua.nure.parser.model.tiny.CompetitionDate;
import ua.nure.parser.model.LogData;
import ua.nure.parser.model.UserData;
import ua.nure.parser.model.tiny.SpentTime;
import ua.nure.parser.model.tiny.UserId;

import java.util.Collection;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public final class CompetitionUsersHandler
        implements ProcessHandler<UserData, CompetitionHandlerParameters> {

    private CompetitionHandlerContext context;

    /**
     * {@inheritDoc}
     *
     * @param parameter
     *         a parameter to be handled
     * @return
     */
    @Override
    public Optional<Collection<UserData>> doHandle(CompetitionHandlerParameters parameter) {

        context = new CompetitionHandlerContext();
        context.addStartLogToContext(parameter);
        context.addFinishLogToContext(parameter);
        context.addDurationToContext(parameter);

        Collection<UserData> userDataList =
                context.getEndLogData()
                       .entrySet()
                       .stream()
                       .map(el -> {
                           String id = el.getKey();
                           UserId userIdWrapper = new UserId(id);

                           CompetitionDate competitionStartDate = CompetitionDate.fromLong(
                                   Long.parseLong(el.getValue()));

                           CompetitionDate competitionEndDate = CompetitionDate.fromLong(
                                   Long.parseLong(el.getValue()));

                           SpentTime spentTime = context.getDuration()
                                                        .get(el.getKey());

                           UserData userData =
                                   new UserData.UserDataBuilder()
                                           .withUseId(userIdWrapper)
                                           .withStartCompetitionDate(competitionStartDate)
                                           .withEndCompetitionDate(competitionEndDate)
                                           .withSpentTime(spentTime)
                                           .build();

                           return userData;
                       })
                       .sorted(Comparator.comparing(userData -> userData.getSpentTime()
                                                                        .getPeriod()))
                       .limit(parameter.getQuantityParameter()
                                       .getOutputQuantity())
                       .collect(Collectors.toList());

        return Optional.of(userDataList);
    }
}
