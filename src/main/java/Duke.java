import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    public static void main(String[] args) {
        Greeting.greet();
        Scanner sc = new Scanner(System.in);
        ItemList items = new ItemList();
        do {
            try {
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
                if(echo.startsWith("deadline ")) {

                    Pattern deadlinepattern = Pattern.compile( "deadline (.*?)/by (.*)");
                    matcher = deadlinepattern.matcher(echo);
                    if(matcher.matches()) {
                        String task = matcher.group(1);
                        String by = matcher.group(2);
                        items.addDeadline(task, by);
                        continue;
                    } else {
                        throw new DeadlineException();
                    }

                }

                if(echo.startsWith("todo ")) {
                    Pattern todopattern = Pattern.compile( "todo (.*)");
                    matcher = todopattern.matcher(echo);
                    if(matcher.matches()) {
                        String task = matcher.group(1);
                        items.addTodo(task);
                        continue;
                    } else {
                        throw new ToDoException();
                    }
                }

                if(echo.startsWith("event ")) {
                    Pattern eventpattern = Pattern.compile( "event (.*?)/from (.*?) /to (.*)");
                    matcher = eventpattern.matcher(echo);
                    if(matcher.matches()) {
                        String task = matcher.group(1);
                        String from = matcher.group(2);
                        String to = matcher.group(3);
                        items.addEvent(task, from, to);
                        continue;
                    } else {
                        throw new EventException();
                    }
                } else {
                    throw new DukeException();
                }
            } catch (EventException e){
                System.out.println(Greeting.linebreak);
                System.out.println(e.toString());
                System.out.println(Greeting.linebreak);
            } catch (ToDoException e) {
                System.out.println(Greeting.linebreak);
                System.out.println(e.toString());
                System.out.println(Greeting.linebreak);
            } catch (DeadlineException e) {
                System.out.println(Greeting.linebreak);
                System.out.println(e.toString());
                System.out.println(Greeting.linebreak);
            } catch (DukeException e) {
                System.out.println(Greeting.linebreak);
                System.out.println(e.toString());
                System.out.println(Greeting.linebreak);
            }





        } while (true);
        Greeting.bye();

    }
}
