package monke;

import monke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public ArrayList<Task> load() throws MonkeException {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                Task task = Parser.parseLoadedData(s.nextLine());
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new MonkeException("Could not load tasks");
        }
    }

    public void saveData(TaskList tasks) throws MonkeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task: tasks.toList()) {
                textToAdd.append(task.formatData());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new MonkeException("Could not open file");
        }
    }
}
