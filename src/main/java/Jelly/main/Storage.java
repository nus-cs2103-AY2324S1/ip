package Jelly.main;

import Jelly.exceptions.JellyException;
import Jelly.task.Deadline;
import Jelly.task.Event;
import Jelly.task.Task;
import Jelly.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./taskData/jelly.txt";

    public ArrayList<Task> startUp() throws JellyException {
        ArrayList<Task> storage = new ArrayList<>();
        try {
            File data = new File(FILE_PATH);
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                return storage;
            }
            int index = storage.size();
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] taskDetails = str.split(" \\| ");
                Task task = new Task("");

                if (taskDetails[0].equals("T")) {
                    task = new Todo(taskDetails[2]);
                } else if (taskDetails[0].equals("D")) {
                    task = new Deadline(taskDetails[2], taskDetails[3]);
                } else if (taskDetails[0].equals("E")) {
                    String[] eventDetails = taskDetails[3].split(" to ");
                    task = new Event(taskDetails[2], eventDetails[0], eventDetails[1]);
                } else {
                    System.out.println("Unknown task in file");
                }

                if (taskDetails[1].equals("1")) {
                    task.markAsDone();
                } else {
                    task.markAsUndone();
                }
                storage.add(task);
                index++;
            }
            return storage;
        } catch (IOException e) {
            throw new JellyException("Error: " + e.getMessage());
        }
    }

    private void saveAndExit(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);

            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).writeToFile() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }
}
