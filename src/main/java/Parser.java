import java.text.ParseException;

public class Parser {

    public int parseMark(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.substring(5));
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to mark!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to mark in the format: 'mark 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to mark!");
        }
        return taskNo;
    }

    public int parseUnmark(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.substring(7));
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to unmark!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to unmark in the format: 'unmark 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to unmark!");
        }
        return taskNo;
    }

    public int parseDelete(String userInput, TaskList list) throws ParserException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.substring(7));
            if (taskNo > list.getSize() | taskNo < 1) {
                throw new InvalidTaskNumberException("Please enter valid Task No. to delete!");
            }
        } catch (NumberFormatException e) {
            throw new ParserException("Please enter valid Task No. (INTEGER) to delete in the format: 'delete 4'");
        } catch (InvalidTaskNumberException d) {
            throw new ParserException("Please enter valid Task No. to delete!");
        }
        return taskNo;
    }


}
