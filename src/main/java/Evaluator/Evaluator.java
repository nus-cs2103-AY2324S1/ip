package Evaluator;

import Logger.Logger;
import Parser.Commands;
import Parser.QueryObject;
import TaskList.TaskList;

import java.util.ArrayList;
import java.util.HashMap;

@FunctionalInterface
interface ThreeParameterFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class Evaluator {
    private TaskList taskList;
    private Logger logger;

    private static HashMap<Commands, ThreeParameterFunction<TaskList, ArrayList<String>, Logger, Boolean>> MAPPER =
            new HashMap<>();
    static
    {
        MAPPER.put(Commands.BYE, (t, a, l) -> new ByeStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.LIST, (t, a, l) -> new ListStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.MARK, (t, a, l) -> new MarkStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.UNMARK, (t, a, l) -> new UnmarkStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.TODO, (t, a, l) -> new ToDoStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.EVENT, (t, a, l) -> new EventStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.DEADLINE, (t, a, l) -> new DeadlineStrategy(t, a).evaluate(l));
        MAPPER.put(Commands.DELETE, (t, a, l) -> new DeleteStrategy(t, a).evaluate(l));
    }

    public Evaluator(Logger logger) {
        this.taskList = new TaskList();
        this.logger = logger;
    }

    public boolean evaluate(QueryObject queryObject) {
        Commands command = queryObject.getCommandType();
        ArrayList<String> arguments = queryObject.getArgs();
        return MAPPER.get(command).apply(taskList, arguments, logger);
    }
}
