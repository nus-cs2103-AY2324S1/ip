//import java.util.ArrayList;
//import java.util.Scanner;
//public class Duke {
//    private static final String BYE = " Bye. Hope to see you again soon!";
//    private int count = 0;
//    private static final String LINE = "____________________________________________________________";
//    private static final String MESSAGE = LINE + "\n"
//            + " Hello! I'm ChatBot\n"
//            + " What can I do for you?\n"
//            + LINE + "\n";
//    private Scanner scanner = new Scanner(System.in);
//    private ArrayList<duke.task.Task> tasksList = new ArrayList<>();
//
//    public static void main(String[] args) {
//        Duke duke = new Duke();
//        duke.run();
//    }
//
//    public void taskPrint(String input) {
//        System.out.println(LINE + "\n"
//                + "Got it. I've added this task" + "\n"
//                + input + "\n" + "Now you have " + this.count
//                + " tasks in this list." + "\n" + LINE);
//    }
//
//    public void deletePrint(String input) {
//        System.out.println(LINE + "\n"
//                + "Noted. I've removed this task: " + "\n"
//                + input + "\n" + "Now you have " + this.count
//                + " tasks in this list." + "\n" + LINE);
//    }
//
//    public void todo(String input) throws duke.exception.DukeException {
//        tasksList.add(new duke.task.Todo(input.replace("todo", "")));
//        count++;
//        taskPrint(tasksList.get(count - 1).toString());
//    }
//
//    public void deadline(String input) throws duke.exception.DukeException {
//        String[] s = input.replace("deadline ", "").split(" /by ");
//            tasksList.add(new duke.task.Deadline(s[0], s[1]));
//            count++;
//            taskPrint(tasksList.get(count - 1).toString());
//    }
//
//    public void event(String input) throws duke.exception.DukeException {
//        String[] s = input.replace("event ", "").split(" /from | /to ");
//            tasksList.add(new duke.task.Event(s[0], s[1], s[2]));
//            count++;
//            taskPrint(tasksList.get(count - 1).toString());
//    }
//
//    public void delete(String input) throws duke.exception.DukeException {
//        int i = Integer.parseInt(input.replace("delete ", "")) - 1;
//        if (i >= 0 && i < tasksList.size()) {
//            count--;
//            deletePrint(tasksList.get(i).toString());
//            tasksList.remove(i);
//        } else {
//            System.out.println("Invalid task number. Please enter a valid task number to delete.");
//        }
//    }
//
//    public void listOfTasks() {
//        for (int i = 0; i < count; i++) {
//            System.out.println((i + 1) + "." + tasksList.get(i).toString());
//        }
//    }
//
//    public void run() {
//        System.out.println(MESSAGE);
//
//        try {
//            duke.storage.Storage storageFile = new duke.storage.Storage();
//            tasksList = storageFile.readFromFile();
//            count = tasksList.size();
//            while (true) {
//                try {
//                    String input = scanner.nextLine();
//                    System.out.println(LINE);
//                    if (input.equalsIgnoreCase("bye")) {
//                        storageFile.writeToFile(tasksList);
//                        System.out.println(BYE);
//                        System.out.println(LINE);
//                        scanner.close();
//                        break;
//                    } else if (input.equalsIgnoreCase("list")) {
//                        System.out.println("Here are the tasks in your list:");
//                        if (count == 0) {
//                            System.out.println("\t You currently have no tasks!");
//                        } else {
//                            listOfTasks();
//                        }
//                    } else if (input.startsWith("mark")) {
//                        int i = Integer.parseInt(input.replace("mark ", "")) - 1;
//                        tasksList.get(i).markDone();
//                    } else if (input.startsWith("unmark")) {
//                        int i = Integer.parseInt(input.replace("unmark ", "")) - 1;
//                        tasksList.get(i).unmarkDone();
//                    } else {
//                        if (input.equalsIgnoreCase("todo")) {
//                            throw new duke.exception.DukeException("OOPS!!! The description of a todo cannot be empty.");
//                        } else if (input.equalsIgnoreCase("deadline")) {
//                            throw new duke.exception.DukeException("OOPS!!! The description of a deadline cannot be empty.");
//                        } else if (input.equalsIgnoreCase("event")) {
//                            throw new duke.exception.DukeException("OOPS!!! The description of an event cannot be empty.");
//                        }
//                        if (input.startsWith("todo")) {
//                            todo(input);
//                        } else if (input.startsWith("event")) {
//                            event(input);
//                        } else if (input.startsWith("deadline")) {
//                            deadline(input);
//                        } else if (input.startsWith("delete")) {
//                            delete(input);
//                        } else {
//                            throw new duke.exception.DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                        }
//                    }
//                } catch (duke.exception.DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        } catch(duke.exception.DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner scanner;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public void run() {
        this.ui.greet();
        try {
            this.storage.init();
            this.tasks = this.storage.readFromFile();
        } catch (DukeException e) {
            this.ui.errorPrint(e);
        }
        while (true) {
            try {
                String input = this.scanner.nextLine();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                if (input.split(" ")[0].equals("bye")) {
                    this.scanner.close();
                    break;
                }
            } catch (DukeException e) {
                this.ui.errorPrint(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}