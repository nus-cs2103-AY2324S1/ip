import java.util.Scanner;
public class Duke {
    public static String line = "----------------------------------------------------\n";
    public static String format_response(String response) {
        return response + "\n" + line;
    }
    public static void greet() {
        System.out.println(line);
        System.out.println(format_response(
                "Hello I'm Project54\n" +
                "What can I do for you?\n"
        ));
    }
    public static void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon\n"
        ));
    }
    public static void main(String[] args) {

        greet();
        bye();

    }
}
