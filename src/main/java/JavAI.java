
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
            String description = "";
            int iterator = 1;
            if (words[0].equals("todo")) {
                while (iterator < words.length) {
                    description+= words[iterator] + " ";
                    iterator++;
                }
                Todo todo = new Todo(description);
                arr[counter] = todo;
                System.out.println(line + "\n      Got it. I've added this task:\n" +
                        "       " + arr[counter] +
                        "\n      Now you have " + (counter+1) + " task(s) in the list.\n" + line);
                counter++;
            } else if (words[0].equals("deadline")) {
                String by = "";
                while (!words[iterator].equals("/by")) {
                    description+= words[iterator] + " ";
                    iterator++;
                }
                iterator++;
                while (iterator < words.length) {
                    by+= words[iterator] + " ";
                    iterator++;
                }
                Deadline deadline = new Deadline(description, by);
                arr[counter] = deadline;
                System.out.println(line + "\n      Got it. I've added this task:\n" +
                        "       " + arr[counter] +
                        "\n      Now you have " + (counter+1) + " task(s) in the list.\n" + line);
                counter++;

            } else if (words[0].equals("event")) {
                String from = "";
                String to = "";
                while (!words[iterator].equals("/from")) {
                    description+= words[iterator] + " ";
                    iterator++;
                }
                iterator++;
                while (!words[iterator].equals("/to")) {
                    from+= words[iterator] + " ";
                    iterator++;
                }
                iterator++;
                while (iterator < words.length) {
                    to+= words[iterator] + " ";
                    iterator++;
                }
                Event event = new Event(description, from, to);
                arr[counter] = event;
                System.out.println(line + "\n      Got it. I've added this task:\n" +
                        "       " + arr[counter] +
                        "\n      Now you have " + (counter+1) + " task(s) in the list.\n" + line);
                counter++;
            } else if (words[0].equals("mark") && words.length == 2) {
                int iden = Integer.parseInt(words[1]) - 1;
                if (iden >= 0 && arr[iden] != null) {
                    arr[iden].markAsDone();
                    System.out.println(line + "\n" + "     " + "  Nice! I've marked this task as done:");
                    System.out.println("       " + arr[iden].toString());
                    System.out.println(line);
                } else {
                    System.out.println("Improper input. Please retry!");
                }
            } else if (words[0].equals("unmark") && words.length == 2) {
                int iden = Integer.parseInt(words[1]) - 1;
                if (iden >= 0 && arr[iden] != null) {
                    arr[iden].markAsUndone();
                    System.out.println(line + "\n" + "     " + "  OK, I've marked this task as not done yet:");
                    System.out.println("       " + arr[iden].toString());
                    System.out.println(line);
                } else {
                    System.out.println("Improper input. Please retry!");
                }
            } else if (output.equals("list")) {
                System.out.println(line);
                System.out.println("      " + "Here are the tasks in your list:");
                for ( int i = 0; i < counter ; i++ ) {
                    System.out.println("      " + (i+1) + "." + arr[i].toString());
                }
                System.out.println(line);
            } else {
                System.out.println("Improper input. Please retry!");
            }
            output = sc.nextLine();
        }
        System.out.println(line + "\n" + "      Bye. Hope to see you again soon!\n" + line);

    }
}
