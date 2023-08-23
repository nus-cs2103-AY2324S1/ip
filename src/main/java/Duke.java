public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }

    private static void greet() {
        System.out.println("Hello. I am Luxion. \nWhat can I do for you?\n");
        exit();
    }

    private static void exit() {
        System.out.println("Bye. See you soon.");
    }

    private static void nextCommand(String command) {
        
    }
}
