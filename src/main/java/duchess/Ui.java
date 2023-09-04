package duchess;

/**
 * A class to deal with the UI. This generally involves printing things to the console.
 */
public class Ui {
    /**
     * Prints text, but with a consistent formatting in Duchess style.
     *
     * @param s - the string to be printed.
     */
    public static void duchessPrint(String s) {
        System.out.println(String.format("[D]: %s", s));
    }

    /**
     * Prints greeting to the user and returns.
     */
    public static void printGreeting() {
        Ui.duchessPrint("(^O^)／ Heya~");
        Ui.duchessPrint("I am ~~Duchess~~!!");
        Ui.duchessPrint("What can I do for you!! o_O");
    }

    /**
     * Prints farewell/exit message.
     */
    public static void printFarewell() {
        Ui.duchessPrint("Bye bye!! Hope to see you again (＾▽＾)");
    }

    // Duchess-GUI

    /**
     * Returns the entered text, but with a consistent formatting in Duchess style.
     *
     * @param s - the string to be printed.
     * @return    the duchess-formatted text.
     */
    public static String duchessPrintGUI(String s) {
        return String.format("[D]: %s\n", s);
    }

    /**
     * Returns the duchess greeting to the user.
     *
     * @return the Duchess Greeting.
     */
    public static String printGreetingGUI() {
        String returnString = "";

        returnString += Ui.duchessPrintGUI("(^O^)／ Heya~");
        returnString += Ui.duchessPrintGUI("I am ~~Duchess~~!!");
        returnString += Ui.duchessPrintGUI("What can I do for you!! o_O");

        return returnString;
    }

    /**
     * Prints farewell/exit message.
     */
    public static String printFarewellGUI() {
        String returnString = "";

        returnString += Ui.duchessPrintGUI("Bye bye!! Hope to see you again (＾▽＾)");

        return returnString;
    }
}
