public class Duke {
    static String name = "Atlas";
    public static void main(String[] args) {
        greet();
        exit();
    }

    public static void greet() {
        String logo =
                "        _______ _                _____ \n" +
                        "     /\\|__   __| |        /\\    / ____|\n" +
                        "    /  \\  | |  | |       /  \\  | (___  \n" +
                        "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n" +
                        "  / ____ \\| |  | |____ / ____ \\ ____) |\n" +
                        " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.printf("Hello, I'm %s!%n", Duke.name);
        System.out.println("What would you like me to do today?");
        printHorizontalLine();
    }

    public static void exit() {
        System.out.println("Goodbye!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        int consoleWidth = 80;
        String line = "_".repeat(consoleWidth);
        System.out.println(line);
    }
}
