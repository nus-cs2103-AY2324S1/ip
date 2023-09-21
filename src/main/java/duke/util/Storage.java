package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    private File file;

    /**
     * Constructor for the Storage class
     * @param filePath The filePath of the file that stores all the Task inputs from user.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update the file that stores all the Task inputs from user.
     * @param task The String representation of the Tasks in a TaskList that
     *             should be stored in the file that stores all the Task inputs
     *             from user.
     */
    public void write(String task) {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(task);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read and load the Tasks stored by previous inputs in the specified file.
     * @return An ArrayList that stores all the previous Tasks stored in the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                Task instance = Storage.readLine(task);
                tasks.add(instance);
            }
        } catch (FileNotFoundException e) {
            System.out.println("error file not found");
            System.out.println(e.getMessage());
        }
        return tasks;
    }


    /**
     * Read the input command and create the corresponding type of Tasks.
     * @param nextLine The command String.
     * @return The Task according to the user input.
     */
    public static Task readLine(String nextLine) {
        String description = nextLine.substring(3);
        Task task;
        if(nextLine.startsWith("[T]")) {
            return Parser.parseFileTodo(description);
        } else if (nextLine.startsWith("[D]")) {
            return Parser.parseFileDeadline(description);
        } else {
            return Parser.parseFileEvent(description);
        }
    }
}

