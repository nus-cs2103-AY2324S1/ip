import java.util.Scanner;

public class Alpha {

    public static void main(String[] args) {

        System.out.println("______________________________");
        System.out.println("Hello! I'm Alpha.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________");

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String userInput = sc.nextLine();

            System.out.println("______________________________");

            switch (userInput) {
                case "list":
                    int count = 1;
                    for (String task : taskList.getTasks()) {
                        System.out.println(count++ + ". " + task);
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("______________________________");
                    return;
                default:
                    taskList.addTask(userInput);
                    System.out.println("added: " + userInput);

            }

            System.out.println("______________________________");
        }
    }
}
