package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;
    private Scanner sc;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist. Creating file...");
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("File created!");
        }
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(Task task : tasks) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    public Scanner load() throws IOException {
        if (sc != null) {
            return sc;
        } else {
            sc = new Scanner(this.file);
            return sc;
        }
    }
}
