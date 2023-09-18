package duke;

import java.time.format.DateTimeFormatter;

/**
 * Formats the date that the bot understand to what the user wants
 * will add more possible ways in the future
 */
public enum FormatterDate {
    basicInput(DateTimeFormatter.ISO_LOCAL_DATE),
    slashInput("dd/MM/yyyy"),
    basicOutput("MMM dd yyyy");

    public DateTimeFormatter formatter;

    /**
     * Takes in a string pattern and will convert the string pattern
     * to the formatter required
     * 
     * @param pattern string to be taken in
     */
    FormatterDate(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * Takes in a formatter
     * 
     * @param formatter formatter that is taken in
     */
    FormatterDate(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
