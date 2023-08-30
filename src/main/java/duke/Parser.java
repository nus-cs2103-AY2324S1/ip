package duke;

import java.util.ArrayList;

public class Parser {
    public static String parseCommand(String s) {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        String check3 = "";
        if (s.length() >= 4) {
            check1 = str.substring(0, 4);
            if (check1.equals("todo")) return check1;
            if (check1.equals("find")) return check1;
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 8);
            if (check2.equals("deadline")) return check2;
        }
        if (s.length() >= 5) {
            check3 = str.substring(0, 5);
            if (check3.equals("event")) return check3;
        }
        return null;
    }
}
