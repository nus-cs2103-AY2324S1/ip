package kevin.evaluator;

import java.util.ArrayList;
import java.util.HashMap;

import kevin.exception.KevinException;
import kevin.parser.Command;
import kevin.parser.QueryObject;
import kevin.storage.FileStorage;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;

@FunctionalInterface
interface FiveParameterFunction<T, U, V, W, X, R> {
    R apply(T t, U u, V v, W w, X x) throws KevinException;
}

/**
 * A class that handles which function to call based on a specific command.
 */
public class Evaluator {
    private static final HashMap<Command, FiveParameterFunction<TaskList, ArrayList<String>,
            Logger, FileStorage, Boolean, String>> MAPPER =
            new HashMap<>();
    static {
        MAPPER.put(Command.BYE, (t, a, l, f, i) -> new ByeStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.LIST, (t, a, l, f, i) -> new ListStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.MARK, (t, a, l, f, i) -> new MarkStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.UNMARK, (t, a, l, f, i) -> new UnmarkStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.TODO, (t, a, l, f, i) -> new ToDoStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.EVENT, (t, a, l, f, i) -> new EventStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.DEADLINE, (t, a, l, f, i) -> new DeadlineStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.DELETE, (t, a, l, f, i) -> new DeleteStrategy(t, a).evaluate(l, f, i));
        MAPPER.put(Command.FIND, (t, a, l, f, i) -> new FindStrategy(t, a).evaluate(l, f, i));
    }
    private final TaskList taskList;
    private final Logger logger;
    private final FileStorage fileStorage;

    /**
     * Constructor to initialize Evaluator.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     */
    public Evaluator(Logger logger, FileStorage fileStorage, TaskList taskList) {
        this.taskList = taskList;
        this.logger = logger;
        this.fileStorage = fileStorage;
    }

    /**
     * @param queryObject This is the QueryObject that contains the command and the arguments.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    public String evaluate(QueryObject queryObject, boolean isInFile) throws KevinException {
        assert queryObject != null : "queryObject is not supposed to be null";

        Command command = queryObject.getCommandType();
        ArrayList<String> arguments = queryObject.getArgs();
        return MAPPER.get(command).apply(taskList, arguments, logger, fileStorage, isInFile);
    }
}
