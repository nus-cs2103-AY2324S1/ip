package aichan;

import aichan.ActionType;
import aichan.command.AddDeadlineCommand;
import aichan.command.AddEventCommand;
import aichan.command.AddToDoCommand;
import aichan.command.Command;
import aichan.command.DeleteCommand;
import aichan.command.ExitCommand;
import aichan.command.ListCommand;
import aichan.command.MarkCommand;
import aichan.command.UnmarkCommand;

public class Parser {
    public static Command parse(String input) throws AiChanException {
        // command is user's input
        // put the if-else logic to call different constructor (subclass)
        if (input == null ) {
            throw new AiChanException("Please enter command, thanks!");
        }
        String inputs[] = input.split(" ", 2);

        if (inputs[0].equals(ActionType.BYE.toString())) {
            return new ExitCommand();
        } else if (inputs[0].equals(ActionType.LIST.toString())){
            return new ListCommand();
        } else if (inputs[0].equals(ActionType.MARK.toString())){
            return new MarkCommand(strToNum(checkLen(inputs)));
        } else if (inputs[0].equals(ActionType.UNMARK.toString())){
            return new UnmarkCommand(strToNum(checkLen(inputs)));
        } else if (inputs[0].equals(ActionType.TODO.toString())){
            return new AddToDoCommand(checkLen(inputs));
        } else if (inputs[0].equals(ActionType.DEADLINE.toString())){
            return new AddDeadlineCommand(checkLen(inputs));
        } else if (inputs[0].equals(ActionType.EVENT.toString())){
            return new AddEventCommand(checkLen(inputs));
        } else if (inputs[0].equals(ActionType.DELETE.toString())){
            return new DeleteCommand(strToNum(checkLen(inputs)));
        } else {
            throw new AiChanException("oops~ I'm so sorry, but I don't know what that means :'(");
        }
    }
    
    public static String checkLen(String[] inputs) throws AiChanException {
        if (inputs.length == 1 || inputs[1].length() == 0) {
            throw new AiChanException("oops~ The description of a " + inputs[0] + " cannot be empty.");
        }
        return inputs[1];
    }

    public static int strToNum(String intStr) throws AiChanException {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new AiChanException("Please provide a task number.");
        }
    }
}
/*
if (inputs[0].equals(ActionType.BYE.toString())) {
            return new ExitCommand();
        } else if (inputs[0].equals(ActionType.LIST.toString())){
            return new ListCommand();
        } else if (inputs[0].equals(ActionType.MARK.toString())){
            if (command.length() < 6) {
                throw new AiChanException("Please provide a task number.");
            }
            // get the number behind "mark "
            int taskId = Integer.parseInt(command.substring(5));
            return new MarkCommand(taskId);
        } else if (inputs[0].equals(ActionType.UNMARK.toString())){
            if (command.length() < 8) {
                throw new AiChanException("Please provide a task number.");
            }
            int taskId = Integer.parseInt(command.substring(7));
            return new UnmarkCommand(taskId);
        } else if (inputs[0].equals(ActionType.TODO.toString())){
            if (command.length() < 6) {
                throw new AiChanException("oops~ The description of a todo cannot be empty.");
            } else if (command.charAt(4) != ' ') {
                throw new AiChanException("Please leave a space behind 'todo'");
            }
            Task t = new ToDo(command.substring(5));
            return new AddCommand(t);
        } else if (inputs[0].equals(ActionType.DEADLINE.toString())){
            if (command.length() < 10) {
                throw new AiChanException("oops~ The description of a deadline cannot be empty.");
            } else if (command.charAt(8) != ' ') {
                throw new AiChanException("Please leave a space behind 'deadline'");
            }
            // split the substring behind "deadline " into two
            String[] arr = command.substring(9).split(" /by ");
            if(arr.length < 2) {
                throw new AiChanException("Behind description, please provide the deadline after ' /by '");
            }
            Task t = new Deadline(arr);
            return new AddCommand(t);
        } else if (inputs[0].equals(ActionType.EVENT.toString())){
            if (command.length() < 7) {
                throw new AiChanException("oops~ The description of a event cannot be empty.");
            } else if (command.charAt(5) != ' ') {
                throw new AiChanException("Please leave a space behind 'event'");
            }
            // split the substring behind "event " into three elements
            String[] arr = command.substring(6).split(" /from | /to ");
            if(arr.length < 3) {
                throw new AiChanException("Behind description, " +
                        "please provide \nthe respective date/time after ' /from ' and ' /to '");
            }
            Task t = new Event(arr);
            return new AddCommand(t);
        } else if (inputs[0].equals(ActionType.DELETE.toString())){
            if (command.length() < 8) {
                throw new AiChanException("Please provide a task number.");
            }
            // get the number behind "delete"
            int taskId = Integer.parseInt(command.substring(7));
            return new DeleteCommand(taskId);
        } else {
        throw new AiChanException("oops~ I'm so sorry, but I don't know what that means :'(");
        }
    }
 */
