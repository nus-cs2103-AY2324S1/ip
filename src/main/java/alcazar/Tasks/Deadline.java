package alcazar.Tasks;
import alcazar.Tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String formattedByDate;



    public Deadline(String description, String inputByDate) {
        super(description);
        System.out.println(inputByDate);
        if (inputByDate == null || inputByDate.isEmpty()) {
            throw new IllegalArgumentException("The date string cannot be empty or null");
        }
        this.formattedByDate = parseDate(inputByDate.trim());

    }

//    public void initialiseDate() {
//        try {
//            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            this.byDate = LocalDate.parse(by, dateFormat);
//        } catch (Exception e) {
//            this.isException = true;
//        }
//    }
    public String parseDate(String inputByDate) {
        String dateAfterFormatting = "";
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(inputByDate, inputFormat);
            dateAfterFormatting = date.format(outputFormat);
        } catch (DateTimeParseException e) {
            dateAfterFormatting = inputByDate;
        }
        return dateAfterFormatting;

    }
    /**
     * Used to evaluate String form of a Deadline
     * @return String form of a Deadline
     */
    @Override
    public String toString() {
//        String deadlineDate = "";
//        if (isException == true) {
//            DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            deadlineDate = this.byDate.format(newDateFormat);
//        } else {
//            DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            deadlineDate = this.byDate.format(newDateFormat);;
//        }
//        String result = "";
//
//        result = "[D]" + super.toString() + " (by: " + deadlineDate + " )";
//
//        return result;
//        String deadlineDate;
//        if (isException) {
//            deadlineDate = by;
//        } else {
//            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            deadlineDate = this.byDate.format(dateFormat);
//        }
        return "[D]" + super.toString() + " (by: "
                + formattedByDate + ")";
    }
}
