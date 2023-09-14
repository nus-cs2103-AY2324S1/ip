package kevin;

import java.util.ArrayList;
import java.util.Scanner;

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
    public static void main(String[] args) {
        Logger logger = new Logger();
        Parser parser = new Parser();
        FileParser fileParser = new FileParser();
        FileStorage fileStorage = new FileStorage();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        Evaluator evaluator = new Evaluator(logger, fileStorage, taskList);

        logger.hello();

        try {
            fileStorage.initialize();
            ArrayList<String> unparsedTasks = fileStorage.getTasksFromFile();
            for (String s : unparsedTasks) {
                evaluator.evaluate(fileParser.parseLine(s), true);
            }
        } catch (KevinException ke) {
            logger.log(ke.getMessage());
        }

        while (true) {
            try {
                QueryObject queryObject = parser.prepareArguments(scanner.nextLine());
                if (!evaluator.evaluate(queryObject, false)) {
                    break;
                }
            } catch (KevinException ke) {
                logger.log(ke.getMessage());
            }
        }
        logger.bye();
    }
}



