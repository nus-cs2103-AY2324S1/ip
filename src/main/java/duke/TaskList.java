package duke;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasklist;

    TaskList(File file) throws DukeException {
        this.tasklist = new ArrayList<Task>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                try {
                    tasklist.add(Parser.ParseFileInfo(data));
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("FileNotFound Error");
        }
    }

    TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    public ArrayList<Task> list() {
        return tasklist;
    }

    public ArrayList<Task> contains(String key) {
        ArrayList<Task> temp = new ArrayList<>(this.tasklist);
        temp.removeIf(t -> !t.containsStr(key));
        return temp;
    }
}
