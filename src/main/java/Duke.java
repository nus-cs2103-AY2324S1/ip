import java.util.Scanner;
import java.lang.NumberFormatException;
public class Duke {
    private int numofList = 0;
    private Task[] list = new Task[100];

    private void displayList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < numofList; i++) {
            System.out.println((i + 1) + "." + list[i]);
        }
        System.out.println("____________________________________________________________");
    }
    private void addList(String Input) throws DukeException {
        int index= Input.indexOf(" ");
        String taskDescription = Input.substring(index + 1);
        if (index == -1) {
            if (taskDescription.equalsIgnoreCase("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("deadline")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("event")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
        String type = Input.substring(0, index);
        if (type.equalsIgnoreCase("todo")) {
            list[numofList] = new Todo(taskDescription);
        } else if (type.equalsIgnoreCase("deadline")) {
            int byIndex = taskDescription.indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("Deadline must contain /by");
            }
            list[numofList] = new Deadline(taskDescription);
        } else if (type.equalsIgnoreCase("event")) {
            int fromIndex = taskDescription.indexOf("/from");
            if (fromIndex == -1) {
                throw new DukeException("Event must contain /from");
            }
            int toIndex = taskDescription.substring(fromIndex).indexOf("/to");
            if (toIndex == -1) {
                throw new DukeException("Event must contain /to");
            }
            list[numofList] = new Event(taskDescription);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        numofList++;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list[numofList - 1]);
        System.out.println(" Now you have " + numofList + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String Input = scanner.nextLine();
        while(!Input.equalsIgnoreCase("bye")) {
            try {
                String[] splittedInput = Input.split(" ");
                if (Input.equals("list")) {
                    displayList();
                } else if (splittedInput[0].equalsIgnoreCase("mark") && splittedInput.length == 2 &&
                        isInteger((splittedInput[1]))) {
                    if (Integer.parseInt(splittedInput[1]) <= 0 || Integer.parseInt(splittedInput[1]) > numofList) {
                        throw new DukeException("Task Not Found :'(");
                    }
                    list[Integer.parseInt(splittedInput[1]) - 1].mark();
                } else if (splittedInput[0].equalsIgnoreCase("unmark") && splittedInput.length == 2 &&
                        isInteger((splittedInput[1]))) {
                    if (Integer.parseInt(splittedInput[1]) <= 0 || Integer.parseInt(splittedInput[1]) > numofList) {
                        throw new DukeException("Task Not Found :'(");
                    }
                    list[Integer.parseInt(splittedInput[1]) - 1].unmark();
                } else {
                    addList(Input);
                }
            }
            catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" ☹ OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
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
