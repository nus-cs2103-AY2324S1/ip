package bellcurvegod.ui;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = "\n" +
            "  ____       _ _    _____                        _____           _ \n" +
            " |  _ \\     | | |  / ____|                      / ____|         | |\n" +
            " | |_) | ___| | | | |    _   _ _ ____   _____  | |  __  ___   __| |\n" +
            " |  _ < / _ \\ | | | |   | | | | '__\\ \\ / / _ \\ | | |_ |/ _ \\ / _` |\n" +
            " | |_) |  __/ | | | |___| |_| | |   \\ V /  __/ | |__| | (_) | (_| |\n" +
            " |____/ \\___|_|_|  \\_____\\__,_|_|    \\_/ \\___|  \\_____|\\___/ \\__,_|";

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println(LOGO);
        showLine();
        System.out.println("Hello! I'm Bell Curve God.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public static String getLine() {
        return HORIZONTAL_LINE;
    }

    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
