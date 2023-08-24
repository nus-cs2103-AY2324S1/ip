import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void addDeadline(ArrayList<Task> list, String command) {
        String splitCommand[] = command.split(" ");

        try {
            boolean hasBy = false;

            if (splitCommand.length == 1) {
                throw new DukeException("Deadline Argument Empty");
            }

            for (int i = 0; i < splitCommand.length; i++) {
                if (splitCommand[i].equals("/by")) {
                    hasBy = true;
                    System.out.println(i);
                    if (i == 1) {
                        throw new DukeException("There is no description");
                    }
                    if (i == splitCommand.length - 1) {
                        throw new DukeException("Please insert the deadline");
                    }
                }
            }

            if (hasBy == false) {
                throw new DukeException("Please insert deadline is /by when");
            }

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Got it, I've added this deadline.");
        String currStr = command.substring(9);
        int dateIndex = currStr.indexOf("/");
        String date = currStr.substring(dateIndex + 4);
        String description = currStr.substring(0, dateIndex);
        Task deadlineTask = new Deadline(description, date);
        list.add(deadlineTask);
        System.out.println(deadlineTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}