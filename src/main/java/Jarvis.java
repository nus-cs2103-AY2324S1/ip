public class Jarvis {
    /**
     * ASCII Art Generated from http://patorjk.com/software/taag/
     */
    private static final String logo = 
"    ██  █████  ██████  ██    ██ ██ ███████ \n" +
"    ██ ██   ██ ██   ██ ██    ██ ██ ██      \n" +
"    ██ ███████ ██████  ██    ██ ██ ███████ \n" +
"██  ██ ██   ██ ██   ██  ██  ██  ██      ██\n" +
"█████  ██   ██ ██   ██   ████   ██ ███████ \n";

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
                            "\n" +
                            logo +
                            "\n" +
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
