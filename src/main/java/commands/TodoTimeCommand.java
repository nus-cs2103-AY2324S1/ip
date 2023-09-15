package commands;

import java.io.IOException;
import java.util.ArrayList;

import storage.DataFile;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTime;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class TodoTimeCommand extends Command {

    private final ArrayList<String> texts;

    public TodoTimeCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    @Override
    public String execute(TaskList tasks, DataFile dF) {
        Task task = new TodoTime(texts.get(0), texts.get(1));
        tasks.addTask(task);
        try {
            dF.writeToFile(task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert !texts.isEmpty();
        return cmdToString(task.toString(), tasks.getSize());
    }

    /**
     * Returns the string representation of todoTime command.
     * @param task String representation of Task.
     * @param size Size of the taskList.
     * @return String representation of todoTime command.
     */
    public String cmdToString(String task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }
}
