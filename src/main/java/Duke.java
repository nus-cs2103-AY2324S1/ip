import java.util.Scanner;
import java.lang.NumberFormatException;
public class Duke {
    private int numofList = 0;
    private Task[] list = new Task[100];

    private void displayList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < numofList; i++) {
            System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
        }
        System.out.println("____________________________________________________________");
    }
    private void addList(String Input) {
        list[numofList] = new Task(Input);
        numofList++;
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + Input);
        System.out.println("____________________________________________________________");
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String Input = scanner.nextLine();
        while(!Input.equalsIgnoreCase("bye")) {
            String[] splittedInput = Input.split(" ");
            if (Input.equals("list")) {
                displayList();
            } else if (splittedInput[0].equalsIgnoreCase("mark") && splittedInput.length == 2 &&
                        isInteger((splittedInput[1])) && Integer.parseInt(splittedInput[1]) > 0 &&
                        Integer.parseInt(splittedInput[1]) <= numofList) {
                list[Integer.parseInt(splittedInput[1]) - 1].mark();
            } else if (splittedInput[0].equalsIgnoreCase("unmark") && splittedInput.length == 2 &&
                    isInteger((splittedInput[1])) && Integer.parseInt(splittedInput[1]) > 0 &&
                    Integer.parseInt(splittedInput[1]) <= numofList) {
                list[Integer.parseInt(splittedInput[1]) - 1].unmark();
            } else {
                addList(Input);
            }
            Input = scanner.nextLine();
        }
    }
    private boolean isInteger(String str) {
        try {
            Integer check = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
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
