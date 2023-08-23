public class Glen {
    static final String HORLINE = "____________________________________________________________\n";    
    public static void main(String[] args) {
        System.out.print(intro());
        System.out.println(exit());
    }

    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    static String exit() {
        String exitTxt = "Bye. Hope to see you again soon!\n";
        return exitTxt + HORLINE;
    }
}
