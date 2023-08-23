public class Duke {
    public static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        printIntro();
        printBye();
        
    }

    private static String breakLine() {
        System.out.println("____________________________________________________________ \n");
        return null;
    } 

    private static String printIntro() {
        breakLine();
        System.out.println("Hi Master! I'm your personal assistant: JARVIS! \n" + 
                            "How can I serve you today? \n");
        breakLine();
        return null;
    }

    private static String printBye() {
        System.out.println("Bye Master. It has been my honour to serve you!\n");
        breakLine();
        return null;
    }
}
