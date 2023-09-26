package oreo.command;

import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {
    private final String filePath = "./oreo.txt";

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath, true);
            int numberOfTasks = tasks.getNumberOfTask();
            for (int i = 0; i < numberOfTasks; i++) {
                String data = tasks.get(i).writeToFile();
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Thoroughly remembered all your task for you! I think I deserve a belly rub!";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) throws IllegalDateTimeException {
        return execute(tasks);
    }
}
