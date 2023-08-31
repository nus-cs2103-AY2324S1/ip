package commands;

import storage.DataFile;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command{
    private final ArrayList<String> texts;
    private Task task;
    private int size;

    public TodoCommand(ArrayList<String> texts) {
        this.texts = texts;
    }

    @Override
    public void execute(TaskList tasks, DataFile dF) {
        Task todo = new Todo(texts.get(0));
        tasks.addTask(todo);
        task = todo;
        size = tasks.getSize();
        System.out.println(this);
        try {
            dF.writeToFile(todo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task +
                "\nNow you have " + size  + " tasks in the list.";
    }
}
