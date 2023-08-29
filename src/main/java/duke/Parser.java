package duke;

import duke.*;

public class Parser {

    //use this class to process the input
    public static Command parse(String input, TaskList lst) throws InvalidInputException {
        String[] words = input.split("\\s+");
        String command = words[0];

        switch (command) {
            case "bye":
                return new ExitTask();
            case "list":
                return new ListTask();
            case "delete":
                return new DeleteTask(Integer.valueOf(words[1]));
            case "mark":
                return new DoneTask(Integer.valueOf(words[1]) - 1);
            case "unmark":
                return new UndoTask(Integer.valueOf(words[1]) - 1);
            case "todo":
                try {
                    String name = words[1];
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new ToDo(input.substring(5));
                return new AddTask(task);
            case "deadline":
                try {
                    String nameOfDeadline = words[1];
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String deadlineInfo = input.substring(9);
                String[] stringInfo = deadlineInfo.split(" /by ");
                Task deadline = new Deadline(stringInfo[0], stringInfo[1]);
                return new AddTask(deadline);
            case "event":
                try {
                    String nameOfEvent = words[1];
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidInputException("OOPS!!! The description of an event cannot be empty.");
                }
                String eventInfo = input.substring(6);
                String[] eventDetails = eventInfo.split(" /from ");
                String[] timings = eventDetails[1].split(" /to ");
                Task event = new Event(eventDetails[0], timings[0], timings[1]);
                return new AddTask(event);
            default:
                throw new InvalidInputException("OOPS! I'm sorry I don't know what that means\nPlease insert a valid type first");
        }
    }
}
