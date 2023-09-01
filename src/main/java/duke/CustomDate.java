package duke;

import parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomDate {

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

    public String addZeroFront(String text) {
        return text.length() == 1 ? 0 + text : text;
    }

    public String formatTime(String str) {
        return str.substring(0, 2) + ":" + str.substring(2, 4);
    }

}
