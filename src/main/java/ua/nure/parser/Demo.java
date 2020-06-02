package ua.nure.parser;

import ua.nure.parser.model.ReadDataCompetition;
import ua.nure.parser.model.TypeReader;
import ua.nure.parser.model.UserData;
import ua.nure.parser.model.tiny.QuantityParameter;

import java.util.Collection;
import java.util.Optional;

public class Demo {

    private final static String START_LOG = "logs/start.txt";
    private final static String END_LOG = "logs/finish.txt";

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.igniteParser();
    }

    private void igniteParser() {
        ParserEnvironment parserEnvironment = ParserEnvironment.instance();
        QuantityParameter quantityParameter = new QuantityParameter(10);
        TypeReader typeReader = TypeReader.RESOURCE_READER;
        ReadDataCompetition readDataCompetition = new ReadDataCompetition(START_LOG, END_LOG,
                                                                          typeReader,
                                                                          quantityParameter);
        Collection<UserData> process = parserEnvironment.getParserProcess()
                                                        .process(readDataCompetition);
        process.forEach(el -> {
            System.out.println(el.getFormattedData());
        });
    }

}
