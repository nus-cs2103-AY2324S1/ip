package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.exceptions.DukeInvalidDateException;
import duke.task.*;
import duke.ui.Ui;

public class Storage {
    private List<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();

        try {
            File dataFolder = new File("./data");

            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File dataFile = new File("./data/data.txt");

            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException error) {
            Ui.printLines("Something went wrong when loading tasks :(");
        }
    }

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
            } catch (DukeInvalidDateException error) {
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


    public void writeTasks(TaskList taskList) {
        try {
            FileWriter dataWriter = new FileWriter("./data/data.txt");
            for (int taskIndex = 1; taskIndex <= taskList.getSize(); taskIndex++) {
                dataWriter.write(taskList.getTask(taskIndex).toDataRepresentation() + "\n");
            }
            dataWriter.close();
        } catch (IOException error) {
            Ui.printLines("Something went wrong when updating tasks :(");
        }
    }
}
