public class Jarvis {
    public static void main(String[] args) {
        greet();
        exit();
    }
    public static void greet() {
        String logo = "  _____     _       ____  __        __ ____    ____           \n"
                + " |_   _|   / \\     |  _ \\ \\ \\      / /|_  _|  / ___|      \n"
                + "   | |    / _ \\    | |_) | \\ \\    / /   | |  | (___   \n"
                + "   | |   / / \\ \\   | ___/   \\ \\  / /    | |   \\___ \\          \n"
                + "  _| |  / ----- \\  | |\\ \\    \\ \\/ /    _| |_  ____) |    \n"
                + " |___/ /_/     \\_\\ |_| \\_\\    \\__/    |_____||_____/             \n";

        horizontal_line_printer(50);
        System.out.println("Hello from Jarvis\n" + logo);
        System.out.println("What can I do for you, sir?");
    }
    public static void exit() {
        horizontal_line_printer(50);
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void horizontal_line_printer(int lenth) {
        System.out.println("-".repeat(lenth));
    }
}
