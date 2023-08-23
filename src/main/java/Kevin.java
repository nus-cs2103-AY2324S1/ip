public class Kevin {
    public static final String HORIZONTAL_LINE = "_".repeat(70);
    public static final String BOT_NAME = "\n" +
            " _   __ _____ _   _ _____ _   _ \n" +
            "| | / /|  ___| | | |_   _| \\ | |\n" +
            "| |/ / | |__ | | | | | | |  \\| |\n" +
            "|    \\ |  __|| | | | | | | . ` |\n" +
            "| |\\  \\| |___\\ \\_/ /_| |_| |\\  |\n" +
            "\\_| \\_/\\____/ \\___/ \\___/\\_| \\_/\n";

    public static void wrapInHorizontalLines(String str) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(str);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void echo(String command) {
        wrapInHorizontalLines(command);
    }

    public static void hello() {
        String welcomeMessage = "Hello! I'm" + BOT_NAME + "\n" + "What can I do for you?";
        wrapInHorizontalLines(welcomeMessage);
    }

    public static void bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        wrapInHorizontalLines(goodbyeMessage);
    }
    public static void main(String[] args) {
        hello();
        bye();
    }
}



