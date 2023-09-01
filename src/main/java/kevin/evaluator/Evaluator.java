package kevin.evaluator;

import kevin.ui.Logger;
import kevin.parser.Commands;
import kevin.parser.QueryObject;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.exception.KevinException;

import java.util.ArrayList;
import java.util.HashMap;

@FunctionalInterface
interface FiveParameterFunction<T, U, V, W, X, R> {
    R apply(T t, U u, V v, W w, X x) throws KevinException;
}

public class Evaluator {
    private TaskList taskList;
    private Logger logger;
    private FileStorage fileStorage;

    private static HashMap<Commands, FiveParameterFunction<TaskList, ArrayList<String>, Logger, FileStorage, Boolean, Boolean>> MAPPER =
            new HashMap<>();
    static
    {
        MAPPER.put(Commands.BYE, (t, a, l, f, i) -> new ByeStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.LIST, (t, a, l, f, i) -> new ListStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.MARK, (t, a, l, f, i) -> new MarkStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.UNMARK, (t, a, l, f, i) -> new UnmarkStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.TODO, (t, a, l, f, i) -> new ToDoStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.EVENT, (t, a, l, f, i) -> new EventStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.DEADLINE, (t, a, l, f, i) -> new DeadlineStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.DELETE, (t, a, l, f, i) -> new DeleteStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Commands.FIND, (t, a, l, f, i) -> new FindStrategy(t, a).evaluate(l, f, i));
    }

    public Evaluator(Logger logger, FileStorage fileStorage, TaskList taskList) {
        this.taskList = taskList;
        this.logger = logger;
        this.fileStorage = fileStorage;
    }

    public boolean evaluate(QueryObject queryObject, boolean isInFile) throws KevinException {
        Commands command = queryObject.getCommandType();
        ArrayList<String> arguments = queryObject.getArgs();
        return MAPPER.get(command).apply(taskList, arguments, logger, fileStorage, isInFile);
    }
}
