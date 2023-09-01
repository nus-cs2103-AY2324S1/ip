package kiera;

import kiera.exception.KieraException;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws KieraException {
        File f = new File(filePath);
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                Task t;
                String next = s.nextLine();
                String[] r = next.split(" // ");
                String type = r[0];
                String done = r[1];
                String desc = r[2];
                if (type.equals("T")) {
                    t = new Todo(desc);
                } else if (type.equals("D")) {
                    t = new Deadline(desc);
                } else {
                    t = new Event(desc);
                }
                if (done.equals("X")) {
                    t.markAsDone();
                } else {
                    t.markAsUndone();
                }
                result.add(t);

            }
        } catch (FileNotFoundException e) {
            throw new KieraException(e.getMessage());
        }
        return result;
    }

    public void save(TaskList tasks) {
        String text =  tasks.getTasks().stream().map(task -> task.toStorageString() + "\n").collect(Collectors.joining());
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new KieraException("data not saved..." + e);
        }
    }

}
