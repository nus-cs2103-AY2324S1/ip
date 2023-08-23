import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Greeting.greet();
        Scanner sc = new Scanner(System.in);
        ItemList items = new ItemList();
        do {
            String echo = sc.next();
            if (echo.equals("bye")) {
                break;
            }

            if (echo.equals("list")) {
                items.showitems();
            } else {
                items.additems(echo);
            }

        } while (true);
        Greeting.bye();

    }
}
