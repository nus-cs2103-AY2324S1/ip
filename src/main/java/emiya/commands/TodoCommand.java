package emiya.commands;

import emiya.emiyaexception.EmptyTodoException;
import emiya.storage.Storage;
import emiya.task.TaskList;
import emiya.task.ToDo;
import emiya.ui.Ui;

/**
 * A class that represents the Todo command.
 */
public class TodoCommand {

    /**
     * Creates a Todo task object and adds the task into the task list.
     *
     * @param taskDetails A String that contains the input from the user.
     * @param taskList The TaskList instance associated with the task bot.
     * @param storage The Storage instance associated with the task bot.
     * @param ui The Ui instance associated with the task bot.
     * @param fileName The name of the file that the newly updated task list will be written into.
     * @param dirName The name of the directory that the newly updated task list will be written into.
     * @return A String that indicates that the Todo task has been successfully added
     *     into the task list.
     * @throws EmptyTodoException An exception that is thrown when the user uses a todo command
     *     but does not input in task details.
     */
    public static String createTodo(String taskDetails, TaskList taskList, Storage storage, Ui ui,
                                    String fileName, String dirName) throws EmptyTodoException {
        if (taskDetails.equals("")) {
            throw new EmptyTodoException();
        }
        ToDo todo = new ToDo(false, taskDetails);
        taskList.add(todo);
        storage.writeToFileFromTaskList(taskList, fileName, dirName);
        if (taskList.size() == 1) {
            return ui.addedSingularMessage(todo, taskList);
        } else {
            return ui.addedPluralMessage(todo, taskList);
        }
    }
}
