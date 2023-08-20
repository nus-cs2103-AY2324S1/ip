import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Obi-wan Kenobi";
        String line = "_____________________________________";
        Scanner scanner = new Scanner(System.in);
        String command;
        String[] tasklist = new String[100];
        int ind = 0;

        System.out.println("Hello There! I am " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            command = scanner.nextLine();

            if(command.equals("bye")) {
                break;
            }

            if(command.equals("list")) {
                for (int i = 0; i < tasklist.length; i++) {
                    if(tasklist[i] == null) {
                        break;
                    }
                    System.out.print((i + 1) + ". " + tasklist[i] + "\n");
                }
            } else {
                tasklist[ind] = command;
                ind++;
                System.out.println("added:" + command);
            }

            System.out.println(line);
        }

        System.out.println("Bye. May the force be with you!");
    }

}
