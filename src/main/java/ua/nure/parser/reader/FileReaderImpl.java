package ua.nure.parser.reader;

import ua.nure.parser.LogData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileReaderImpl implements Reader<LogData, ReaderQuery> {

    @Override
    public Iterable<LogData> fetchLogData(ReaderQuery query) throws ReaderOperationException {
        String path = query.getFilePath();
        List<LogData> logData;

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            logData = stream
                    .map(t -> new LogData(t.substring(4, 16), t.substring(20, 32)))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ReaderOperationException("The process of reading the log file was failed.");
        }

        return logData;
    }
}
