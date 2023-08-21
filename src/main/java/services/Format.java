package services;

public class Format {
    private final static String horizontalLine = "____________________________________________________________\n";

    public static void print(String message) {
        String messageWithHorizontalLine = horizontalLine + message + "\n" + horizontalLine;
        System.out.println(messageWithHorizontalLine);
    }
}
