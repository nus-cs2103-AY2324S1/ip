package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class to store the Deadline Task subclass
 */
public class Deadline extends Task {

    /**
     * field inputs to split the name of the command
     * field date stores the date placed into the Deadline
     */
    private String[] inputs;
    private LocalDate date;

    /**
     * constructor for the Deadline class
     * @param name takes in the command to be processed
     * @throws DukeException when the input of the Deadline is wrong
     */

    public Deadline(String name) throws DukeException {
        //consturctor will take in te "/by format also"
        super(name.substring(0 , name.indexOf("/") - 1));
        this.ogName = name;
        String d = name.substring(name.indexOf("/") + 4); // 2/12/2019 1800
        this.inputs = name.split("/");
        this.type = "Deadline";
        String[] dtime = d.split(" ");
        if (this.inputs.length < 2) {
            throw new DukeException(" Deadline has no end date!");
        }
        this.date = LocalDate.parse(dtime[0]);
    }

    /**
     * @return String format for the Deadline class
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
