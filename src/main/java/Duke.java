import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    public static void main(String[] args) {
        Greeting.greet();
        Scanner sc = new Scanner(System.in);
        ItemList items = new ItemList();
        do {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                break;
            }

            if (echo.equals("list")) {
                items.showitems();
                continue;
            }

            Pattern markpattern = Pattern.compile("mark (\\d+).*");
            Matcher matcher = markpattern.matcher(echo);
            if(matcher.matches()) {
                String digitString = matcher.group(1);
                int number = Integer.parseInt(digitString);
                items.markDone(number);
                continue;
            }

            Pattern unmarkpattern = Pattern.compile("unmark (\\d+).*");
            matcher = unmarkpattern.matcher(echo);
            if(matcher.matches()) {
                String digitString = matcher.group(1);
                int number = Integer.parseInt(digitString);
                items.markUndone(number);
            } else {
                items.additems(echo);
            }


        } while (true);
        Greeting.bye();

    }
}
