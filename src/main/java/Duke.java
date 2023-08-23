public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();

        duke.line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        duke.line();
        System.out.println(" Bye. Hope to see you again soon!");
        duke.line();
    }
    public void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }
}
