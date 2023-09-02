public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private String by;
    private String d1;

    int i = 0;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        String date = "";
        for (i = 0; i < by.length(); i++) {
            if (by.charAt(i) == ' ') {
                break;
            }
            date += by.charAt(i);
        }
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate d = LocalDate.parse(date);
            d1 = d.format(df);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
>>>>>>> branch-Level-7
    }

    /**
     * Used to evaluate String form of a Deadlien
     * @return String form of a Deadline
     */
    @Override
    public String toString() {
<<<<<<< HEAD
        return "[D]" + super.toString() + " (by: " + by + ")";
=======
        String s = "";
        by = by.trim();
        s = "[D]" + super.toString() + " (by: " + d1 + " " + by.substring(i) + ")";


        return s;
>>>>>>> branch-Level-7
    }
}
