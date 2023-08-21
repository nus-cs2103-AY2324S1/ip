import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Botty";
        String echo = "";
        Scanner scanner = new Scanner(System.in);
        greet(name);
        while (true) {
            echo = scanner.next();
            if (echo == "bye") {
                break;
            } else {
                System.out.println(echo);
            }
        }
        bye();
    }
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
