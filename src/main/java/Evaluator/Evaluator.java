package Evaluator;

import Logger.Logger;
import Parser.Commands;
import Parser.QueryObject;
import TaskList.TaskList;

import java.util.ArrayList;

public class Evaluator {
    private TaskList taskList;
    private Logger logger;

    public Evaluator(Logger logger) {
        this.taskList = new TaskList();
        this.logger = logger;
    }
    public boolean evaluate(QueryObject queryObject) {
        Commands command = queryObject.getCommandType();
        ArrayList<String> arguments = queryObject.getArgs();

        switch (command) {
            case BYE:
                new ByeStrategy(taskList, arguments).evaluate(logger);
                return false;
            case LIST:
                new ListStrategy(taskList, arguments).evaluate(logger);
                return true;
            case MARK:
                new MarkStrategy(taskList, arguments).evaluate(logger);
                return true;
            case UNMARK:
                new UnmarkStrategy(taskList, arguments).evaluate(logger);
                return true;
            case TODO:
                new ToDoStrategy(taskList, arguments).evaluate(logger);
                return true;
            case DEADLINE:
                new DeadlineStrategy(taskList, arguments).evaluate(logger);
                return true;
            case EVENT:
                new EventStrategy(taskList, arguments).evaluate(logger);
                return true;
            case DELETE:
                new DeleteStrategy(taskList, arguments).evaluate(logger);
                return true;
            default:
                return false;
        }
    }
}
