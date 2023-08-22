package services;

public class Format {
    private final static String horizontalLine = "_________________________________________________________________________\n";

    public static void print(String message) {
        String messageWithHorizontalLine = horizontalLine + message + "\n" + horizontalLine;
        System.out.print(messageWithHorizontalLine);
    }

    public static String format(String message) {
        String messageWithHorizontalLine = horizontalLine + message + "\n" + horizontalLine;
        return messageWithHorizontalLine;
    }

    public static String format(String formattedMessage, Object... args) {
        String messageWithHorizontalLine = horizontalLine + formattedMessage + "\n" + horizontalLine;
        return String.format(messageWithHorizontalLine, args);
    }
}
