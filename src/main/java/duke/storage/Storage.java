package duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.Duke;
import duke.dates.Dates;
import duke.task.ItemList;
import duke.task.Task;
import duke.task.deadline.Deadline;
import duke.task.event.Event;
import duke.task.todo.ToDo;
import duke.ui.UI;

/**
 * Storage class loads the local data into the program, and into a. ItemList.
 */
public class Storage {
    private ItemList items;

    public Storage() {
        try {
            this.items = loadAll();
        } catch (IOException e) {
            this.items = new ItemList(new File(Duke.LISTPATH));
            UI.printMessage("List not found, empty list will be created");
        }
    };

    private static ToDo parseTodo(String line) {
        String[] para = line.split(" | ");
        String description = para[4];
        ToDo newTask = new ToDo(description);
        if (!para[2].equals("0")) {
            newTask.setDone();
        }
        return newTask;
    }

    private static Deadline parseDeadline(String line) {
        String[] para = line.split(" \\| ", 4);
        String description = para[2];
        String by = para[3];
        Deadline newTask;
        if (Dates.checkDateString(by)) {
            newTask = new Deadline(description, Dates.createDateTime(by));
        } else {
            newTask = new Deadline(description, by);
        }

        if (!para[1].equals("0")) {
            newTask.setDone();
        }

        return newTask;
    }

    private static Event parseEvent(String line) {
        String[] para = line.split(" \\| ", 4);
        String description = para[2];
        String block = para[3];
        String[] fromTo = block.split(" to ", 2);
        Event newTask;
        if (Dates.checkDateString(fromTo[0]) && Dates.checkDateString(fromTo[1])) {
            newTask = new Event(description, Dates.createDateTime(fromTo[0]),
                    Dates.createDateTime(fromTo[1]));
        } else {
            newTask = new Event(description, fromTo[0], fromTo[1]);
        }

        if (!para[1].equals("0")) {
            newTask.setDone();
        }

        return newTask;
    }



    /**
     * returns the Items being stored in Storage
     * @return the items stored that is loaded from local storage
     */
    public ItemList getItems() {
        return this.items;
    }
    private static ItemList loadAll() throws IOException {
        File file = getFile();
        ArrayList<Task> items = new ArrayList<>();
        int noOfItems = 0;
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.charAt(0) == 'T') {
                items.add(parseTodo(line));
                noOfItems++;
                continue;
            }
            if (line.charAt(0) == 'D') {
                items.add(parseDeadline(line));
                noOfItems++;
                continue;
            }

            if (line.charAt(0) == 'E') {
                items.add(parseEvent(line));
                noOfItems++;

            }
        }
        s.close();
        return new ItemList(file, noOfItems, items);
    }

    private static File getFile() throws IOException {
        File file = new File(Duke.LISTPATH);
        if (!file.exists()) {
            File parentDirectory = file.getParentFile();  // get the parent directory, which is 'data' in this case
            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();  // create the directory if it doesn't exist
            }
            file.createNewFile();  // create the file inside the directory
        }
        assert file.isFile() : "The provided path does not point to a valid file: " + Duke.LISTPATH;
        return file;
    }
}
