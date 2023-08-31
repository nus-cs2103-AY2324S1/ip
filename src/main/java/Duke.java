import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

//    private Scanner sc = new Scanner(System.in);
//    private final TaskManager taskManager = new TaskManager();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        boolean isExit = false;
        ui.showWelcome();
        Parser parser = new Parser();
        while(!isExit) {
            try {
                String input = ui.readCommand();
                parser.parseAndExecute(input, tasks, ui, storage);
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

//    public void validateInput(String input) throws DukeException {
//        if (input.equals("todo") || input.equals("deadline") || input.equals("event") || input.equals("mark")
//                || input.equals("unmark") || input.equals("delete")) {
//            throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
//        }
//
//        if (input.startsWith("deadline ") && !input.contains("/by")) {
//            throw new DukeException("☹ OOPS!!! The description of a deadline must contain /by.");
//        }
//
//        if (input.startsWith("event ") && !input.contains("/from") && !input.contains("/to")) {
//            throw new DukeException("☹ OOPS!!! The description of a event must contain /from and /to.");
//        }
//
//        if (!input.startsWith("todo ") && !input.startsWith("deadline ") && !input.startsWith("event ")
//                && !input.equals("list") && !input.equals("bye") && !input.startsWith("mark ")
//                && !input.startsWith("unmark ") && !input.startsWith("delete ")) {
//            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//    }
//    public void printDone(int index) {
//        printLine();
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(taskManager.getTask(index));
//        printLine();
//    }
//
//    public void printUndone(int index) {
//        printLine();
//        System.out.println("OK, I've marked this task as not done yet:");
//        System.out.println(taskManager.getTask(index));
//        printLine();
//    }
//
//    public void printAdded(Task task) {
//        System.out.println("Got it. I've added this task:");
//        System.out.println(task);
//        System.out.println("Now you have " + taskManager.getSize() + " tasks in the list.");
//    }
//
//    public void addTask(String input) {
//        printLine();
//        if (input.startsWith("todo ")) {
//            Task task = new Todo (input.substring(5));
////            list.add(task);
//            taskManager.addTask(task);
//            printAdded(task);
//        } else if (input.startsWith("deadline ")) {
//            String[] arr = input.substring(9).split(" /by ");
//            Task task = new Deadline(arr[0], arr[1]);
////            list.add(task);
//            taskManager.addTask(task);
//            printAdded(task);
//        } else if (input.startsWith("event ")) {
//            String[] arr = input.split("\\s*/from\\s*|\\s*/to\\s*");
//            Task task = new Event(arr[0], arr[1], arr[2]);
////            list.add(task);
//            taskManager.addTask(task);
//            printAdded(task);
//        }
//        printLine();
//    }
//
//    public void deleteTask(int index) {
//        printLine();
//        System.out.println("Noted. I've removed this task:");
//        System.out.println(taskManager.getTask(index));
//        taskManager.deleteTask(index);
//        System.out.println("Now you have " + taskManager.getSize() + " tasks in the list.");
//        printLine();
//    }
//
//    public void printList() {
//        printLine();
//        System.out.println("Here are the tasks in your list:");
//        taskManager.printList();
//        printLine();
//    }
//    public void printGreeting() {
//        System.out.println("Hello! I'm Max\n" + "What can I do for you?");
//    }
//
//    public void printBye() {
//        printLine();
//        System.out.println("Bye. Hope to see you again soon!");
//        printLine();
//    }
//
//    public void printLine() {
//        System.out.println("____________________________________________________________");
//    }
}

