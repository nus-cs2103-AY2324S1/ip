package dialogix.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dialogix.exception.DialogixException;
import dialogix.task.Deadline;
import dialogix.task.Event;
import dialogix.task.Task;
import dialogix.task.Todo;

/**
 * Handles loading and saving tasks from/to the user's hard drive.
 */
public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the user's hard drive.
     *
     * @return The list of tasks currently stored in the user's hard drive.
     * @throws DialogixException If an error occurs during loading.
     */
    ArrayList<Task> load() throws DialogixException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String input;
            while ((input = br.readLine()) != null) {
                processInput(input, tasks);
            }
        } catch (IOException e) {
            throw new DialogixException("An IOException occurred. " + e);
        } catch (NumberFormatException e) {
            throw new DialogixException("An error occurred during file parsing, unexpected done value encountered.");
        }
        return tasks;
    }

    private void processInput(String input, ArrayList<Task> tasks) throws DialogixException {
        String[] splitInput = input.split(" \\| ");
        Task task;
        switch (splitInput[0]) {
        case "T":
            task = new Todo(splitInput[2]);
            break;
        case "D":
            if (Parser.isDate(splitInput[3])) {
                task = new Deadline(splitInput[2], Parser.parseDate(splitInput[3]));
                break;
            }
            task = new Deadline(splitInput[2], splitInput[3]);
            break;
        case "E":
            if (Parser.isDate(splitInput[3])) {
                task = new Event(splitInput[2], Parser.parseDate(splitInput[3]));
                break;
            }
            task = new Event(splitInput[2], splitInput[3]);
            break;
        default:
            throw new DialogixException("Error occurred during file parsing, unexpected task type encountered.");
        }
        if (Integer.parseInt(splitInput[1]) == 1) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Saves the given list of tasks to the user's hard drive.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DialogixException If an error occurs during saving.
     */
    public void save(ArrayList<Task> tasks) throws DialogixException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task l : tasks) {
                bw.append(l.getOutputFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DialogixException("There is an error of saving: " + e);
        }
    }
}
