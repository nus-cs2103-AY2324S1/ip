public class Duke {

    // An greeting display everytime entering the program
    private static void OnEnter () {
        System.out.println("____________________________________________");
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    // AN exit display everytime exits the program
    private static void OnExit() {
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        OnEnter();
        OnExit();
    }
}
