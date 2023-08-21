import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Task[] items = new Task[100];
        int itemCount = 0;
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello! I'm Ding!\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            String command = str.split(" ")[0];
            if (command.equals("list")) {
                System.out.println("These are your tasks... If I remember correctly:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(String.format("%d. %s", i+1, items[i]));
                }
            } else if (command.equals("mark") && str.split(" ").length == 2) {
                int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                items[taskIndex].markAsDone();
                System.out.println(String.format("Ding: Okay, I marked this task as done, but I have no idea what that means:\n %s", items[taskIndex]));
            } else if (command.equals("unmark") && str.split(" ").length == 2) {
                int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                items[taskIndex].markAsUndone();
                System.out.println(String.format("Ding: Okay, I marked this task as undone, but I have no idea what that means:\n %s", items[taskIndex]));
            } else {
                items[itemCount] = new Task(str);
                if (itemCount < 100) {
                    itemCount++;
                }
                System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.", str));
            }
            System.out.println("\n____________________________________________________________\n");
            str = sc.nextLine();
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________");

    }

}
