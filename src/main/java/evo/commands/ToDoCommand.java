package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.tasks.ToDo;
import evo.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The ToDoCommand class represents a command to add a "To-Do" task to a TaskList.
 * When executed, it creates a new "To-Do" task with the specified description and adds it to the TaskList.
 */
public class ToDoCommand extends Command {

    /**
     * The array of action type and task description extracted from the user command.
     */
    protected String[] actionType;

    /**
     * Constructs a ToDoCommand with the specified action type and task description.
     *
     * @param actionType The array containing the action type and task description.
     */
    public ToDoCommand(String[] actionType) {
        this.actionType = actionType;
    }

    /**
     * Executes the ToDoCommand, creating a "To-Do" task based on user input and adding it to the tasksList.
     *
     * @param tasksList The tasksList to which the "To-Do" task will be added.
     * @param ui The Ui component for user interface interactions.
     * @param storage The Storage component for data storage operations.
     * @return A confirmation message indicating the task has been added or an error message if an exception occurs.
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        ArrayList<String> responses = new ArrayList<>();
        try {
            String taskDescription = "";
            for (int i = 1; i < this.actionType.length; i++) {
                if (i == this.actionType.length - 1) {
                    taskDescription += this.actionType[i];
                } else {
                    taskDescription += this.actionType[i] + " ";
                }
            }

            ToDo toDo = new ToDo(taskDescription);
            tasksList.addTask(toDo);
            storage.saveTasksInFile(tasksList);
            responses.add(ui.showText("Got it. I've added this task:\n"));
            responses.add(ui.showText("  " + toDo.toString() + "\n"));
            if (tasksList.tasksListLength() <= 1) {
                responses.add(ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list."));
            } else {
                responses.add(ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list."));
            }
        } catch (IOException e) {
            responses.add(ui.showText("Something went wrong: " + e.getMessage()));
        }
        return concatenateString(responses);
    }

    /**
     * Concatenates a list of response strings into a single string.
     *
     * @param responses The list of response strings to concatenate.
     * @return The concatenated response string.
     */
    public String concatenateString(ArrayList<String> responses) {
        String textToRespond = "";
        for (int i = 0; i < responses.size(); i++) {
            textToRespond += responses.get(i);
        }
        return textToRespond;
    }
}
