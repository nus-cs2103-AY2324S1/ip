package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the object that stores the data created when using the chatbot.
 */
public class Storage {
    private final String filePath = "./data/duke.txt";

    /**
     * Creates the file to save the chatbot data.
     */
    public void createFile() {
        File dataFile = new File(filePath);
        File dataDirectory = new File("./data");
        dataDirectory.mkdir();
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create file to save commands");
        }
    }

    /**
     * Saves the task list to file.
     * @param list The task list to be saved
     * @throws IOException when unable to save to file
     */
    public void saveTasksToFile(TaskList list) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(list.toLogString());
        writer.close();
    }

    /**
     * Returns an ArrayList of tasks loaded from the saved file.
     * @return The ArrayList of tasks
     * @throws DukeException if data in the saved file is corrupted
     */
    public ArrayList<Task> loadFile() throws DukeException {
        File dataFile = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String[] commandArray = fileReader.nextLine().split("\\|");
                Task newTask;
                switch (commandArray[0]) {
                case "T":
                    newTask = new Todo(commandArray[2]);
                    break;
                case "D":
                    newTask = new Deadline(commandArray[2], commandArray[3]);
                    break;
                case "E":
                    newTask = new Event(commandArray[2], commandArray[3], commandArray[4]);
                    break;
                default:
                    throw new DukeException("Data file is corrupted, cannot load from file");
                }
                if (Objects.equals(commandArray[1], "X")) {
                    newTask.markDone();
                }
                taskList.add(newTask);
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Data file is corrupted, cannot load from file");
        } catch (FileNotFoundException e) {
            createFile();
        }
        return taskList;
    }
}
