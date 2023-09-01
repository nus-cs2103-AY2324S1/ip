package main;
import java.util.Scanner;
import mypackage.CustomList;
import mypackage.Deadline;
import mypackage.Database;
import mypackage.Event;
import mypackage.DukeException;
import mypackage.ToDo;
import mypackage.CommandType;
import mypackage.Global;

public class Duke {
    private static final String chatBotName = "CHAD CCP";
    private static CustomList list;
    private static final Database database = new Database();
    
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        Scanner scanner = new Scanner(System.in);
        dukeInstance.greetUser();
        try {
            database.loadOrCreateFile();
            list = database.readData();
        } catch (DukeException e) {
            System.out.println(Global.LINE);
            System.out.println(e);
            System.out.println(Global.LINE);
        }

    while (true) {
        String command = scanner.nextLine();
        CommandType commandType = CommandType.getCommandType(command);

        switch (commandType) {
        case BYE:
            dukeInstance.goodBye();
            scanner.close();
            return; 
        case LIST:
            list.printList();
            break;
        case MARK:
            try {
                list.markAsDone(command, database);
            } catch (DukeException e) {
                System.out.println(Global.LINE);
                System.out.println(e);
                System.out.println(Global.LINE);
            }
            break;
        case UNMARK:
            int unmarkIndex = Integer.parseInt(command.substring(7));
            list.markAsUndone(unmarkIndex, database);
            break;
        case TODO:
            try {
                list.addTask(createToDoTask(command), database);
            } catch (DukeException e) {
                System.out.println(Global.LINE);
                System.out.println(e);
                System.out.println(Global.LINE);
            }
            break;
        case DEADLINE:
            list.addTask(new Deadline(command.substring(9)), database);
            break;
        case EVENT:
            list.addTask(new Event(command.substring(6)), database);
            break;
        case DELETE:
            int deleteIndex = Integer.parseInt(command.substring(7));
            list.deleteTask(deleteIndex, database);
            break;
        case UNKNOWN:
            System.out.println(new DukeException("I'm sorry, but I don't know what that means :-("));
            System.out.println(Global.LINE);
            break;
        }
    }
}

    public void greetUser() {
        System.out.println(Global.LINE);
        String logo = " ____        _        \n"
               + "|  _ \\ _   _| | _____ \n"
               + "| | | | | | | |/ / _ \\\n"
               + "| |_| | |_| |   <  __/\n"
               + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm " + Duke.chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(Global.LINE);
    }

    // public void echoUserAndAddToList(String command) {
    //     System.out.println(Global.LINE);
    //     list.add(new Task(command));
    //     System.out.println("added: " + command);
    //     System.out.println(Global.LINE);
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
        System.out.println(Global.LINE);
    }

}
