import Evaluator.Evaluator;
import Parser.Parser;
import Parser.FileParser;
import Parser.QueryObject;
import Ui.Logger;
import Exception.KevinException;
import Storage.FileStorage;
import TaskList.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

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



