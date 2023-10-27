package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;
import yong.tasks.ToDo;

import yong.exception.DukeException;

/**
 * Represents a command for creating a ToDo task.
 */
public class ToDoCommand extends Command {

    private String inputString;

    /**
     * Constructs a ToDoCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param inp      The input line from the command-line interface.
     */
    public ToDoCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inputString = inp;
    }

    /**
     * Initializes a new ToDo task and adds it to the taskList.
     *
     * @return A message indicating that the task has been added.
     * @throws DukeException If the input is invalid.
     */
    @Override
    public String execute() {
        try {
            String[] typeDescription = inputString.split(" ", 2);
            String type = typeDescription[0];
            String description = typeDescription[1];

            Task newTask = new ToDo(description);

            taskList.add(newTask);

            outputString = "Okay! Task added \n" + newTask;

            return outputString;
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a ToDo task!"
                    + " An example format will be \"ToDo Borrow book\"");
        }
    }
}
