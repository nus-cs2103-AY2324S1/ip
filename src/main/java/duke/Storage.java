package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.Tasks.TaskList;

import duke.Tasks.Task;

public class Storage {
    private String filePath;
    private File file;
    private FileReader fr;
    private Scanner sc;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("Creation of File in process, please be patient");
        }
        this.fr = new FileReader(file);
    }

    // solution for save adapted from asdfghjkxd
    // Storage.java write method
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    public Scanner load() {
        if (sc != null) {
            return sc;
        } else {
            sc = new Scanner(fr);
            return sc;
        }
    }
}
