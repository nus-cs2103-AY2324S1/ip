package Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private DateTimeFormatter inputFormatter;
    private DateTimeFormatter outputFormatter;

    public DateTime() {
        this.inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    }

    public String formatDateTime(String dateTimeStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Invalid format";
        }
    }

}
