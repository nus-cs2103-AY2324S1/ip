package kevin.evaluator;

import java.util.ArrayList;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.tasklist.TaskList;
import kevin.tasklist.ToDo;
import kevin.ui.Logger;

/**
 * A class responsible for the logic for TODO command.
 */
public class ToDoStrategy extends BaseStrategy {
    /**
     * Constructor to initialize ToDoStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public ToDoStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the TODO command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation.
     * @throws KevinException On the detection of errors.
     */
    @Override
    public String evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.parseBoolean(this.arguments.get(0));
        String toDoName = this.arguments.get(1);

        ToDo newToDo = taskList.addToDo(isDone, toDoName);

        if (!isInFile) {
            fileStorage.addToDo(newToDo);
            return logger.log(String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list.",
                    newToDo, this.taskList.size()));
        }

        return "";
    }
}
