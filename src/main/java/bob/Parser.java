package bob;

/**
 * Represents a parser that deals with making sense of the user command
 */
public class Parser {

    public Parser() {

    }

    public void parse(String input) {

    }

    /**
     * Checks if the input statement is a command to mark a Task
     *
     * @param input
     * @return boolean true if input is mark
     */
    public boolean isMark(String input) {
        char[] charArray = input.toCharArray();
        if (charArray[0] == 'm' && charArray[1] == 'a' && charArray[2] == 'r' && charArray[3] == 'k'
                && Character.isWhitespace(charArray[4]) && Character.isDigit(charArray[5])) {
            return true;
        }
        return false;
    }

    /**
     * Returns the digit of the index of the Task that should be marked
     *
     * @param input
     * @return int the index of marked task
     */
    public int getMarkDigit(String input) {
        char[] charArray = input.toCharArray();
        return Character.getNumericValue(charArray[5]);
    }

    /**
     * Checks if input statement is a command to delete a Task
     *
     * @param input
     * @return boolean true if statment is a delete command
     */
    public boolean isDelete(String input) {
        char[] charArray = input.toCharArray();
        if (charArray[0] == 'd' && charArray[1] == 'e' && charArray[2] == 'l' && charArray[3] == 'e' &&
                charArray[4] == 't' && charArray[5] == 'e' && Character.isWhitespace(charArray[6]) &&
                Character.isDigit(charArray[7])) {
            return true;
        }
        return false;
    }

    /**
     * Returns the digit of the index of the Task that should be deleted
     *
     * @param input
     * @return int the number of the task to be deleted
     */
    public int getDeleteDigit(String input) {
        char[] charArray = input.toCharArray();
        return Character.getNumericValue(charArray[7]);
    }
}
