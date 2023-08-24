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
    public static Instruction parse(String userInput, TaskList taskList) {
        
        String[] formattedUserInput = userInput.split(" ", 2);

        switch (formattedUserInput[0]) {
        case "bye":
            if (formattedUserInput.length > 1 && !formattedUserInput[1].isEmpty()) {
                return null;
            } else {
                return new Instruction.Exit();
            }
        case "list":
            if (formattedUserInput.length > 1 && !formattedUserInput[1].trim().isEmpty()) {
                return null;
            } else {
                return new Instruction.List(taskList);
            }
        case "mark":
            if (formattedUserInput.length == 1) {
                return null;
            } else {
                try {
                    Integer index = Integer.parseInt(formattedUserInput[1]);
                    if (index > taskList.size() || index <= 0) {
                        return null;
                    } else {
                        return new Instruction.Mark(index, taskList);
                    }
                } catch (NumberFormatException n) {
                    return null;
                }
            }
        case "unmark":
            if (formattedUserInput.length == 1) {
                return null;
            } else {
                try {
                    Integer index = Integer.parseInt(formattedUserInput[1]);
                    if (index > taskList.size() || index <= 0) {
                        return null;
                    } else {
                        return new Instruction.Unmark(index, taskList);
                    }
                } catch (NumberFormatException n) {
                    return null;
                }
            }
        case "todo":
            if(formattedUserInput.length == 1){
                return null;
            } else {
                return new Instruction.Add(new Todo(formattedUserInput[1]), taskList);
            }
        case "deadline":
            if (formattedUserInput.length == 1) {
                return null;
            } else {
                String[] temp = formattedUserInput[1].split(" /by ", 2);
                if (temp.length == 1) {
                    return null;
                } else {
                    return new Instruction.Add(new Deadline(temp[0], temp[1]), taskList);
                }
            }
        case "event":
            if (formattedUserInput.length ==1) {
                return null;
            } else {
                String[] temp = formattedUserInput[1].split(" /from ", 2);
                if (temp.length == 1) {
                    return null;
                } else {
                    String[] dates = temp[1].split(" /to ", 2);
                    if (dates.length == 1) {
                        return null;
                    } else {
                        return new Instruction.Add(new Event(temp[0], dates[0], dates[1]), taskList);
                    }
                }
            }
        default:
            return null;
        }
    }
}
