package haste.data;

import haste.commands.Parser;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;
import haste.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Storage {
    // create data file
    private final String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        create();
    }
    public void create() {
        this.file = new File(this.filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error creating file");
            }
        }
    }

    // read data file
    public void read(Ui ui, TaskList tasks) {
        try {
            Scanner scanner = new Scanner(this.file);
            if (scanner.hasNextLine()) {
               System.out.println("loading tasks from saved files...");
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task currTask = readTask(line);
                tasks.addTask(currTask);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // add one line to text file
    public void write(String line) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task readTask(String line) {
        String[] words = line.split("[|]");
        String command = words[0];
        boolean isComplete = Boolean.parseBoolean(words[1]);
        String description = words[2];
        Task newTask = null; // task will never be null as tasks are correctly saved

        switch (command.toLowerCase()) {
            case "t":
                newTask = new ToDo(description, isComplete);
                break;
            case "d":
                newTask = new Deadline(description, Parser.parseTime(words[3]), isComplete);
                break;
            case "e":
                newTask = new Event(description, Parser.parseTime(words[3]), Parser.parseTime(words[4]), isComplete);
                break;


        }
        return newTask;

    }

    public void save(TaskList tasks) {
        create();
        for (Task task: tasks.taskList) {
            write(task.save() + "\n");
        }
    }
    public void delete() {
        file.delete();
    }

    public static void main (String args[]) {
        Storage store = new Storage("./Data.txt");

    }
}
