package commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.CustomDate;
import storage.DataFile;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;


/**
 * Represents a type of command that can be read by the chatbot.
 */
public class EventCommand extends Command {

    private final ArrayList<String> texts;

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
    public String execute(TaskList tasks , DataFile dF) {
        CustomDate cD = new CustomDate();
        Task event = new Event(texts.get(0),
                cD.strToDateTime(texts.get(1)), cD.strToDateTime(texts.get(2)));
        tasks.addTask(event);
        try {
            dF.writeToFile(event);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert !texts.isEmpty();
        return cmdToString(event.toString(), tasks.getSize());
    }

    /**
     * Returns the string representation of event command.
     * @return String representation of event command.
     */
    public String cmdToString(String task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }
}
