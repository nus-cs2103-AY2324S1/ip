public class Juke {
    private final static String SEPARATOR = "\n-----------------------------------------------------------------------\n";
    private final static String LOGO =
            "       __      __      \n" +
            "      / /_  __/ /_____ \n" +
            " __  / / / / / //_/ _ \\\n" +
            "/ /_/ / /_/ / ,< /  __/\n" +
            "\\____/\\__,_/_/|_|\\___/ ";
    private final static String INTRO = "What's up! My name is Juke (J|ava D|uke) and I will be your personal \n" +
            "assistant for today! How may I assist you?";
    private final static String EXIT = "Goodbye!";

    public static void main(String[] args) {
        printIntroduction();
        printExit();
    }

    private static void printIntroduction() {
        StringBuilder builder = new StringBuilder();
        builder.append(LOGO);
        builder.append(SEPARATOR);
        builder.append(INTRO);
        builder.append(SEPARATOR);

        System.out.print(builder);
    }

    private static void printExit() {
        StringBuilder builder = new StringBuilder();
        builder.append(EXIT);
        builder.append(SEPARATOR);

        System.out.print(builder);
    }
}
