import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
        String userinput;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner( System.in );

        userinput = scan.nextLine();

        while (!userinput.equalsIgnoreCase("bye")) {
            if (userinput.equalsIgnoreCase("list")) {

                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + "." + list.get(i));
                }

            } else  if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("mark ")) {

                try {
                    int taskno = Integer.parseInt(userinput.substring(5));
                    if (taskno <= list.size() + 1) {
                        Task task = list.get(taskno - 1);
                        task.setdone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + task);
                    } else {
                        System.out.println("Enter a valid number to mark");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid number to mark");
                }
            } else {

                System.out.println("added: " + userinput);
                list.add(new Task(userinput));

            }
            userinput = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
