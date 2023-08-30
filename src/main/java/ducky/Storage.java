package ducky;

import ducky.task.DeadlineTask;
import ducky.task.EventTask;
import ducky.task.Task;
import ducky.task.TodoTask;
import ducky.util.DuckyFileParseException;
import ducky.util.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getSaveableList());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(TaskList taskList) {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task parsedTask = Parser.parseSavedTask(s.nextLine());
                taskList.addTask(parsedTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No task file found. No tasks were loaded.");
        } catch (DuckyFileParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
