package commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.CustomDate;
import storage.DataFile;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;


/**
 * Represents a type of command that can be read by the chatbot.
 */
public class DeadlineCommand extends Command {

    private final ArrayList<String> texts;

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
    public String execute(TaskList tasks , DataFile dF) {
        CustomDate cD = new CustomDate();
        Task dL = new Deadline(texts.get(0), cD.strToDateTime(texts.get(1)));
        tasks.addTask(dL);
        try {
            dF.writeToFile(dL);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert !texts.isEmpty();
        return cmdToString(dL.toString(), tasks.getSize());
    }

    /**
     * Returns the string representation of deadline command.
     * @return String representation of deadline command.
     */

    public String cmdToString(String task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }
}
