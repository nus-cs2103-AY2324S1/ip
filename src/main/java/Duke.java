import java.util.Scanner;
import Command.Command;
import DukeException.DukeException;
import FileStorage.FileStorage;
import List.TaskList;
import Parser.Parser;
import Tasks.Deadline;
import Ui.Ui;

/**
 * A class that the chatbot program will run from.
 */
public class Duke {

    private TaskList userList;
    private FileStorage fileStorage;
    private Ui userInterface;

    /**
     * A constructor method to initialise the bot.
     *
     * @param filePath the file that will be written or read from.
     */
    public Duke(String filePath) {
        this.userInterface = new Ui();
        this.fileStorage = new FileStorage(filePath);
        try {
            //System.out.println("here");
            userList = new TaskList(fileStorage.read());
        } catch (DukeException e) {
            //System.out.println("new userlist");
            this.userList = new TaskList();
            System.out.println("File Empty");
        }
    }

    /**
     * A method that will need the user to input what Text file they would like to use.
     *
     * @param args arguments use to start the program.
     * @throws DukeException if the file provided is invalid.
     */
    public static void main(String[] args) throws DukeException {
        System.out.println("\n \n" + "Please Input the txt file you wish to access");
        Scanner scanner = new Scanner(System.in);
        String textFile = scanner.nextLine();
        new Duke(textFile).run();
   }

    /**
     * The method that will be running the ongoing program.
     */
   public void run() {
        userInterface.showGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.readCommand();
                userInterface.showLine();
                Command c = Parser.parse(fullCommand);
                c.excute(userList, userInterface, fileStorage);
                isExit = c.isExit();
            } catch (DukeException e) {
                userInterface.showError(e.getMessage());
            } finally {
                userInterface.showLine();
            }
        }
       userInterface.closeScanner();
   }
       //System.out.print(fileStorage.getFile().exists());
       //System.out.print(userList.size());
       /*
       final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
       final String NAME = "CathyTheChattyCat";
       String lineBreak = "\n_________________________________________\n";
       System.out.println(lineBreak + "Hello! I'm " + NAME);
       System.out.printf("What can I do for you?" + lineBreak);

       String message;
       //ArrayList<Tasks.Task> userList = new ArrayList<>();
       Scanner userInput = new Scanner(System.in);
       message = userInput.nextLine();

       while (!message.equalsIgnoreCase("bye")) {
           Tasks.Task task = new Tasks.Task(message);
           // Listing things out
           if (message.equalsIgnoreCase("list")) {
               System.out.println(lineBreak);
               System.out.println("Here are the tasks in your list:");
               for (int i = 0; i < userList.size(); i++) {
                   int index = i + 1;
                   System.out.println(index + ". " + userList.get(i));
               }
               System.out.println(lineBreak);
               task.notTask();
               task.isValid();
           }
           //marking tasks
           if (message.startsWith("mark")) {
               int taskIndex = Integer.parseInt(message.substring(5)) - 1;
               if (taskIndex >= 0 && taskIndex < userList.size()) {
                   userList.get(taskIndex).markDone();
                   System.out.println(lineBreak + "Nice! I've marked this task as done:");
                   System.out.println("  " + userList.get(taskIndex) + lineBreak);
               } else {
                   System.out.println(lineBreak + "Invalid Tasks.Task Number" + lineBreak);
               }
               task.notTask();
               task.isValid();
               //userList.remove(task);
           }
           //un marking task
           if (message.startsWith("unmark")) {
               int taskIndex = Integer.parseInt(message.substring(7)) - 1;
               if (taskIndex >= 0 && taskIndex < userList.size()) {
                   userList.get(taskIndex).unmarkDone();
                   System.out.println(lineBreak + "OK, I've marked this task as not done yet:");
                   System.out.println("  " + userList.get(taskIndex) + lineBreak);
               } else {
                   System.out.println(lineBreak + "Invalid Tasks.Task Number" + lineBreak);
               }
               task.notTask();
               task.isValid();
               //userList.remove(task);
           }
           //deleting task
           if (message.startsWith("delete")) {
               int taskIndex = Integer.parseInt(message.substring(7)) - 1;
               try {
                   task = userList.deleteTask(taskIndex);
                   System.out.println(lineBreak + "Noted. I've removed this task: \n" + task);
                   System.out.println("Now you have " + userList.size() + " tasks in the list" + lineBreak);
                   task.notTask();
                   task.isValid();
               } catch (DukeException.DukeException e) {
                   System.out.println(lineBreak + e.getMessage() + lineBreak);
               }
           }
           // it is a task then
           try {
               if (message.startsWith("todo")) {
                   String info = message.substring(5);
                   if (info.isEmpty()) {
                       task.hasError();
                       throw new DukeException.DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                   }
                   task = new Tasks.Todo(info);
                   task.isValid();
               }
               if (message.startsWith("deadline")) {
                   String info = message.substring(10);
                   String[] split = info.split("/by ");
                   if (split.length != 2) {
                       task.hasError();
                       throw new DukeException.DukeException("☹ OOPS!!! The description of a deadline is invalid.");
                   }
                   try {
                       String deadDate = LocalDate.parse(split[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                       task = new Tasks.Deadline(split[0], deadDate);
                       task.isValid();
                   } catch (DateTimeException e) {
                       throw new DukeException.DukeException("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
                   }
               }
               if (message.startsWith("event")) {
                   String info = message.substring(7);
                   String[] split = info.split("/from | /to ");
                   if (split.length != 3) {
                       task.hasError();
                       throw new DukeException.DukeException("☹ OOPS!!! The description of a event is invalid.");
                   }
                   try {
                       String startDate = LocalDate.parse(split[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                       String endDate = LocalDate.parse(split[2]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                       task = new Tasks.Event(split[0], startDate, endDate);
                       task.isValid();
                   } catch (DateTimeException e) {
                       throw new DukeException.DukeException("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
                   }
               }
           } catch (DukeException.DukeException e) {
               System.out.printf(lineBreak + e.getMessage() + lineBreak);
           }
           if (!task.isItValid()) {
               System.out.println(lineBreak + UNKNOWN_COMMAND + lineBreak);
           }
           if (!task.isItErrored() && task.isItTask() && task.isItValid()) {
               System.out.println(lineBreak + "Got it. I've added this task:");
               userList.add(task);
               int size = userList.size();
               System.out.println(task);
               System.out.println("Now you have " + size + " tasks in the list." + lineBreak);
           }
           message = userInput.nextLine();
       }
       System.out.print(lineBreak + "Bye. Hope to see you again soon!" + lineBreak);
       try {
           fileStorage.write(userList);
       } catch (DukeException.DukeException e) {
           System.out.println("Duke Writing Error");;
       }
       userInput.close();
        */
}

