
import java.util.Scanner;
public class JavAI {
    public static void main(String[] args) {
        Task[] arr = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        String line = "     ____________________________________________________________";
        System.out.println(line + "\n      Hello, I'm JavAI.\n      What can I do for you?\n" + line);
        String output = sc.nextLine();
        while (!output.equals("bye")) {
            String[] words = output.split(" ");
            if (words[0].equals("mark") && words.length == 2) {
                int iden = Integer.parseInt(words[1]) - 1;
                if (iden >= 0 && arr[iden] != null) {
                    arr[iden].markAsDone();
                    System.out.println(line + "\n" + "     " + "  Nice! I've marked this task as done:");
                    System.out.println("       " + "[" + arr[iden].getStatusIcon() + "] " + arr[iden].description);
                    System.out.println(line);
                } else {
                    System.out.println("Improper input. Please retry!");
                }
            } else if (words[0].equals("unmark") && words.length == 2) {
                int iden = Integer.parseInt(words[1]) - 1;
                if (iden >= 0 && arr[iden] != null) {
                    arr[iden].markAsUndone();
                    System.out.println(line + "\n" + "     " + "  OK, I've marked this task as not done yet:");
                    System.out.println("       " + "[" + arr[iden].getStatusIcon() + "] " + arr[iden].description);
                    System.out.println(line);
                } else {
                    System.out.println("Improper input. Please retry!");
                }
            } else if (output.equals("list")) {
                System.out.println(line);
                System.out.println("      " + "Here are the tasks in your list:");
                for ( int i = 0; i < counter ; i++ ) {
                    System.out.println("       " + (i+1) + ".[" + arr[i].getStatusIcon() + "] " + arr[i].description);
                }
                System.out.println(line);
            } else {
               Task t = new Task(output);
               arr[counter] = t;
               System.out.println(line + "\n" + "     " + "  added: " + output + "\n" + line);
               counter++;
            }
            output = sc.nextLine();
        }
        System.out.println(line + "\n" + "      Bye. Hope to see you again soon!\n" + line);

    }
}
