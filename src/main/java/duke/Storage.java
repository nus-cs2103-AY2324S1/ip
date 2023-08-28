package duke;

import duke.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private static final String DATA_FILE_PATH = "\\data\\duke.txt";
    private static final String DIRECTORY_PATH = "\\data";

    public void writeToFile(String textToAdd) throws DukeException {
        try {
            File theDir = new File(DIRECTORY_PATH);
            if (!theDir.exists()) {
                theDir.mkdirs();
                File dataFile = new File(DATA_FILE_PATH);
                dataFile.createNewFile();
            }

            File file = new File(DATA_FILE_PATH);

            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            if (file.length() != 0) {
                fw.write(System.lineSeparator());
            }
            fw.write(textToAdd);

            fw.close();
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
    public TaskList loadFromFile() {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            if (dataFile.exists()) {
                Scanner sc = new Scanner(dataFile);
                TaskList tasks = new TaskList();
                while (sc.hasNext()) {
                    String taskInfo = sc.nextLine();
                    String taskName = taskInfo.substring(7);
                    boolean marked = taskInfo.charAt(4) == 'X';

                    if (taskInfo.charAt(1) == 'T') {
                        Task task = new Todo(taskName);
                        tasks.add(task);
                    } else if (taskInfo.charAt(1) == 'D') {
                        Task task = new Deadline(taskName);
                        tasks.add(task);
                    } else if (taskInfo.charAt(1) == 'E') {
                        Task task = new Event(taskName);
                        tasks.add(task);
                    }

                    if (marked) {
                        tasks.mark(tasks.size() - 1);
                    }
                }
                sc.close();

                return tasks;
            }
            return new TaskList();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return new TaskList();
    }

    public void writeAllToFile(ArrayList<Task> tasklist) throws DukeException {
        try {
            File file = new File(DATA_FILE_PATH);
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();

            for (int i = 0; i < tasklist.size(); i++) {
                writeToFile(tasklist.get(i).storageText());
            }
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }

    }
}
