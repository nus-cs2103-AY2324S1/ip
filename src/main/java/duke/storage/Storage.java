package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.exceptions.DukeInvalidDateException;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import duke.ui.Ui;

/**
 * Represents a storage to write and read data
 * of the users' tasks.
 *
 * @author Andrew Daniel Janong
 */
public class Storage {

    /**
     * Creates a Storage object.
     * Folder and File of data will be created if have not before.
     */
    public Storage() {
        try {
            File dataFolder = new File("./data");

            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File dataFile = new File("./data/data.txt");

            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException exception) {
            Ui.printLines("Something went wrong when loading tasks :(");
        }
    }

    /**
     * Gets data from the data file and
     * passes it to the task list.
     *
     * @param taskList Task list of the chatbot.
     * @throws IOException Error when reading data.
     */
    public void getTasksFromData(TaskList taskList) throws IOException {
        List<Task> tasks = new ArrayList<>();
        File dataFile = new File("./data/data.txt");
        Scanner reader = new Scanner(dataFile);

        while (reader.hasNextLine()) {
            String[] currentTask = reader.nextLine().split(Pattern.quote("|"), 2);
            String taskType = currentTask[0];
            String[] taskInfo = currentTask[1].split(Pattern.quote("|"), 0);

            Task task;

            try {
                switch (taskType) {
                    case "T":
                        task = new ToDo(taskInfo[1]);
                        break;
                    case "D":
                        task = new Deadline(taskInfo[1], taskInfo[2]);
                        break;
                    case "E":
                        task = new Event(taskInfo[1], taskInfo[2], taskInfo[3]);
                        break;
                    default:
                        continue;
                }
            } catch (DukeInvalidDateException exception) {
                Ui.printLines("Something went wrong when loading tasks :(");
                break;
            }

            if (taskInfo[0].equals("1")) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }

            tasks.add(task);
        }

        reader.close();
        taskList.readTasksFromStorage(tasks);
    }

    /**
     * Writes to the data file the current users' tasks.
     *
     * @param taskList Task list of the chatbot.
     */
    public void writeTasks(TaskList taskList) {
        try {
            FileWriter dataWriter = new FileWriter("./data/data.txt");
            for (int i = 1; i <= taskList.getSize(); i++) {
                dataWriter.write(taskList.getTask(i).getDataRepresentation() + "\n");
            }
            dataWriter.close();
        } catch (IOException error) {
            Ui.printLines("Something went wrong when updating tasks :(");
        }
    }
}
