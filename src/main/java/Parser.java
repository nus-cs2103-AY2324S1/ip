public class Parser {

    public static String parseCommand(String input) throws DukeInvalidCommandException {
        String[] strArr;
        try {
            strArr = input.split(" ", 2);
            return strArr[0];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(input);
        }
    }

    public static String parseInfo(String input) throws DukeMissingArgumentException {
        try {
            String[] strArr = input.split(" ", 2);
            return strArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
