package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

import duke.task.Deadlines;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;
import duke.task.Events;

public class Storage {
    protected String filePath;

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

    public void writeData(String data) {
        try {
            FileWriter file = new FileWriter(this.filePath, false);
            file.write(data);
            file.close();
        } catch (IOException e) {
            //Throw DukeException
        }

    }

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
