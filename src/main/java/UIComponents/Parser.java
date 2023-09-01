package UIComponents;

import CrackerPackage.Cracker;
import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    private String input ;


    public static Cracker.Type parseCommand(String input){
        switch(input.split(" ")[0]) {
            case "mark":
            case "unmark":
                return Cracker.Type.MARK;
            case "todo":
            case "deadline":
            case "event":
                return Cracker.Type.TASK;
            case "delete":
                return Cracker.Type.DELETE;
            case "list":
                return Cracker.Type.LIST;
            case "bye":
                return Cracker.Type.QUIT;

            default:
                return Cracker.Type.UNKNOWN;
        }
    }

    public static Task parseTask(String input) throws EmptyDescriptionException, IllegalFormatException {
        String command = input.split(" ")[0];
        switch (command.toLowerCase()){
            case "todo" :
                return new Todo(input.replaceFirst(command,""));
            case "event":
                return new Event(input.replaceFirst(command,""));
            case "deadline":
                return new Deadline(input.replaceFirst(command,""));
            default :
                System.out.println("should not be triggered");
                return null;
        }
    }

    public static int parseIndex(String input){
        return Integer.parseInt(input.replace(input.split(" ")[0], "").trim()) - 1;
    }


}