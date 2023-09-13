package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE_BREAK = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";


    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the given message between horizontal lines
     *
     * @param msg String to output
     */
    public String displayMessage(String msg) {
        String indentedMsg = indent(msg);
        System.out.println(LINE_BREAK);
        System.out.println(indentedMsg);
        System.out.println(LINE_BREAK);

        return indentedMsg;
    }

    public String displayError(String msg) {
        return displayMessage("☹ OOPS!!! \n" + msg);
    }

    public String readNextInput() {
        return sc.nextLine();
    }

    private static String indent(String msg) {
        return msg.replaceAll("(?m)^", "\t");
    }
}
