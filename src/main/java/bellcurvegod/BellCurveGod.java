package bellcurvegod;

import bellcurvegod.parser.Parser;
import bellcurvegod.storage.Storage;
import bellcurvegod.ui.Ui;

import java.io.IOException;

public class BellCurveGod {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    public static void main(String[] args) {
        try {
            Storage.loadTasks(DATA_FILE_PATH);
        } catch (IOException e) {
            System.out.println(e);
        }

        Ui.greet();
        Parser.parse();
    }
}
