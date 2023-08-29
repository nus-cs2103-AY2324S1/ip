import Evaluator.Evaluator;
import TaskList.TaskList;
import Parser.*;
import Logger.*;
import Evaluator.BaseStrategy;
import Exception.KevinException;

import java.util.Scanner;

public class Kevin {
    public static void main(String[] args) {
        Logger logger = new Logger();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        Evaluator evaluator = new Evaluator(logger);
        logger.hello();
        while (true) {
            try {
                QueryObject queryObject = parser.prepareArguments(scanner.nextLine());
                if (!evaluator.evaluate(queryObject)) {
                    break;
                }
            } catch (KevinException ke) {
                logger.log(ke.getMessage());
            }
        }
        logger.bye();
    }
}



