import java.util.*;

public class Puke {
    public static String separator = "____________________________________________________________";
    public static String errorMessage = "Unfortunately, the circumstances preceding this has necessitated that I issue and apology for the input that I have received is unrecognised.";
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
            String command = sc.next();
            System.out.println(separator);
            if (command.equals("bye")) {
                if (!sc.nextLine().isEmpty()) {
                    System.out.println(errorMessage);
                    System.out.println(separator);
                    continue;
                }
                break;
            } else if (command.equals("list")) {
                if (!sc.nextLine().isEmpty()) {
                    System.out.println(errorMessage);
                    System.out.println(separator);
                    continue;
                }
                System.out.println("Here is the collection of items, previously designated to be known as Tasks, that you have inputted over a previous unspecified period of time\n" +
                        "that may or may not require urgent attention, but will nevertheless necessitate some level of action within an either\n" +
                        "indicated or not indicated time period.");
                int i = 1;
                for (Task s : list) {
                    System.out.println(String.format("%d. %s", i, s.toString()));
                    i++;
                }
                System.out.println(separator);
            } else if (command.equals("mark")) {
                int index = sc.nextInt();
                if (!sc.nextLine().isEmpty()) {
                    System.out.println(errorMessage);
                    System.out.println(separator);
                    continue;
                }
                try {
                    list.get(index - 1).mark();
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("I have been made aware of your desire to indicate that the task numbered " + index +
                        " has been since been achieved as of the time at which you hve stipulated as so.");
                System.out.println(separator);
            } else if (command.equals("unmark")) {
                int index = sc.nextInt();
                if (!sc.nextLine().isEmpty()) {
                    System.out.println(errorMessage);
                    System.out.println(separator);
                    continue;
                }
                try {
                    list.get(index - 1).unmark();
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("Very well. I have acknowledged your request to unmark the task of specified index as having been completed and\n" +
                        "will now proceed to set said task of specified index to be considered as having not yet been completed.");
                System.out.println(separator);
            } else if (command.equals("todo")) {
                try {
                    list.add(new ToDo(sc.nextLine()));
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("Understood. I have hereby created a task known to require doing at a future time but with no such time being specified and inserted it into " +
                        "the overall collection of said tasks that require action.\n" +
                        "Here is a display of the added deadline task: " + list.get(list.size() - 1) + "\n" +
                        "You now, in total, have " + list.size() + " of these tasks recorded within said collection.");
                System.out.println(separator);
            } else if (command.equals("deadline")) {
                try {
                    list.add(new Deadline(sc.nextLine().split("/")));
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("Understood. I have hereby created a task known to require doing at a future time alongside the stipulated time that you have indicated and inserted it into " +
                        "the overall collection of these tasks that require action.\n" +
                        "Here is a display of the added deadline task: " + list.get(list.size() - 1) + "\n" +
                        "You now, in total, have " + list.size() + " of these tasks recorded within said collection.");
                System.out.println(separator);
            } else if (command.equals("event")) {
                try {
                    list.add(new Event(sc.nextLine().split("/")));
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("Understood. I have hereby created a task known to require doing for a set period of time alongside this stipulated duration that you have indicated and inserted it into " +
                        "the overall collection of these tasks that require action.\n" +
                        "Here is a display of the added deadline task: " + list.get(list.size() - 1) + "\n" +
                        "You now, in total, have " + list.size() + " of these tasks recorded within said collection.");
                System.out.println(separator);
            } else if (command.equals("delete")) {
                Task hold;
                try {
                    int index = sc.nextInt();
                    hold = list.get(index - 1);
                    if (!sc.nextLine().isEmpty()) {
                        System.out.println(errorMessage);
                        System.out.println(separator);
                        continue;
                    }
                    list.remove(index - 1);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                    continue;
                }
                System.out.println("I have acknowledged your request to have the task allocated to the specific index at which you have mentioned removed from the collection of all\n" +
                        "such tasks, colloquially known as your To Do list.\n" +
                        "The task in question that has been deleted is: " + hold + "\n" +
                        "As of this current moment, there are a total of " + list.size() + " occurrences of tasks in your list.");
                System.out.println(separator);
            } else {
                System.out.println("Unfortunately, the circumstances preceding this has necessitated that I issue and apology for the input that I have received is unrecognised.");
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
    protected Task(String tag, String description) throws PukeException {
        this.tag = tag;
        this.description = description;
        this.done = false;
        if (tag.isEmpty() || description.isEmpty()) {
            throw new PukeException();
        }
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String write() {
        int checked = 0;
        if (this.done) {
            checked = 1;
        }
        return String.format("%s|%d|%s", this.tag, checked , this.description);
    }

    @Override
    public String toString() {
        String status = "[ ]";
        if (done) {
            status = "[X]";
        }
        return String.format("%s%s %s", this.tag, status, this.description);
    }
}

class ToDo extends Task {
    private final static String tag = "[T]";

    public ToDo(String desc) throws PukeException {
        super(tag, desc);
    }
}

class Deadline extends Task {
    private final static String tag = "[D]";
    private final String date;

    public Deadline(String[] all) throws PukeException {
        super(tag, all[0]);
        this.date = all[1].split("by ")[1];
        if (this.date.isEmpty()) {
            throw new PukeException();
        }
    }

    public static Deadline construct(String desc, String date) throws PukeException {
        String[] container = new String[2];
        container[0] = desc;
        container[1] = "by: " + date;
        return new Deadline(container);
    }

    @Override
    public String write() {
        return super.write() + this.date;
    }

    public String toString() {
        return super.toString() + "(by: " + this.date + ")";
    }
}

class Event extends Task {
    private static final String tag = "[E]";
    private final String from;
    private final String to;

    public Event(String[] all) throws PukeException {
        super(tag, all[0]);
        this.from = all[1].split("from ")[1];
        this.to = all[2].split("to ")[1];
        if (this.from.isEmpty() || this.to.isEmpty()) {
            throw new PukeException();
        }
    }

    public static Event construct(String desc, String from, String to) throws PukeException {
        String[] container = new String[3];
        container[0] = desc;
        container[1] = "from: " + from;
        container[2] = "to: " + to;
        return new Event(container);
    }

    @Override
    public String write() {
        return super.write() + this.from + "|" + this.to;
    }

    public String toString() {
        return super.toString() + "(from: " + this.from + " " +
                "to: " + this.to + ")";
    }
}

class PukeException extends Exception {
    public PukeException() {
        super("Unfortunately, the circumstances preceding this has necessitated that I issue and apology for the input that I have received is unrecognised.");
    }
}

class DataHandler {
    public static Task translate(String input) throws PukeException {
        String[] split = input.split("|");
        Task output;
        if (split[0].equals("T")) {
            output = new ToDo(split[2]);
        } else if (split[0].equals("D")) {
            output = Deadline.construct(split[2], split[3]);
        } else if (split[0].equals("E")) {
            output = Event.construct(split[2], split[3], split[4]);
        } else {
            throw new PukeException();
        }

        if (split[1].equals("0")) {
            output.unmark();
        } else if (split[1].equals("1")) {
            output.mark();
        } else {
            throw new PukeException();
        }

        return output;
    }
}