package commands;

import duke.CustomDate;
import storage.DataFile;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class DeadlineCommand extends Command{
    private final ArrayList<String> texts;
    private Task task;
    private int size;

    /**
     * DeadlineCommand constructor that takes in an ArrayList.
     * @param texts ArrayList of texts needed for deadline.
     */
    public DeadlineCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    /**
     * Executes the deadline command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks , DataFile dF) {
        CustomDate cD = new CustomDate();
        Task dl = new Deadline(texts.get(0), cD.strToDateTime(texts.get(1)));
        tasks.addTask(dl);
        task = dl;
        size = tasks.getSize();
        System.out.println(this);
        try {
            dF.writeToFile(dl);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the string representation of deadline command.
     * @return String representation of deadline command.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task +
                "\nNow you have " + size + " tasks in the list.";
    }
}
