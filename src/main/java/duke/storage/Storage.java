package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
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
        } catch (FileNotFoundException e) {
            this.items = new ItemList(new File(Duke.LISTPATH));
            UI.printMessage("List not found, empty list will be created");
        }
    };


    /**
     * returns the Items being stored in Storage
     * @return the items stored that is loaded from local storage
     */
    public ItemList getItems() {
        return this.items;
    }
    private static ItemList loadAll() throws FileNotFoundException {
        File file = new File(Duke.LISTPATH);
        assert file.isFile() : "The provided path does not point to a valid file: " + Duke.LISTPATH;
        ArrayList<Task> items = new ArrayList<Task>();
        int len = 0;
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.charAt(0) == 'T') {
                String[] para = line.split(" | ");
                String description = para[4];
                ToDo newtask = new ToDo(description);
                if (!para[2].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;
                continue;
            }
            if (line.charAt(0) == 'D') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String by = para[3];
                Deadline newtask;
                if (Dates.checkDateString(by)) {
                    newtask = new Deadline(description, Dates.createDateTime(by));
                } else {
                    newtask = new Deadline(description, by);
                }

                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;
                continue;
            }


            if (line.charAt(0) == 'E') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String block = para[3];
                String[] fromTo = block.split(" to ", 2);
                Event newtask;
                if (Dates.checkDateString(fromTo[0]) && Dates.checkDateString(fromTo[1])) {
                    newtask = new Event(description, Dates.createDateTime(fromTo[0]),
                            Dates.createDateTime(fromTo[1]));
                } else {
                    newtask = new Event(description, fromTo[0], fromTo[1]);
                }

                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                items.add(newtask);
                len++;

            }
        }
        s.close();
        return new ItemList(file, len, items);
    }
}
