/**
 * A parser class whose main method is the parse method which
 * helps to parse user input and determine whether the input is valid
 * or not.
 */
public class Parser {

    /**
     * Parses the given input and checks if it is valid and if it is
     * returns an instruction correspoding to it.
     *
     * @param userInput The string from user to parse.
     * @param taskList The taskList of the bot to perform instructions on.
     * @return Returns an instance of Instruction if parse was successful and null if not.
     */
    public static Instruction parse(String userInput, TaskList taskList) throws DukeException {
        
        String[] formattedUserInput = userInput.split(" ", 2);
        InstructionEnum instruction = InstructionEnum.getInstructionEnum(formattedUserInput[0]);
        if (instruction == null) {
            throw new DukeException("Unrecognized instruction. Try again.");
        }
        switch (instruction) {
        case BYE:
            if (formattedUserInput.length > 1 && !formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of bye must be empty. Try again.");
            } else {
                return new Instruction.Exit();
            }
        case LIST:
            if (formattedUserInput.length > 1 && !formattedUserInput[1].trim().isEmpty()) {
                throw new DukeException("The description of list must be empty. Try again.");
            } else {
                return new Instruction.List(taskList);
            }
        case MARK:
            if (formattedUserInput.length == 1 || formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of mark cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(formattedUserInput[1]);
                    if (index > taskList.size() || index <= 0) {
                        throw new DukeException("The index is not a valid index. Try again.");
                    } else {
                        return new Instruction.Mark(index, taskList);
                    }
                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case UNMARK:
            if (formattedUserInput.length == 1 || formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of unmark cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(formattedUserInput[1]);
                    if (index > taskList.size() || index <= 0) {
                        throw new DukeException("The index is not a valid index. Try again.");
                    } else {
                        return new Instruction.Unmark(index, taskList);
                    }
                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case DELETE:
            if (formattedUserInput.length == 1 || formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of delete cannot be empty. Try again.");
            } else {
                try {
                    Integer index = Integer.parseInt(formattedUserInput[1]);
                    if (index > taskList.size() || index <= 0) {
                        throw new DukeException("The index is not a valid index. Try again.");
                    } else {
                        return new Instruction.Delete(index, taskList);
                    }
                } catch (NumberFormatException n) {
                    throw new DukeException("The index is not a valid index. Try again.");
                }
            }
        case TODO:
            if(formattedUserInput.length == 1 || formattedUserInput[1].isEmpty()){
                throw new DukeException("The description of a todo cannot be empty. Try again.");
            } else {
                return new Instruction.Add(new Todo(formattedUserInput[1]), taskList);
            }
        case DEADLINE:
            if (formattedUserInput.length == 1 || formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty. Try again.");
            } else {
                String[] temp = formattedUserInput[1].split(" /by ", 2);
                if (temp.length == 1 || temp[1].isEmpty() || temp[0].isEmpty()) {
                    throw new DukeException("Insufficient number of arguments for a deadline. Try again.");
                } else {
                    return new Instruction.Add(new Deadline(temp[0], temp[1]), taskList);
                }
            }
        case EVENT:
            if (formattedUserInput.length ==1 || formattedUserInput[1].isEmpty()) {
                throw new DukeException("The description of an event cannot be empty. Try again.");
            } else {
                String[] temp = formattedUserInput[1].split(" /from ", 2);
                if (temp.length == 1 || temp[1].isEmpty() || temp[0].isEmpty()) {
                    throw new DukeException("Insufficient number of arguments for an event. Try again.");
                } else {
                    String[] dates = temp[1].split(" /to ", 2);
                    if (dates.length == 1 || dates[0].isEmpty() || dates[1].isEmpty()) {
                        throw new DukeException("Insufficient number of arguments for an event. Try again.");
                    } else {
                        return new Instruction.Add(new Event(temp[0], dates[0], dates[1]), taskList);
                    }
                }
            }
        default:
            //program will not reach here.
            return null;
        }

    }
}
