import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> userList;
    private FileStorage fileStorage;
    public Duke(String filePath) {
        this.fileStorage = new FileStorage(filePath);
        try {
            //System.out.println("here");
            this.userList = fileStorage.read();
        } catch (DukeException e) {
            //System.out.println("new userlist");
            this.userList = new ArrayList<>();
            System.out.println("File Empty");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textFile = scanner.nextLine();
        new Duke(textFile).run();
   }

   public void run() {
       //System.out.print(fileStorage.getFile().exists());
       //System.out.print(userList.size());
       final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
       final String NAME = "CathyTheChattyCat";
       String lineBreak = "\n_________________________________________\n";
       System.out.println(lineBreak + "Hello! I'm " + NAME);
       System.out.printf("What can I do for you?" + lineBreak);

       String message;
       //ArrayList<Task> userList = new ArrayList<>();
       Scanner userInput = new Scanner(System.in);
       message = userInput.nextLine();

       while (!message.equalsIgnoreCase("bye")) {
           Task task = new Task(message);
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
                   System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
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
                   System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
               }
               task.notTask();
               task.isValid();
               //userList.remove(task);
           }
           //deleting task
           if (message.startsWith("delete")) {
               int taskIndex = Integer.parseInt(message.substring(7)) - 1;
               if (taskIndex >= 0 && taskIndex < userList.size()) {
                   Task removing = userList.get(taskIndex);
                   System.out.println(lineBreak + "Noted. I've removed this task:");
                   System.out.println("  " + removing);
                   userList.remove(removing);
                   System.out.println("Now you have " + userList.size() + " tasks in the list" + lineBreak);
               } else {
                   System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
               }
               task.notTask();
               task.isValid();
           }
           // it is a task then
           try {
               if (message.startsWith("todo")) {
                   String info = message.substring(5);
                   if (info.isEmpty()) {
                       task.hasError();
                       throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                   }
                   task = new Todo(info);
                   task.isValid();
               }
               if (message.startsWith("deadline")) {
                   String info = message.substring(9);
                   String[] split = info.split("/by");
                   if (split.length != 2) {
                       task.hasError();
                       throw new DukeException("☹ OOPS!!! The description of a deadline is invalid.");
                   }
                   task = new Deadline(split[0], split[1]);
                   task.isValid();
               }
               if (message.startsWith("event")) {
                   String info = message.substring(6);
                   String[] split = info.split("/from | /to ");
                   if (split.length != 3) {
                       task.hasError();
                       throw new DukeException("☹ OOPS!!! The description of a event is invalid.");
                   }
                   task = new Event(split[0], split[1], split[2]);
                   task.isValid();
               }
           } catch (DukeException e) {
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
       } catch (DukeException e) {
           System.out.println("Duke Writing Error");;
       }
       userInput.close();
   }
}

