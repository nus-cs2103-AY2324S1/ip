import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        int count = 0;

        while (!inData.equals("bye")) {
            inData = scan.nextLine();

            if (inData.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (inData.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < count; i++) {
                    System.out.println((i + 1) +  "." + list[i].toString());
                }
                continue;
            }

            if (inData.startsWith("mark ")) {
                int num = inData.charAt(5) - '0' - 1;
                if (num >= 0 && num < count) {
                    list[num].markAsDone();
                    System.out.println("Nice! I've marked this task done:");
                    System.out.println(list[num]);
                } else {
                    System.out.println("Invalid");
                }
                continue;
            }

            if (inData.startsWith("unmark ")) {
                int num = inData.charAt(7) - '0' - 1;
                if (num >= 0 && num < count) {
                    list[num].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list[num]);
                } else {
                    System.out.println("Invalid");
                }
                continue;
            }

            list[count] = new Task(inData);
            count++;
            System.out.println("added: "+ inData);
        }
    }
}

