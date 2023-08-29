
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * This class encapsulates the items being that are added by the users, and
 * the functions used to list them out, mark them add and delete items from it
 *
 */
public class ItemList {
    private ArrayList<Task> items;
    private int len;
    private File file;
    public ItemList(File file) {
        this.items = new ArrayList<>();
        this.len = 0;
        this.file = file;
    }

    public ItemList(File file, int len, ArrayList<Task> items) {
        this.file = file;
        this.items = items;
        this.len = len;
    }
    /**
     * This method add a Deadline task to the item list.
     *
     * @param name the string name of the Deadline task.
     * @param by the string of the time the task ends.
     */
    @SuppressWarnings("unchecked")
    public void addDeadline(String name, String by) {

        if (name.equals("")) {
            UI.printMessage("Please enter an item");
            return;
        }

        if (by.equals("")) {
            UI.printMessage("Please enter an end date");
            return;
        }
        Deadline deadline;
        if(Dates.checkDateinput(by)) {
            deadline = new Deadline(name, Dates.convertToDateTime(by));
        } else {
            deadline = new Deadline(name, by);
        }


        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            this.items.add(deadline);
            this.len++;
            this.saveAll();
            System.out.println(Greeting.linebreak);
            UI.printMessage("Got it. I've added this task:",this.items.get(this.len-1).showTaskinList(),
                    "Now you have " + String.valueOf(len) + " tasks in this list");
        } catch (IOException e) {
            this.items = copy;
            this.len--;
            UI.printFileError();
        }


    }

    /**
     * This method add a Todo task to the item list.
     *
     * @param newitem the string name of the todo task.
     */
    @SuppressWarnings("unchecked")
    public void addTodo(String newitem) {
        if (newitem.equals("")) {
            UI.printMessage("Please enter an item");
            return;
        }
        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            this.items.add(new ToDo(newitem));
            this.len++;
            this.saveAll();
            UI.printMessage("Got it. I've added this task:",this.items.get(this.len-1).showTaskinList(),
                    "Now you have " + String.valueOf(len) + " tasks in this list");

        } catch (IOException e) {
            this.items = copy;
            this.len--;
            UI.printFileError();
        }
    }
    /**
     * This method add an Event task to the item list.
     *
     * @param newitem the string name of the event task.
     * @param from the string name of the start date.
     * @param to the string name of the end date.
     */
    @SuppressWarnings("unchecked")
    public void addEvent(String newitem, String from, String to) {

        if (newitem.equals("")) {
            UI.printMessage("Please enter an item");
            return;
        }

        if (from.equals("")) {
            UI.printMessage("Please enter a start date");
            return;
        }

        if (to.equals("")) {
            UI.printMessage("Please enter an end date");
            return;
        }
        Event event;
        if(Dates.checkDateinput(from) && Dates.checkDateinput(to))  {
            event = new Event(newitem, Dates.convertToDateTime(from), Dates.convertToDateTime(to));
        } else {
            event = new Event(newitem, from, to);
        }
        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            this.items.add(event);
            this.len++;
            this.saveAll();
            UI.printMessage("Got it. I've added this task:", this.items.get(this.len-1).showTaskinList()
            ,"Now you have " + String.valueOf(len) + " tasks in this list");
        } catch (IOException e) {
            this.items = copy;
            this.len--;
            UI.printFileError();
        }


    }

    /**
     * This method print out the items currently in the list
     *
     */
    public void showitems() {
        if (this.len <= 0) {
            UI.printMessage("No item in the list.");
            return;
        }
        UI.printList(this.items);
    }

    /**
     * This method mark the event at a specified index as done
     *
     * @param index the index of the task to be marked done
     */


    public void markDone(int index) {
        int i = index - 1;
        if (i < 0 || i >= this.len) {
            UI.NoSuchTaskError();
            return;
        }
        try {
            this.items.get(i).setDone();
            this.saveAll();
            UI.printMessage("Nice! I've marked this task as done:", this.items.get(i).showTaskinList());
        } catch (IOException e) {
            this.items.get(i).setUndone();
            UI.printFileError();
        }

    }

    /**
     * This method mark the event at a specified index as undone
     *
     * @param index the index of the task to be marked undone
     */

    public void markUndone(int index) {
        int i = index - 1;
        if (i < 0 || i >= this.len) {
            UI.NoSuchTaskError();
            return;
        }

        try {
            this.items.get(i).setUndone();
            this.saveAll();
            UI.printMessage("OK, I've marked this task as not done yet:",this.items.get(i).showTaskinList());
        } catch (IOException e) {
            this.items.get(i).setDone();
            UI.printFileError();

        }

    }

    /**
     * This method delete the event at a specified index.
     *
     * @param index the index of the task to be deleted.
     */
    @SuppressWarnings("unchecked")
    public void delete(int index) {
        if(this.len <= 0) {
            UI.printMessage("Nothing to Delete");
            return;
        }
        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            String todelete = this.items.get(index - 1).showTaskinList();
            this.items.remove(index-1);
            this.len--;
            this.saveAll();
            UI.printMessage("Noted. I've removed this task:", todelete);
        } catch (IOException e) {
            this.items = copy;
            this.len++;
            UI.printFileError();
        }



    }

    public void saveAll() throws IOException{
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < this.items.size(); i++) {
                if(i == this.items.size() - 1) {
                    writer.write(this.items.get(i).printList());
                } else {
                    writer.write(this.items.get(i).printList() + "\n");
                }

            }
            writer.close();
    }








}
