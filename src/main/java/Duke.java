import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String LINE = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println("\t" + LINE + "\n" +
                "\t Hello I'm ADJ \n" +
                "\t What can I do for you? \n\t" +
                LINE);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("\t" + LINE + "\n" +
                        "\t Bye. Hope to see you again soon! \n\t" +
                        LINE);
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                System.out.println("\t" + LINE);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t " + (i+1) + ". " +  tasks.get(i));
                }
                System.out.println("\t" + LINE);
            } else {
                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println("\t" + LINE + "\n" +
                        "\t added: " + userInput + "\n\t" +
                        LINE);
            }
        }

    }
}
