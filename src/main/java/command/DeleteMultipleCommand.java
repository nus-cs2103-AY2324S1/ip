package command;

import java.util.Arrays;
import java.util.stream.Stream;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Task;
import ui.Ui;



/**
 * The class that will execute the Delete multipleTask command.
 * This class extends from the Command class.
 */
public class DeleteMultipleCommand extends Command {
    private final String indexes;
    /**
     * Constructs the class.
     *
     * @param indexes The index used to determine the class.
     */
    public DeleteMultipleCommand(String indexes) {
        this.indexes = indexes;
    }

    /**
     * Executes the Command of deleting multiple task from the TaskList.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        String[] individual = indexes.split(",");
        TaskList deletedList = new TaskList();
        int[] values = Stream.of(individual)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int[] reversedValues = new int[values.length];
        System.out.println(Arrays.toString(reversedValues));
        for (int i = 0; i < values.length; i++) {
            reversedValues[i] = values[values.length - 1];
        }
        for (int reversedValue : reversedValues) {
            Task task = taskList.deleteTask(reversedValue - 1);
            deletedList.add(task);
        }
        fileStorage.write(taskList);
        return ui.showNewList(deletedList, taskList, "del");
    }
}
