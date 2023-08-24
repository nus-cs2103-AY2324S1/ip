package main;
import java.util.Scanner;
import mypackage.CustomList;
import mypackage.Deadline;
import mypackage.Event;
import mypackage.DukeException;
import mypackage.ToDo;

public class Duke {
    private static final String chatBotName = "CHAD CCP";
    private static final CustomList list = new CustomList();
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Duke dukeInstance = new Duke();
        Scanner scanner = new Scanner(System.in);
        dukeInstance.greetUser();

        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                dukeInstance.goodBye();
                break;
            } else if (command.equals("list")) {
                list.printList();
            } else if (command.startsWith("mark")) {
                try {
                    list.markAsDone(command);
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e);
                    System.out.println("________________________________");
                }
            } else if (command.startsWith("unmark")) {
                int index = Integer.valueOf(command.substring(7));
                list.markAsUndone(index);
            } else if (command.startsWith("todo")) {
                try {
                    list.addTask(createToDoTask(command));
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e);
                    System.out.println("________________________________");
                }
            } else if(command.startsWith("deadline")) {
                list.addTask(new Deadline(command.substring(9)));
            } else if(command.startsWith("event")){
                list.addTask(new Event(command.substring(6)));
            } else if (command.startsWith("delete")) {
                list.deleteTask(Integer.valueOf(command.substring(7)));
            }
            else {
                //dukeInstance.echoUserAndAddToList(command);
                System.out.println(new DukeException("I'm sorry, but I don't know what that means :-("));
                System.out.println("________________________________");
            }
        }

        scanner.close();
    }

    public void greetUser() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm " + Duke.chatBotName);
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    // public void echoUserAndAddToList(String command) {
    //     System.out.println("________________________________");
    //     list.add(new Task(command));
    //     System.out.println("added: " + command);
    //     System.out.println("________________________________");
    // }

    public static ToDo createToDoTask(String command) throws DukeException{
        try {
            command.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("todo command must be followed by a space and a string. ERR: STRING INDEX OUT OF BOUNDS.");
            } 
        
            String description = command.substring(5);

            if (description.isEmpty()) {
                throw new DukeException("todo command must be followed by a space and a string. ERR: NO STRING.");
            } 
        return new ToDo(description);
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

}
