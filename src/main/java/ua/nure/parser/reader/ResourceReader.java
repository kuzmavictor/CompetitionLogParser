package ua.nure.parser.reader;

import ua.nure.parser.LogData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public final class ResourceReader implements Reader<LogData, ReaderQuery> {

    private static final String ERROR_MESSAGE = "The process of reading the log file was failed.";

    @Override
    public Iterable<LogData> fetchLogData(ReaderQuery query) throws ReaderOperationException {
        //TODO:2020-06-01:kuzma.victor: Add better implementation.
        String path = query.getFilePath();
        Stream<String> stream = null;
        InputStream inputStream = null;
        List<LogData> logData = new ArrayList<>();

        try {

            inputStream = ResourceReader.class.getClassLoader()
                                              .getResourceAsStream(path);
            stream = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();

            logData = stream.map(line -> new LogData(line.substring(4, 16), line.substring(20, 32)))
                            .collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ReaderOperationException(ERROR_MESSAGE);

        } finally {
            if (stream != null) {
                stream.close();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new ReaderOperationException(ERROR_MESSAGE);
                }
            }
        }

        return logData;
    }
}

