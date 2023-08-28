import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
    /**
     * This method add a Deadline task to the item list.
     *
     * @param name the string name of the Deadline task.
     * @param by the string of the time the task ends.
     */
    @SuppressWarnings("unchecked")
    public void addDeadline(String name, String by) {

        if (name.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (by.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an end date");
            System.out.println(Greeting.linebreak);
            return;
        }
        Deadline deadline = new Deadline(name, by);

        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            this.items.add(deadline);
            this.len++;
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("Got it. I've added this task:");
            System.out.println(items.get(this.len - 1).showTaskinList());
            System.out.println("Now you have " + String.valueOf(len) + " tasks in this list");
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items = copy;
            this.len--;
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
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
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }
        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {


            this.items.add(new ToDo(newitem));
            this.len++;
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("Got it. I've added this task:");
            System.out.println(this.items.get(this.len-1).showTaskinList());
            System.out.println("Now you have " + String.valueOf(len) + " tasks in this list");
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items = copy;
            this.len--;
            System.out.println(e.getMessage());
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
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
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (from.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter a start date");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (to.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter a end date");
            System.out.println(Greeting.linebreak);
            return;
        }

        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            this.items.add(new Event(newitem, from ,to));
            this.len++;
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("Got it. I've added this task:");
            System.out.println(this.items.get(this.len-1).showTaskinList());
            System.out.println("Now you have " + String.valueOf(len) + " tasks in this list");
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items = copy;
            this.len--;
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
        }


    }

    /**
     * This method print out the items currently in the list
     *
     */
    public void showitems() {
        if (this.len <= 0) {
            System.out.println(Greeting.linebreak);
            System.out.println("No item in the list.");
            System.out.println(Greeting.linebreak);
            return;
        }
        System.out.println(Greeting.linebreak);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.len; i++) {
            String index = String.valueOf(i + 1);
            System.out.println(index + ". " + this.items.get(i).showTaskinList());
        }
        System.out.println(Greeting.linebreak);
    }

    /**
     * This method mark the event at a specified index as done
     *
     * @param index the index of the task to be marked done
     */


    public void markDone(int index) {
        int i = index - 1;
        if (i < 0 || i >= this.len) {
            System.out.println(Greeting.linebreak);
            System.out.println("No such Task");
            System.out.println(Greeting.linebreak);
            return;
        }
        try {
            this.items.get(i).setDone();
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.items.get(i).showTaskinList());
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items.get(i).setUndone();
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
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
            System.out.println(Greeting.linebreak);
            System.out.println("No such Task");
            System.out.println(Greeting.linebreak);
            return;
        }

        try {
            this.items.get(i).setUndone();
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.items.get(i).showTaskinList());
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items.get(i).setDone();
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
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
            System.out.println(Greeting.linebreak);
            System.out.println("Nothing to delete");
            System.out.println(Greeting.linebreak);
            return;
        }
        ArrayList<Task> copy = (ArrayList<Task>) this.items.clone();
        try {
            String todelete = this.items.get(index - 1).showTaskinList();
            this.items.remove(index-1);
            this.len--;
            this.saveAll();
            System.out.println(Greeting.linebreak);
            System.out.println("Noted. I've removed this task:");
            System.out.println(todelete);
            System.out.println(Greeting.linebreak);
        } catch (IOException e) {
            this.items = copy;
            this.len++;
            System.out.println(Greeting.linebreak);
            System.out.println("Something wrong with the list file");
            System.out.println(Greeting.linebreak);
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

    public void loadAll() throws FileNotFoundException {
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
                this.items.add(newtask);
                this.len++;
                continue;
            }
            if (line.charAt(0) == 'D') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String by = para[3];
                Deadline newtask = new Deadline(description, by);
                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                this.items.add(newtask);
                this.len++;
                continue;
            }


            if(line.charAt(0) == 'E') {
                String[] para = line.split(" \\| ", 4);
                String description = para[2];
                String block = para[3];
                String[] fromTo = block.split(" to ", 2);
                Event newtask = new Event(description, fromTo[0], fromTo[1]);
                if (!para[1].equals("0")) {
                    newtask.setDone();
                }
                this.len++;
                this.items.add(newtask);
            }
        }
        s.close();
    }




}
