import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {

    protected String by;

    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = null;
    }

    public Deadline(String description, String by, LocalDate date) {
        super(description);
        this.by = by;
        this.date = date;
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
        Task deadlineTask;
        try {
            LocalDate d1 = LocalDate.parse(date);
            deadlineTask = new Deadline(description, date, d1);
        } catch (DateTimeParseException e) {
            deadlineTask = new Deadline(description, date);;
        }

        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write(  "D | 0 | " + description + "| " + date + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list.add(deadlineTask);
        System.out.println(deadlineTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }


    public static void readData(ArrayList<Task> list, String data) {
        String splitDeadline[] = data.split(" \\| ");

        Task deadlineTask;
        try {
            LocalDate d1 = LocalDate.parse(splitDeadline[3]);
            deadlineTask = new Deadline(splitDeadline[2] + " ", splitDeadline[3], d1);
        } catch (DateTimeParseException e) {
            deadlineTask = new Deadline(splitDeadline[2], splitDeadline[3]);
        }

        list.add(deadlineTask);
        if (splitDeadline[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }


    @Override
    public String toString() {
        if (this.date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + getDate();
        }
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}