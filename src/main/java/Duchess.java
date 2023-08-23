public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * Prints greeting to the user and returns.
     */
    private static void printGreeting() {
        System.out.println("(^O^)／ Heya~");
        System.out.println("I am ~~Duchess~~!!");
        System.out.println("What can I do for you!! o_O");
    }

    /**
     * Prints farewell/exit message.
     */
    private static void printFarewell() {
        System.out.println("Bye bye!! Hope to see you again (＾▽＾)");
    }

    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        // + "|  _ \\ _   _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| |   <  __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        
        Duchess.printGreeting();
        Duchess.printFarewell();
        
    }
}
