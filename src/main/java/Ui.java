import types.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }
    private static final String line = "______________________________\n";


    public static void intro(ArrayList<Task> list) {
        String logo = " ____             _\n"
                + "|  _ \\           | |\n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\ |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/\n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        System.out.println(line
                + "Hi Barbie! Hi Ken!\n"
                + "\nI'm\n"
                + logo
                + "\n\nThis is the list of things you have today!");
        list.forEach(System.out::println);
        System.out.println("\nWhat can I do for you?\n" + line);
        System.out.println("[you]:");

    }

    public static void exit() {
        System.out.println("Bye Barbie! Bye Ken!");
    }

    public static void taskAdded(Task task) {
        System.out.println("\tGot you barbie! I've added this task to your Barbie list:\n"
                + "\t " + task);
    }

    public static void mark(Task task) {
        System.out.println("\t Nice! I've marked this task as done:\n"
                + "\t " + task + "\n"
                + "\t" + line);
    }

    public static void unmark(Task task) {
        System.out.println("\t Alright! I've marked this task as not done yet:\n"
                + "\t " + task + "\n"
                + "\t" + line);
    }

    public static void del() {
        System.out.println("\t Deletion success! I've deleted this task off your list.");
    }

    public static void barbie() {
        System.out.println("\t" + line
                + "\t [barbie]:\n");
    }

    protected static void listTasks(ArrayList<Task> list,int indexNumber) {
        if (indexNumber == 0) {
            System.out.println("Hmm.. your list looks empty. To add items, use the 'todo', 'deadline' or 'party' commands!");
        }
        // "list" command
        for (int i = 0; i < indexNumber; i++) {
            int itemNumber = i + 1;
            System.out.println("\t" + itemNumber + ". " + list.get(i));
        }
    }




}
