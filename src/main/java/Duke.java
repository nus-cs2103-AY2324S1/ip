import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String doneCheckbox = "[X] ";
    public static String undoneCheckbox = "[ ] ";

    public static void main(String[] args) {
        System.out.println(greet());
        List<Task> lst = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            String[] words = input.split("\\s+");
            if (words.length > 1) {
                if (words[0].equalsIgnoreCase("mark")) {
                    //mark as completed
                    Task tsk = lst.get(Integer.valueOf(words[1]) - 1);
                    tsk.setCompleted();
                    continue;
                } else if (words[0].equalsIgnoreCase("unmark")) {
                    Task tsk = lst.get(Integer.valueOf(words[1]) - 1);
                    tsk.setUncompleted();
                    continue;
                }
            }
            if (input.equalsIgnoreCase("list")) {
                //print out list
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < lst.size(); i++) {
                    Task task = lst.get(i);
                    System.out.println(String.valueOf(i + 1) + "." + task.getStatus());
                }
            } else if (input.equalsIgnoreCase("bye")) {
                //break out
                sc.close();
                break;
            } else {
                //add the item into the list
                lst.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println(Duke.byeGreetings);
    }

    public static String byeGreetings = "Bye. Hope to see you again soon!";
    public static String name = " ____    __        __       \n"
            + "|  _ \\   | |  ____ | | __\n"
            + "| | | |  | | |     | |/ /\n"
            + "| |_| |  | | |     |   < \n"
            + "|____/   ___  ____ |_|\\_\\\n";
    public static String greet() {
        return "Hello from \n " + Duke.name + "What can I do for you? ";
    }
}
