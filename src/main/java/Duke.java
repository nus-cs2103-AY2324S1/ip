import java.util.Scanner;
public class Duke {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String Input = scanner.nextLine();
        while(!Input.equals("bye")) {
            String output = "____________________________________________________________\n" +
                    " " + Input + "\n" +
                    "____________________________________________________________\n";
            System.out.println(output);
            Input = scanner.nextLine();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String Introduction = "____________________________________________________________\n" +
                        " Hello! I'm FootyCouch\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n";
        String Exit =   "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
        System.out.println(Introduction);
        duke.run();
        System.out.println(Exit);
    }
}
