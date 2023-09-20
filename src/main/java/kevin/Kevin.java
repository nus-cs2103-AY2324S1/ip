package kevin;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kevin.evaluator.Evaluator;
import kevin.exception.KevinException;
import kevin.parser.FileParser;
import kevin.parser.Parser;
import kevin.parser.QueryObject;
import kevin.storage.FileStorage;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;

/**
 * Kevin class, a task list chatbot.
 */
public class Kevin {
    protected Logger logger;
    protected Parser parser;
    protected FileParser fileParser;
    protected FileStorage fileStorage;
    protected TaskList taskList;
    protected Evaluator evaluator;

    /**
     * Constructor for Kevin.
     */
    public Kevin() {
        this.logger = new Logger();
        this.parser = new Parser();
        this.fileParser = new FileParser();
        this.fileStorage = new FileStorage();
        this.taskList = new TaskList();
        this.evaluator = new Evaluator(logger, fileStorage, taskList);
        try {
            this.fileStorage.initialize();
            ArrayList<String> unparsedTasks = this.fileStorage.getTasksFromFile();
            for (String s : unparsedTasks) {
                this.evaluator.evaluate(fileParser.parseLine(s), true);
            }
        } catch (KevinException ke) {
            this.logger.log(ke.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            QueryObject queryObject = parser.prepareArguments(input);
            String output = evaluator.evaluate(queryObject, false);
            if (output.equals("bye")) {
                Timer timer = new Timer();
                int twoSeconds = 2 * 1000;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        javafx.application.Platform.exit();
                        System.exit(0);
                    }
                }, twoSeconds);
                return logger.bye();
            }
            return output;
        } catch (KevinException ke) {
            return logger.log(ke.getMessage());
        }
    }
}



