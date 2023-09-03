package duke;

public class Ui {
    final static String LINE = "────────────────────────────────────────────────────";

    /**
     * Greets the user.
     */
    public static void greet() {
        String logo = "___.                 \n"
                + "\\_ |__ ___.__. ____  \n"
                + " | __ <   |  |/ __ \\ \n"
                + " | \\_\\ \\___  \\  ___/ \n"
                + " |___  / ____|\\___  >\n"
                + "     \\/\\/         \\/ \n";
        System.out.println("\nHello! I'm \n" + logo);
        System.out.println("How can I help you? \n" + LINE);
    }

    /**
     * Says bye.
     */
    public static void bye() {
        System.out.println("Bye (actually hehe). Hope to see you again!\n" + LINE);
    }
}
