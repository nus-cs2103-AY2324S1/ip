import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Level 0
        String botName = "PAUUU";
        String introduction = " HI! I'm " + botName + "\n" + " Please entertain me!";
        String exit = "byebye come play with me next time\n";
        System.out.println(introduction);

        //Level 2
        String input;
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                System.out.println("You gotta girlboss through these tasks:");
                for(int i = 0; i < list.size(); i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + ".[" + curr.getStatusIcon() + "] " + curr.description);
                }
            } else if (input.startsWith("mark")) {
                System.out.println("Good job, you've completed a task! You're so productive!");
                String parts[] = input.split(" ");
                int taskNo = Integer.parseInt(parts[1]);
                Task checkedTask = list.get(taskNo - 1);
                checkedTask.markAsDone();
                System.out.println("[" + checkedTask.getStatusIcon() + "] " + checkedTask.description);
            } else if (input.startsWith("unmark")) {
                String parts[] = input.split(" ");
                int taskNo = Integer.parseInt(parts[1]);
                Task checkedTask = list.get(taskNo - 1);
                checkedTask.markAsUndone();
                System.out.println("I see you're not going to " + checkedTask.description + " but remember to do it later!");
                System.out.println("[" + checkedTask.getStatusIcon() + "] " + checkedTask.description);
            } else {
                Task item = new Task(input);
                list.add(item);
                System.out.println("added: " + input);
            }
        }

    }
}
