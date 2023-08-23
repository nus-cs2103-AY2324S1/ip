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
                for (int i = 0; i < list.size(); i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + ". " + curr.toString());
                }
            } else if (input.startsWith("mark")) {
                System.out.println("Good job, you've completed a task! You're so productive!");
                String parts[] = input.split(" ");
                int taskNo = Integer.parseInt(parts[1]);
                Task checkedTask = list.get(taskNo - 1);
                checkedTask.markAsDone();
                System.out.println(checkedTask.toString());
            } else if (input.startsWith("unmark")) {
                String parts[] = input.split(" ");
                int taskNo = Integer.parseInt(parts[1]);
                Task checkedTask = list.get(taskNo - 1);
                checkedTask.markAsUndone();
                System.out.println("I see you're not going to " + checkedTask.description + " but remember to do it later!");
                System.out.println(checkedTask.toString());
            } else if (input.startsWith("todo")) {
                Task item = new ToDo(input.replace("todo ", ""));
                list.add(item);
                System.out.println("todo added: " + item.toString());
                System.out.println("You have this many stuff to complete: " + list.size());
            } else if (input.startsWith("deadline ")) {
                String parts[] = input.split("/by");
                Task item = new Deadline(parts[0].replace("deadline ", ""), parts[1]);
                list.add(item);
                System.out.println("deadline added: " + item.toString());
                System.out.println("You need to complete " + list.size() + " more tasks");
            } else if (input.startsWith("event")) {
                String parts[] = input.split("/from");
                String time[] = parts[1].split("/to");
                Task item = new Event(parts[0].replace("event ", ""), time[0], time[1]);
                list.add(item);
                System.out.println("event added: " + item.toString());
                System.out.println("You need to complete " + list.size() + " more tasks");
            }
        }
    }
}
