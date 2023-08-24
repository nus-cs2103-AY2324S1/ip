import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String bye = " Bye. Hope to see you again soon!";
        String message = "____________________________________________________________\n"
                + " Hello! I'm ChatBot\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        Task[] tasksList = new Task[100];
        int count = 0;

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            String[] splitted = input.split(" ");
            System.out.println("----------------------------------------------");
            if(input.equalsIgnoreCase("bye")) {
                System.out.println(bye);
                System.out.println("----------------------------------------------");
                break;

            } else if(input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                if (count == 0) {
                    System.out.println("\t You currently have no tasks!");
                }

                for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + tasksList[i].toString());
                }

                System.out.println("----------------------------------------------");
            } else if (input.startsWith("mark")) {
                tasksList[Integer.parseInt(input.replace("mark ", "")) - 1].markDone();
            } else if (input.startsWith("unmark")) {
                tasksList[Integer.parseInt(input.replace("unmark ", "")) - 1].unmarkDone();
            } else {
                //System.out.println("Got it. I've added this task:");
                if(input.startsWith("todo")) {

                    tasksList[count] = new Todo(input.replace("todo", ""));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + tasksList[count].toString());
                    System.out.println("Now you have " + (count + 1) + " tasks in the list");
                    System.out.println("----------------------------------------------");
                } else if(input.startsWith("event")) {
                    //event project meeting /from Mon 2pm /to 4pm
                    String[] s = input.replace("event ", "").split(" /from | /to");
                    tasksList[count] = new Event(s[0], s[1], s[2]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + tasksList[count].toString());
                    System.out.println("Now you have " + (count + 1) + " tasks in the list");
                    System.out.println("----------------------------------------------");

                } else if (input.startsWith("deadline")) {
                    //deadline return book /by Sunday
                    String[] s = input.replace("deadline ", "").split(" /by ");
                    tasksList[count] = new Deadline(s[0], s[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + tasksList[count].toString());
                    System.out.println("Now you have " + (count + 1) + " tasks in the list");
                    System.out.println("----------------------------------------------");
                }
                count++;

            }
        }
    }

}
