package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String[] inputs;
    LocalDate date;

    public Deadline(String name) throws DukeException {
        //consturctor will take in te "/by format also"
        super(name.substring(0 , name.indexOf("/") - 1));
        this.ogname = name;
        String d = name.substring(name.indexOf("/") + 4); // 2/12/2019 1800
        this.inputs = name.split("/");
        this.type = "Deadline";
        String[] dtime = d.split(" ");

        if (this.inputs.length < 2) {
            throw new DukeException("deadline has no end date!");
        }
        this.date = LocalDate.parse(dtime[0]);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+")";
    }
}
