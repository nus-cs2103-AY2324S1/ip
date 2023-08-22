import java.util.*;

public class Duke {
    public static String separator = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<Task>(100);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| |_| | | | | |/ / _ \\\n"
                + "| ___/| |_| |    | __/\n"
                + "| |    \\__,_|_|\\_\\___|\n"
                + "|_|";
        Scanner sc = new Scanner(System.in);
        System.out.println("Salutations! I hereby would like to inform you that my identity is that of\n" + logo +
                "\nAn exceedingly verbose conversation simulation program.");
        System.out.println(separator);
        while (true) {
            String input = sc.nextLine();
            String[] components = input.split(" ");
            String command = components[0];
            System.out.println(separator);
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                int i = 1;
                for (Task s : list) {
                    System.out.println(String.format("%d. %s", i, s.toString()));
                    i++;
                }
                System.out.println(separator);
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(components[1]);
                list.get(index - 1).mark();
                System.out.println("I have been made aware of your desire to indicate that the task numbered " + index +
                        " has been since been achieved as of the time at which you hve stipulated as so.");
                System.out.println(separator);
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(components[1]);
                list.get(index).unmark();
                System.out.println("Very well. I have acknowledged your request to unmark the task of specified index as having been completed and\n" +
                        "will now proceed to set said task of specified index to be considered as having not yet been completed.");
                System.out.println(separator);
            } else {
                list.add(new Task("-", input));
                System.out.println(String.format("added: %s", input));
                System.out.println(separator);
            }
        }
        System.out.println("It appears that the user has decided to close the program as indicated by the command of which this is the function being issued and therefore,\n" +
                "I shall bid thee farewell and wish thee great fortune in your future endeavors.");
    }
}

class Task {
    protected String tag;
    protected boolean done;
    protected String description;
    protected Task(String tag, String description) {
        this.tag = tag;
        this.description = description;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String status = "[ ]";
        if (done) {
            status = "[X]";
        }
        return String.format("%s %s", status, this.description);
    }
}