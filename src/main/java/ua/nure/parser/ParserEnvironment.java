package ua.nure.parser;

import ua.nure.parser.process.CompetitionUsersProcess;
import ua.nure.parser.process.ParserProcess;
import ua.nure.parser.process.handler.CompetitionUsersHandler;
import ua.nure.parser.process.handler.ProcessHandler;
import ua.nure.parser.reader.FileReaderImpl;
import ua.nure.parser.reader.Reader;
import ua.nure.parser.reader.ResourceReader;

/**
 * An environment of the current parser instance.
 *
 * @implNote Its instance is created once per JVM.
 */
public final class ParserEnvironment {

    private static final ParserEnvironment PARSER_ENVIRONMENT = new ParserEnvironment();

    private final Reader resourceReader;
    private final Reader fileReader;
    private final ParserProcess parserProcess;
    private final ProcessHandler processHandler;

    public ParserEnvironment() {
        this.fileReader = new FileReaderImpl();
        this.resourceReader = new ResourceReader();
        this.processHandler = new CompetitionUsersHandler();
        this.parserProcess = new CompetitionUsersProcess(processHandler, resourceReader);
    }

    public static ParserEnvironment instance() {
        return PARSER_ENVIRONMENT;
    }

    public Reader getResourceReader() {
        return resourceReader;
    }

    public Reader getFileReader() {
        return fileReader;
    }

    public ParserProcess getParserProcess() {
        return parserProcess;
    }

    public ProcessHandler getProcessHandler() {
        return processHandler;
    }
}
