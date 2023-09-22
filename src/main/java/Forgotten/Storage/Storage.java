package Forgotten.Storage;

import Forgotten.Task.Deadline;
import Forgotten.Task.Event;
import Forgotten.Task.Task;
import Forgotten.Task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Default constructor method.
     *
     * @param filePath The file where tasks are going to be written on.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads the data from the hard disk to an array list.
     *
     * @return An array list of task objects.
     * @throws FileNotFoundException If file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            readString(nextLine, taskList);
        }
        scanner.close();
        return taskList;
    }

    private void readString(String string, ArrayList<Task> taskList) {
        String string1 = string.substring(0,6);
        char taskType = string1.charAt(1);
        boolean isDone = string1.charAt(4) == 'X';

        if (taskType == 'T') {
            String description = string.substring(7);
            taskList.add(new Todo(description, isDone));
        } else if (taskType == 'D') {
            String[] split = string.split(" \\(by: ");
            String description = split[0].substring(7);
            LocalDate day = LocalDate.parse(split[1].substring(0, split[1].length() - 1));
            taskList.add(new Deadline(description, day, isDone));
        } else {
            String[] split1 = string.split(" \\(from: ");
            String[] split2 = split1[1].split(" to: ");
            String description = split1[0].substring(7);
            String start = split2[0];
            String end = split2[1].substring(0, split2[1].length() - 1);
            taskList.add(new Event(description, LocalDate.parse(start), LocalDate.parse(end), isDone));
        }
    }

    /**
     * This method writes the tasks into the file given by the path.
     *
     * @param taskDetail Description of the task.
     * @throws IOException If file does not exist and cannot be created.
     */
    public void writeToFile(String taskDetail) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(taskDetail + "\n");
        fileWriter.close();
    }

    public void createNewFile() {
        System.out.println("We have detected that you're missing the database file");
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        assert !file.exists(): "File does not exist";
    }

    public void rewriteFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task: tasks) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
