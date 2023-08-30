import java.util.List;
import java.util.stream.Stream;

public class Ui {
    private static String LINE_SEPARATOR = "    ----------------------------------------------------------------------";

    public static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    public static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    public static <T> void respond(Stream<T> messages) {
        System.out.println(LINE_SEPARATOR);
        messages.forEach(message -> System.out.println(String.format("     %s",  message.toString())));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }
}
