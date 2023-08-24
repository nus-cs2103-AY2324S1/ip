import java.util.Scanner;
public class Duke {
    private int numofList = 0;
    private String[] list = new String[100];
    private void displayList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < numofList; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }
    private void addList(String Input) {
        list[numofList] = Input;
        numofList++;
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + Input);
        System.out.println("____________________________________________________________");
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String Input = scanner.nextLine();
        while(!Input.equals("bye")) {
            if (Input.equals("list")) {
                displayList();
            } else {
                addList(Input);
            }

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
        System.out.printf(Introduction);
        duke.run();
        System.out.printf(Exit);
    }
}
