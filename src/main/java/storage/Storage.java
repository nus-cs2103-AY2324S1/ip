package storage;

import errors.TaskError;
import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // TODO: Handle the situation of the data file being corrupted
    public static ArrayList<Task> getTasks(File file) {
        try {
            Scanner sc = new Scanner(file);

            ArrayList<Task> taskList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (currLine.isBlank()) {
                    break;
                }
                // Pipe is a special character is regex
                String[] items = currLine.split(" \\| ");
                String taskType = items[0];
                boolean isCompleted = items[1].equals("1");
                String description = items[2];
                switch (taskType) {
                case "T":
                    Task todoTask = new Todo(description, isCompleted);
                    taskList.add(todoTask);
                    break;
                case "D":
                    Task deadlineTask = new Deadline(description, items[3], isCompleted);
                    taskList.add(deadlineTask);
                    break;
                case "E":
                    Task eventTask = new Event(description, items[3], items[4], isCompleted);
                    taskList.add(eventTask);
                    break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            TaskError.ERR_READING_FILE.printErrorMessage(e);
        }
        return new ArrayList<>();
    }

    public static void saveTasks(File file, ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task currTask : taskList) {
                fw.write(currTask.getFileFormat() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            TaskError.ERR_WRITING_FILE.printErrorMessage(e);
        }
    }
}
