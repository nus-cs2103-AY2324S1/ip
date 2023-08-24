import java.util.*;
public class Edna {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String partition = "----------------------------------------";

        System.out.println(partition);
        System.out.println("Hello! I'm Edna.");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        input = sc.next();

        while(input.equals("bye") == false) {
            if (input.equals("list")) {
                print();
                System.out.println(partition);
            } else if (input.equals("mark")) {
                int num = sc.nextInt();
                taskList.get(num - 1).markDone();
                System.out.println(partition);
            } else if (input.equals("unmark")) {
                int num = sc.nextInt();
                taskList.get(num - 1).unmarkDone();
                System.out.println(partition);
            } else {
                add(input);
                System.out.println(partition);
            }
            input = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }

    public static void add(String input) {
        taskList.add(new Task(input));
        System.out.println("added: " + input);
    }

    public static void print() {
        int num = 1;
        for(Task temp: taskList) {
            System.out.println(num + ". " + temp.getStatus());
            num++;
        }
    }
}
