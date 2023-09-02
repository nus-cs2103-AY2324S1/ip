package Ally;

import Ally.Exceptions.AllyException;
import Ally.Tasks.Deadline;
import Ally.Tasks.Event;
import Ally.Tasks.Task;
import Ally.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) throws AllyException {
        this.filePath = filePath;
        try {
            load();
        } catch (AllyException e) {
            throw new AllyException("Unable to load");
        }
    }
    public ArrayList<Task> load() throws AllyException {
        ArrayList<Task> loadTasks = new ArrayList<>(100);
        createFile();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String line = s.nextLine();
                loadTasks.add(readData(line));
            }
            s.close();
        } catch (FileNotFoundException e) {
            this.createFile();
            throw new AllyException("Not able to scan!");
        }
        return loadTasks;
    }

    public Task readData(String data) {
        String[] splits = data.split(" | ");
        Task savedTasks = null;

        if (splits[0].equals("T")) {
            savedTasks = new Todo(splits[2]);
        } else if (splits[0].equals("D")) {
            savedTasks = new Deadline(splits[2], splits[3]);
        } else if (splits[0].equals("E")) {
            savedTasks = new Event(splits[2], splits[3], splits[4]);
        }

        if (splits[1].equals("1")) {
            savedTasks.setMarked();
        }
        return savedTasks;
    }

    public void appendToFile(Task task) throws AllyException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.formatFile());
            fw.close();
        } catch (IOException e) {
            throw new AllyException("Can't write your file");
        }
    }

    public void createFile() throws AllyException {
        File f = new File(filePath);
        File dir = new File(f.getParent());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new AllyException("Ohnos, you can't create the file :-(");
        }
    }
}
