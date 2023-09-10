package commands;

import java.io.IOException;
import java.util.ArrayList;

import storage.DataFile;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;


/**
 * Represents a type of command that can be read by the chatbot.
 */
public class TodoCommand extends Command {

    private final ArrayList<String> texts;

    /**
     * TodoCommand constructor that takes in an ArrayList.
     * @param texts ArrayList of texts needed for todo.
     */
    public TodoCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    /**
     * Executes the todo command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public String execute(TaskList tasks, DataFile dF) {
        Task todo = new Todo(texts.get(0));
        tasks.addTask(todo);
        try {
            dF.writeToFile(todo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert !texts.isEmpty();
        return cmdToString(todo.toString(), tasks.getSize());
    }

    /**
     * Returns the string representation of todo command.
     * @return String representation of todo command.
     */
    public String cmdToString(String task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }
}
