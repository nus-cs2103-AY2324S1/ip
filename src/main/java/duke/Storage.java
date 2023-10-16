package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * The `Storage` class handles the reading and writing of tasks from/to a data file.
 */
public class Storage {
    private String filePath;

    private String outputFilePath = "./data/outputDuke.txt";

    /**
     * Constructs a new `Storage` instance with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file specified in the constructor.
     *
     * @return An ArrayList of Task objects representing the tasks loaded from the file.
     * @throws IOException              if there is an issue reading the file.
     * @throws InvalidFileFormatException if the file contains tasks in an invalid format.
     */
    public ArrayList<Task> load() throws IOException, InvalidFileFormatException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        ArrayList<Task> taskList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            switch (line.charAt(0)) {
            case 'T':
                loadTodoTask(line, taskList);
                break;

            case 'D':
                loadDeadlineTask(line, taskList);
                break;

            case 'E':
                loadEventTask(line, taskList);
                break;

            default:
                // Handle invalid lines or raise an exception if needed.
                break;
            }
        }
        return taskList;
    }

    private static void loadEventTask(String line, ArrayList<Task> taskList) throws InvalidFileFormatException {
        String[] splitStringList = line.split("\\|");
        if (splitStringList.length != 5) {
            throw new InvalidFileFormatException("Invalid format for Event task in the file.");
        }

        String fabricatedUserInput = "event " + splitStringList[2] + "/from " + splitStringList[3] + "/to "
                + splitStringList[4];

        Task ev;
        try {
            ev = Task.taskCon(fabricatedUserInput);
        } catch (InvalidCommandException | InvalidTaskCreationException e) {
            throw new InvalidFileFormatException("Invalid format for Event task in the file.");
        }

        if (splitStringList[1].equals("1")) {
            ev.markAsDone();
        }
        taskList.add(ev);
    }

    private static void loadDeadlineTask(String line, ArrayList<Task> taskList) throws InvalidFileFormatException {
        String[] splitStringList = line.split("\\|");
        if (splitStringList.length != 4) {
            throw new InvalidFileFormatException("Invalid format for Deadline task in the"
                    + " file.");
        }
        String fabricatedUserInput = "deadline " + splitStringList[2] + "/by " + splitStringList[3];

        Task d;
        try {
            d = Task.taskCon(fabricatedUserInput);
        } catch (InvalidCommandException | InvalidTaskCreationException e) {
            throw new InvalidFileFormatException("Invalid format for Deadline task in the"
                    + " file.");
        }

        if (splitStringList[1].equals("1")) {
            d.markAsDone();
        }
        taskList.add(d);
    }

    private static void loadTodoTask(String line, ArrayList<Task> taskList) throws InvalidFileFormatException {
        String[] splitStringList = line.split("\\|");
        if (splitStringList.length != 3) {
            throw new InvalidFileFormatException("Invalid format for Todo task in the file.");
        }
        String fabricatedUserInput = "todo " + splitStringList[2];

        Task t;
        try {
            t = Task.taskCon(fabricatedUserInput);
        } catch (InvalidCommandException | InvalidTaskCreationException e) {
            throw new InvalidFileFormatException("Invalid format for Todo task in the file.");
        }

        if (splitStringList[1].equals("1")) {
            t.markAsDone();
        }
        taskList.add(t);
    }

    public void saveTasksToFile(TaskList tasks) throws IOException, InvalidFileFormatException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFilePath))) {
            for (Task task : tasks.taskArrayList) {
                String taskOutput = task.toFileOutput();
                writer.write(taskOutput);
                writer.newLine(); // Add a newline after each task
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
    }


}
