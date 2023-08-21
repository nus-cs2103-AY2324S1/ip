import java.util.Scanner;
public class Duke {


    public static void main(String[] args) {
        System.out.println("Hello! I'm FRIDAY!\n" +
                "What can I do for you?");

        echo();
    }

    public static void echo() {
        TaskList taskList = new TaskList();

        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println(taskList);
            } else {
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                System.out.println("added: " + newTask);
            }
        }
    }
}
