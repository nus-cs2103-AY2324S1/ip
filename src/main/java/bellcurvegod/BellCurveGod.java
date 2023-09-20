package bellcurvegod;

import bellcurvegod.exception.BellCurveGodException;
import bellcurvegod.parser.Parser;
import bellcurvegod.storage.Storage;

/**
 * Main class.
 */
public class BellCurveGod {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";

    private final String filePath;

    /**
     * Creates a BellCurveGod with filePath specified.
     *
     * @param path filePath.
     */
    public BellCurveGod(String path) {
        filePath = path;
        Storage.loadTasks(filePath);
    }

    public BellCurveGod() {
        this(DATA_FILE_PATH);
    }

    public String getResponse(String input) {
        try {
            return Parser.parse(input);
        } catch (BellCurveGodException e) {
            return e.getMessage();
        }
    }
}
