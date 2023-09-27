package geraldbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

/**
 * Handles reading and writing tasks to the task storage file.
 */
public class TaskStorage extends Storage<Task> {
    /**
     * Constructor for TaskStorage.
     *
     * @param path The path to the task storage file.
     */
    public TaskStorage(String path) {
        super(path);
    }

    /**
     * Returns the storage file.
     *
     * @return The storage file.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Reads tasks from the storage file.
     *
     * @return An ArrayList of Task objects.
     */
    @Override
    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\|");
                String[] task = new String[line.length];
                for (int i = 0; i < line.length; i++) {
                    task[i] = line[i].trim();
                }

                if (task[0].equals("T")) {
                    taskList.add(new Todo(task[2], task[1].equals("1")));
                } else if (task[0].equals("D")) {
                    LocalDateTime deadline = LocalDateTime.parse(task[3]);
                    taskList.add(new Deadline(task[2], task[1].equals("1"), deadline));
                } else if (task[0].equals("E")) {
                    String[] timeframe = task[3].split("-");
                    taskList.add(new Event(task[2], task[1].equals("1"), timeframe[0], timeframe[1]));
                } else {
                    System.out.println("Unknown task type: " + task[0]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            createFile();
        }

        return taskList;
    }

    /**
     * Adds a task in file format to the storage file.
     *
     * @param fileFormat The formatted task to add.
     */
    public void addTask(String fileFormat) {
        try {
            FileWriter fw = new FileWriter(this.file, true);

            if (this.file.length() != 0) {
                fw.write("\n");
            }

            fw.write(fileFormat);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Updates a task in the storage file.
     *
     * @param index The index of the task to update.
     * @param updatedFile The updated task in file format.
     */
    public void updateTask(int index, String updatedFile) {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<String> updatedTaskList = new ArrayList<>();
            int idx = 0;

            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();

                if (idx != index) {
                    updatedTaskList.add(currTask);
                } else {
                    if (updatedFile != null) {
                        updatedTaskList.add(updatedFile);
                    }
                }

                idx++;
            }

            sc.close();

            FileWriter fw = new FileWriter(this.file);
            fw.write(String.join("\n", updatedTaskList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
