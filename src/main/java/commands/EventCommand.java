package commands;

import duke.CustomDate;
import storage.DataFile;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {

    private final ArrayList<String> texts;
    private Task task;
    private int size;

    public EventCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

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

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size  + " tasks in the list.";
    }
}
