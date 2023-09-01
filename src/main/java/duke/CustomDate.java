package duke;

import parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Converts the string input of date into date and time objects.
 */
public class CustomDate {

    /**
     * Returns the LocaleDateTime from a string.
     * @param text Date, time in string.
     * @return LocaleDateTime from the string.
     */
    public LocalDateTime strToDateTime(String text) {
        Parser p = new Parser();
        ArrayList<String> texts = p.splitter(text, "/");
        ArrayList<String> yearNTime = p.splitter(texts.get(2), " ");
        String year = addZeroFront(yearNTime.get(0));
        String mon = addZeroFront(texts.get(1));
        String day = addZeroFront(texts.get(0));
        String time = formatTime(yearNTime.get(1));
        LocalDateTime dOne = LocalDateTime.parse(year + "-" + mon + "-" + day + "T" + time);
        return dOne;
    }

    /**
     * Add zero to the front of a string that is 1 in length.
     * @param text Text to be edited.
     * @return Text with a zero in front.
     */
    public String addZeroFront(String text) {
        return text.length() == 1 ? 0 + text : text;
    }

    /**
     * Returns formatted time string.
     * @param str Time in string.
     * @return Formatted time string.
     */
    public String formatTime(String str) {
        return str.substring(0, 2) + ":" + str.substring(2, 4);
    }

}
