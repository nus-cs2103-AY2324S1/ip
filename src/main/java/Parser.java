public class Parser {

    public Parser() {

    }

    public void parse(String input) {

    }

    public boolean isMark(String input) {
        char[] charArray = input.toCharArray();
        if (charArray[0] == 'm' && charArray[1] == 'a' && charArray[2] == 'r' && charArray[3] == 'k'
                && Character.isWhitespace(charArray[4]) && Character.isDigit(charArray[5])) {
            return true;
        }
        return false;
    }

    public int getMarkDigit(String input) {
        char[] charArray = input.toCharArray();
        return Character.getNumericValue(charArray[5]);
    }

    public boolean isDelete(String input) {
        char[] charArray = input.toCharArray();
        if (charArray[0] == 'd' && charArray[1] == 'e' && charArray[2] == 'l' && charArray[3] == 'e' &&
                charArray[4] == 't' && charArray[5] == 'e' && Character.isWhitespace(charArray[6]) &&
                Character.isDigit(charArray[7])) {
            return true;
        }
        return false;
    }

    public int getDeleteDigit(String input) {
        char[] charArray = input.toCharArray();
        return Character.getNumericValue(charArray[7]);
    }
}
