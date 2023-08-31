package commands;

import duke.CustomDate;
import parser.Parser;
import storage.DataFile;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class DeadlineCommand extends Command{
    private final ArrayList<String> texts;
    private Task task;
    private int size;

    public DeadlineCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    @Override
    public void execute(TaskList tasks , DataFile dF) {
        CustomDate cD = new CustomDate();
        Task dl = new Deadline(texts.get(0), cD.strToDateTime(texts.get(1)));
        tasks.addTask(dl);
        task = dl;
        size = tasks.getSize();

        try {
            dF.writeToFile(dl);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task +
                "\nNow you have " + size + " tasks in the list.";
    }
}
