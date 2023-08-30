public class Parser {
    //later go delete, mark, unmark throw error
    public static Command parse(String command) throws AiChanException {
        //fullCommand is user's input
        // put the if-else logic to call different constructor (subclass)
        if (command.equals(ActionType.BYE.toString())) {
            return new ExitCommand();
        } else if (command.equals(ActionType.LIST.toString())){
            return new ListCommand();
        } else if (command.startsWith(ActionType.MARK.toString())){
            if (command.length() < 6) {
                throw new AiChanException("Please provide a task number.");
            }
            // get the number behind "mark "
            int taskId = Integer.parseInt(command.substring(5));
            return new MarkCommand(taskId);
        } else if (command.startsWith(ActionType.UNMARK.toString())){
            if (command.length() < 8) {
                throw new AiChanException("Please provide a task number.");
            }
            int taskId = Integer.parseInt(command.substring(7));
            return new UnmarkCommand(taskId);
        } else if (command.startsWith(ActionType.TODO.toString())){
            if (command.length() < 6) {
                throw new AiChanException("oops~ The description of a todo cannot be empty.");
            } else if (command.charAt(4) != ' ') {
                throw new AiChanException("Please leave a space behind 'todo'");
            }
            Task t = new ToDo(command.substring(5));
            return new AddCommand(t);
        } else if (command.startsWith(ActionType.DEADLINE.toString())){
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
        } else if (command.startsWith(ActionType.EVENT.toString())){
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
        } else if (command.startsWith(ActionType.DELETE.toString())){
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
}
