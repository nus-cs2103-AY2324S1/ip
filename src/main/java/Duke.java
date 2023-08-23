import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> storedList = new ArrayList<>();
        String line = "____________________________________________________________";
        String logo = "        ┏┓\n"
                + "        ┃┃\n"
                + "        ┃┗━┳┓╋┏┳━━┓\n"
                + "        ┃┏┓┃┃╋┃┃┃━┫\n"
                + "        ┗━━┻━┓┏┻━━┛\n"
                + "        ╋╋╋┏━┛┃\n"
                + "        ╋╋╋┗━━┛\n";
        System.out.println(line + "\nHello! I'm \n" + logo);
        System.out.println("How can I help you? \n" + line);
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print("Here are the tasks in your list: \n");
                for (int i = 0; i < storedList.size(); i++) {
                    int index = i + 1;
                    Task t = storedList.get(i);
                    System.out.println(index + "." + t.getStatusIcon() + t.description);
                }
                System.out.print(line + "\n");
            } else if (input.contains("unmark")) {
                int index = input.charAt(7) - 49;
                Task t = storedList.get(index);
                storedList.get(index).markAsUndone();
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println(t.getStatusIcon() + t.description);
                System.out.print(line);
            } else if (input.contains("mark")) {
                int index = input.charAt(5) - 49;
                Task t = storedList.get(index);
                storedList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.getStatusIcon() + t.description);
                System.out.print(line);
            } else {
                Task task = new Task(input);
                storedList.add(task);
                System.out.println("added: "+ input + "\n" + line);
            }

            input = userInput.nextLine();
        }

        System.out.println("Bye (actually hehe). Hope to see you again!\n" + line);
    }
}