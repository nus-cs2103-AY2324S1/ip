import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException, IOException {
        //Level 0
        String botName = "PAUUU";
        String introduction = " HI! I'm " + botName + "\n" + " Please entertain me!";
        String exit = "byebye come play with me next time";
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
                try {
                    ToDo item = Task.createToDo(input);
                    list.add(item);
                    System.out.println("todo added: " + item.toString());
                    System.out.println("You have this many stuff to complete: " + list.size());
                } catch (NoDescException e) {
                }
            } else if (input.startsWith("delete")) {
                String parts[] = input.split(" ");
                int taskNo = Integer.parseInt(parts[1]);
                Task checkedTask = list.get(taskNo - 1);
                list.remove(checkedTask);
                System.out.println("not you running away from your responsibilities, i guess you don't have to do this now:");
                System.out.println(checkedTask.toString());
                System.out.println("sucks to be you, you still have " + list.size() + " tasks");
            } else if (input.startsWith("deadline")) {
                try {
                    Deadline item = Task.createDeadline(input);
                    list.add(item);
                    System.out.println("deadline added: " + item.toString());
                    System.out.println("You have this many stuff to complete: " + list.size());
                } catch (NoDescException e) {
                } catch (DeadlineNoEndException e) {
                }
            } else if (input.startsWith("event")) {
                try {
                    Event item = Task.createEvent(input);
                    list.add(item);
                    System.out.println("event added: " + item.toString());
                    System.out.println("You have this many stuff to complete: " + list.size());
                } catch (NoDescException e) {
                }
            }
        }
    }
}
