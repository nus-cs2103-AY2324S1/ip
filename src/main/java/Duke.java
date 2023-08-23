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
                continue;
            }

            Pattern deadlinepattern = Pattern.compile( "deadline (.*?)/by (.*)");
            matcher = deadlinepattern.matcher(echo);
            if(matcher.matches()) {
                String task = matcher.group(1);
                String by = matcher.group(2);
                items.addDeadline(task, by);
                continue;
            }

            Pattern todopattern = Pattern.compile( "todo (.*)");
            matcher = todopattern.matcher(echo);
            if(matcher.matches()) {
                String task = matcher.group(1);
                items.addTodo(task);
                continue;
            }

            Pattern eventpattern = Pattern.compile( "event (.*?)/from (.*?) /to (.*)");
            matcher = eventpattern.matcher(echo);
            if(matcher.matches()) {
                String task = matcher.group(1);
                String from = matcher.group(2);
                String to = matcher.group(3);
                items.addEvent(task, from, to);
                continue;
            } else {
                System.out.println(Greeting.linebreak);
                System.out.println("Invalid input");
                System.out.println(Greeting.linebreak);

            }


        } while (true);
        Greeting.bye();

    }
}
