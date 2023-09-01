package commands;

import duke.CustomDate;
import storage.DataFile;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class EventCommand extends Command {

    private final ArrayList<String> texts;
    private Task task;
    private int size;

    /**
     * EventCommand constructor that takes in an ArrayList.
     * @param texts ArrayList of texts needed for event.
     */
    public EventCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    /**
     * Executes the event command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks , DataFile dF) {
        CustomDate cD = new CustomDate();
        Task event = new Event(texts.get(0),
                cD.strToDateTime(texts.get(1)), cD.strToDateTime(texts.get(2)));
        tasks.addTask(event);
        task = event;
        size = tasks.getSize();
        System.out.println(this);
        try {
            dF.writeToFile(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the string representation of event command.
     * @return String representation of event command.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size  + " tasks in the list.";
    }
}
