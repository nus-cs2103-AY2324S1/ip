import java.util.ArrayList;
import java.util.Scanner;

public class Smolbrain {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________\n");
                break;
            }

            switch (input) {
                case "list":
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i+1) + ". " + data.get(i).display());
                    }
                    break;

                default:
                    if (input.contains("unmark")){
                        int num = Integer.parseInt(input.replace("unmark ", "")) - 1;
                        System.out.println("OK, I've marked this task as not done yet: ");
                        data.get(num).unmark();
                        System.out.println(data.get(num).display());
                        break;
                    } else if (input.contains("mark")){
                        int num = Integer.parseInt(input.replace("mark ", "")) - 1;
                        System.out.println("Nice! I've marked this task as done: ");
                        data.get(num).mark();
                        System.out.println(data.get(num).display());
                        break;
                    }
                    
                    Task task = new Task(input);
                    data.add(task);
                    System.out.println("added: " + input);
                    break;
            }
            System.out.println("____________________________________________________________\n");
        }

    }
}
