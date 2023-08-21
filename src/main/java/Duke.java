import java.util.Scanner;
import pkg.task.*;
import pkg.command.*;


public class Duke {
    private invoker invoker = new invoker();
    public static void main(String[] args) {
        Duke duke = new Duke();
        //duke.invoker.setCommand("todo", new );
       Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                return;
            }
            duke.invoker.execute(input);
        }
    }
}
            
/* 
            // \\s means one empty space, \\d means one digit, + means one or more, () means group
            Pattern markPattern = Pattern.compile("mark\\s(\\d+)");
            if (markPattern.matcher(input).matches()) {
                index = Integer.parseInt(input.replaceAll("\\D+", ""));
                tasks[index - 1].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index - 1]);
                continue;
            } 

            Pattern unmarkPattern = Pattern.compile("unmark\\s(\\d+)");
            if (unmarkPattern.matcher(input).matches()) {
                index = Integer.parseInt(input.replaceAll("\\D+", ""));
                tasks[index - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index - 1]);
                continue;
            } 

            switch(input) {
                case "bye":
                    Bye();
                    scanner.close();
                    return;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] != null) {
                            System.out.println(i + 1 + "." + tasks[i]);
                        }
                    }
                    break;
                default:
                    System.out.println("added: " + input);
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] == null) {
                            tasks[i] = new Task(input);
                            break;
                        }
                    }
            }
            
        }   
    }

}
*/
