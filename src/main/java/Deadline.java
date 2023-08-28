import java.util.ArrayList;

public class Deadline extends Task{

    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
    public String toStringFile() {
        return "D | " + super.toStringFile() + "/by " + by;
    }


    public static void addDeadline(String description, ArrayList<Task> list) throws DukeException {
        String[] deadline = description.stripTrailing().split("/by ", 2);
        if (deadline[0].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadline.length == 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid deadline");
        }

        Deadline newTask = new Deadline(deadline[0], deadline[1]);
        list.add(newTask);
        System.out.println(line);
        System.out.println("Got it. I've added the Deadline:\n\t" + newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);

    }

    public static void addSavedDeadline(String description, ArrayList<Task> list, String isMarked) {
        String[] deadline = description.stripTrailing().split("/by ", 2);
        Deadline newTask = new Deadline(deadline[0], deadline[1]);
        newTask.markFromRead(isMarked);
        list.add(newTask);
    }
}
