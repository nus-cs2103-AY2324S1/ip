package duke;

/**
 * Parser class that takes in input from user and parse it accordingly to a command using switch
 */
public class Parser {

    /**
     * Parse function to parse input from user
     * @param input String input from the user
     * @return Command object depending on result of the parse
     * @throws InvalidInputException exception whenever input given is unable to be parsed
     */
    public static Command parse(String input) throws InvalidInputException {
        String[] words = input.split("\\s+");
        String command = words[0];

        switch(command) {
        case "bye":
            return new ExitTask();
        case "list":
            return new ListTask();
        case "delete":
            return new DeleteTask(Integer.valueOf(words[1]) - 1);
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
            String deadlineTime;
            try {
                deadlineTime = stringInfo[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!!! Please provide a valid deadline");
            }
            Task deadline = new Deadline(stringInfo[0], deadlineTime);
            return new AddTask(deadline);
        case "event":
            try {
                String nameOfEvent = words[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!!! The description of an event cannot be empty.");
            }
            String eventInfo = input.substring(6);
            String[] eventDetails = eventInfo.split(" /from ");
            String[] timings;
            try {
                timings = eventDetails[1].split(" /to ");
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("Please provide a valid timing");
            }
            String startTime;
            String endTime;
            try {
                startTime = timings[0];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!!! Please provide a valid start time");
            }
            try {
                endTime = timings[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!!! Please provide a valid end time");
            }
            Task event = new Event(eventDetails[0], startTime, endTime);
            return new AddTask(event);
        case "find":
            String keyword;
            try {
                keyword = words[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!!! Please specify what you want to find");
            }
            return new FindTask(keyword);
        case "update":
            int indexToUpdate;
            try {
                indexToUpdate = Integer.valueOf(words[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("OOPS!! Please specify what you want to update with");
            }
            String updateDetails = input.substring(9);
            return new UpdateTask(indexToUpdate, updateDetails);
        default:
            throw new InvalidInputException("OOPS! I'm sorry I don't know what that means"
                    + "\nPlease insert a valid type first");
        }
    }
}
