package ua.nure.parser.reader;

import ua.nure.parser.Query;
import ua.nure.parser.Result;

import java.util.List;
import java.util.Optional;

public interface Reader<R extends Result, Q extends Query> {

    Iterable<R> fetchLogData(Q query) throws ReaderOperationException;

}
