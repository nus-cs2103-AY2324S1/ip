package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.task.Deadlines;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;
import duke.task.Events;

/**
 * A class handling the saving and load the taskList
 * into a file.
 */
public class Storage {

    /**
     * The path of the file where the taskList is saved
     */
    protected String filePath;

    /**
     * The constructor for the Storage
     */
    public Storage() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "duke.txt");
        File file = new File(path.toString());
        try {
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            this.filePath = path.toString();
        } catch (IOException e) {
            System.out.println("Cannot create file!");
        }
    }

    /**
     * Writes the data into the file.
     * @param data The content to be written into the file
     */
    public void writeData(String data) {
        try {
            FileWriter file = new FileWriter(this.filePath, false);
            file.write(data);
            file.close();
        } catch (IOException e) {
            //Throw DukeException
        }

    }

    /**
     * Reads the content of the file
     * @return TaskList
     */
    public TaskList loadData() {
        TaskList tempList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tempList.addTask(parseLine(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        return tempList;

    }

    /**
     * Reads the line and convert them into Task objects
     * @param line The line read from the file
     * @return Task
     */
    public Task parseLine(String line) {
        String[] input = line.split(" \\| ");
        if (input[0].equals("T")) {
            Todos task = new Todos(input[2]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (input[0].equals("D")) {
            Deadlines task = new Deadlines(input[2], input[3]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (input[0].equals("E")) {
            Events task = new Events(input[2], input[3], input[4]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        }
        return null;
    }

}
