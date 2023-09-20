package bellcurvegod;

import java.io.IOException;

import bellcurvegod.exception.BellCurveGodException;
import bellcurvegod.parser.Parser;
import bellcurvegod.storage.Storage;

/**
 * Main class.
 */
public class BellCurveGod {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";

    private final String filePath;

    public BellCurveGod(String path) {
        filePath = path;
        Storage.loadTasks(filePath);
    }

    public BellCurveGod()  {
        this(DATA_FILE_PATH);
    }

    public String getResponse(String input) {
//        try {
//            Storage.loadTasks(filePath);
//        } catch (IOException e) {
//            return e.getMessage();
//        }

        try {
            return Parser.parse(input);
        } catch (BellCurveGodException e) {
            return e.getMessage();
        }
    }
}
