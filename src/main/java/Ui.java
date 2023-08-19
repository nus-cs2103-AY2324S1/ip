public class Ui {
    private static final String DIVIDER = "__________________________________";

    public static void greet(String name) {
        printLogo();
        System.out.println("Hi there! I'm " + name);
        System.out.println("How can I help you today?");
        printDivider();
    }

    public static void exit() {
        System.out.println("Bye. Have a nice day!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printLogo() {
        String logo = "++      ++      ++\n" +
                "||      ||      ||\n" +
                "| +----+ |      ||\n" +
                "| +----+ |      ||\n" +
                "||      ||      ||\n" +
                "++      ++      ++\n";
        System.out.println(logo);
    }
}
