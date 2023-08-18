public class Duke {
    public static void printHorizontalLine() {
        System.out.println("------------------------------------------------------------------------------------");
    }
    public static void main(String[] args) {
        String logo = "\n" +
                "_________ .__            __    __                \n" +
                "\\_   ___ \\|  |__ _____ _/  |__/  |_  ___________ \n" +
                "/    \\  \\/|  |  \\\\__  \\\\   __\\   __\\/ __ \\_  __ \\\n" +
                "\\     \\___|   Y  \\/ __ \\|  |  |  | \\  ___/|  | \\/\n" +
                " \\______  /___|  (____  /__|  |__|  \\___  >__|   \n" +
                "        \\/     \\/     \\/                \\/       \n";
        printHorizontalLine();
        System.out.println("Hello! I'm" + logo + "\nWhat can I do for you?\n");
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
