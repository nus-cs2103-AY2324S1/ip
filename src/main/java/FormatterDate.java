import java.time.format.DateTimeFormatter;

public enum FormatterDate {
    basicInput(DateTimeFormatter.ISO_LOCAL_DATE),
    slashInput("dd/MM/yyyy"),
    basicOutput("MMM dd yyyy");

    DateTimeFormatter formatter;

    FormatterDate(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    FormatterDate(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
