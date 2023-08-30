package ducky;

import ducky.task.DeadlineTask;
import ducky.task.EventTask;
import ducky.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Storage module that handles file I/O for Ducky chatbot.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance that saves and loads the specified file path.
     * @param filePath File path to load persistent data from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data in the specified TaskList to previously specified file path.
     * @param taskList TaskList with data to be stored.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getSaveableList());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads data (if any) in previously specified file path into specified TaskList.
     * @param taskList TaskList for persistent data to be loaded into.
     */
    public void load(TaskList taskList) {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] line = s.nextLine().trim().split(" \\| ");
                boolean taskIsDone = line[1].equals("1");
                switch (line[0]) {
                    case "T":
                        TodoTask newTodo = new TodoTask(line[2]);
                        if (taskIsDone) {
                            newTodo.setComplete();
                        }
                        taskList.addTask(newTodo);
                        break;
                    case "D":
                        LocalDate deadline = LocalDate.parse(line[3]);
                        DeadlineTask newDeadline = new DeadlineTask(line[2], deadline);
                        if (taskIsDone) {
                            newDeadline.setComplete();
                        }
                        taskList.addTask(newDeadline);
                        break;
                    case "E":
                        EventTask newEvent = new EventTask(line[2], line[3], line[4]);
                        if (taskIsDone) {
                            newEvent.setComplete();
                        }
                        taskList.addTask(newEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ducky.task.Task file not created yet.");
        }
    }
}
