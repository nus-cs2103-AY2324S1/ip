package dialogix.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<Task> load() throws DialogixException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String input;
                while ((input = br.readLine()) != null) {
                    Task task = parseTaskFromInput(input);
                    tasks.add(task);
                }
                br.close();
            }
        } catch (IOException e) {
            throw new DialogixException("An IOException occurred. " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTaskFromInput(String input) throws DialogixException {
        String[] splitInput = input.split(" \\| ");
        validateInputFormat(splitInput);

        String taskType = splitInput[0];
        String description = splitInput[2];

        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = createDeadlineTask(description, splitInput[3]);
            break;
        case "E":
            task = createEventTask(description, splitInput[3]);
            break;
        default:
            throw new DialogixException("Error occurred during file parsing, unexpected task type encountered.");
        }
        if (Integer.parseInt(splitInput[1]) == 1) {
            task.markAsDone();
        }
        return task;
    }

    private Task createDeadlineTask(String description, String dateOrTime) throws DialogixException {
        if (Parser.isDate(dateOrTime)) {
            return new Deadline(description, Parser.parseDate(dateOrTime));
        } else {
            return new Deadline(description, dateOrTime);
        }
    }

    private Task createEventTask(String description, String dateOrTime) throws DialogixException {
        if (Parser.isDate(dateOrTime)) {
            return new Event(description, Parser.parseDate(dateOrTime));
        } else {
            return new Event(description, dateOrTime);
        }
    }

    private void validateInputFormat(String[] splitInput) throws DialogixException {
        boolean isInvalidLength = splitInput.length != 4;
        boolean isEmptyFields = Arrays.stream(splitInput).anyMatch(String::isEmpty);

        if (isInvalidLength || isEmptyFields) {
            throw new DialogixException("Invalid task format in storage file.");
        }
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
