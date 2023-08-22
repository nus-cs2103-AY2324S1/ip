package java.doctor;
import java.doctor.Builder.*;
import java.doctor.Command.*;
import java.doctor.Invoker.Invoker;
import java.util.Scanner;


public class Duke {
    private Invoker invoker = new Invoker();
    public static void main(String[] args) {
        System.out.println("Hello! I'm Doctor101");
        System.out.println("What can I do for you?");
        
        Duke duke = new Duke();
        duke.invoker.setCommand("list", new List());
        duke.invoker.setCommand("mark", new Mark());
        duke.invoker.setCommand("unmark", new Unmark());
        duke.invoker.setCommand("todo", new AddTask(new TodoBuilder()));
        duke.invoker.setCommand("deadline", new AddTask(new DeadlineBuilder()));
        duke.invoker.setCommand("event", new AddTask(new EventBuilder()));

       Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                return;
            }
            duke.invoker.execute(input);
            System.out.println("____________________________________________________________");
        }
    }
}