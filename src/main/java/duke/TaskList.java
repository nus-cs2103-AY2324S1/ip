package duke;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList(File file) throws DukeException {
        this.taskList = new ArrayList<Task>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                try {
                    taskList.add(Parser.parseFileInfo(data));
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("FileNotFound Error");
        }
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> list() {
        return taskList;
    }
}
