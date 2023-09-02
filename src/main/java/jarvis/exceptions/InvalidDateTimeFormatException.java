package jarvis.exceptions;

public class InvalidDateTimeFormatException extends JarvisException{
    public InvalidDateTimeFormatException(String message) {
        super("     Please enter a correct DateTime Format Example: \n" 
                    + "     \"Nov 12 2022 1200\"" + "\n"
                    + "     \"12 Nov 2022 1200\"" + "\n"
                    + "     \"2022-11-12 1200\"" + "\n"
                    + "     \"12/11/2022 1200\"" + "\n"
                    + message);
    }
}
