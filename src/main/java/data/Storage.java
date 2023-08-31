package data;

import commands.Parser;
import tasks.Task;
import ui.Ui;

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
    public void read(Ui ui) {
        try {
            Scanner scanner = new Scanner(this.file);
            if (scanner.hasNextLine()) {
               System.out.println("loading tasks from saved files...");
            }
            while (scanner.hasNextLine()) {
                String cmd = scanner.nextLine();
                Parser.handleCommand(cmd, ui);
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

    public void save() {
        create();
        for (Task task: TaskList.taskList) {
            write(task.getCmd() + "\n");
        }
    }
    public void delete() {
        file.delete();
    }
}
