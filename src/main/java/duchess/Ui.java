package duchess;

/**
 * A class to deal with the UI. This generally involves printing things to the console.
 */
public class Ui {
    /**
     * Formats text in a consistent formatting in Duchess style.
     *
     * @param s - the string to be formatted.
     * @return    the formatted text.
     */
    public static String duchessFormat(String s) {
        return String.format("[D]: %s \n", s);
    }

    /**
     * Formats greeting to the user and returns.
     *
     * @return the formatted greeting.
     */
    public static String printGreeting() {
        String s = "";
        s += Ui.duchessFormat("(^O^)／ Heya~");
        s += Ui.duchessFormat("I am ~~Duchess~~!!");
        s += Ui.duchessFormat("What can I do for you!! o_O");
        return s;
    }

    /**
     * Returns farewell/exit message.
     *
     * @return the formatted farewell message.
     */
    public static String printFarewell() {
        return Ui.duchessFormat("Bye bye!! Hope to see you again (＾▽＾)");
    }
}
