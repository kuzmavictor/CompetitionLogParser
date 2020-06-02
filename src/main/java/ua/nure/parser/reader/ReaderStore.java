package ua.nure.parser.reader;

import ua.nure.parser.model.TypeReader;

import java.util.Optional;

/**
 * A simple reader store.
 * <p>Allows to choose a {@link Reader} implementation.
 */
public final class ReaderStore {

    /**
     * Prevents instantiation.
     */
    private ReaderStore() {

    }

    /**
     * Obtains the {@link Reader} implementation.
     *
     * @param typeReader
     *         a type of {@link Reader}
     */
    public static Optional<Reader> getReader(TypeReader typeReader) {

        if (typeReader == TypeReader.RESOURCE_READER) {

            return Optional.of(new ResourceReader());
        }

        if (typeReader == TypeReader.FILE_READER) {
            return Optional.of(new FileReaderImpl());
        }

        return Optional.empty();
    }
}
