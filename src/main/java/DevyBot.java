import java.util.Scanner;
import java.util.ArrayList;
public class DevyBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String welcome = "Hello! I'm DevyBot\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";
        String lineBreak = "____________________________________________________________";

        System.out.println(welcome);
        while (true) {
            String userInput = scanner.nextLine();
            String[] wordsArray = userInput.split("\\s+");
            if (userInput.equals("bye")) {
                System.out.println(lineBreak);
                System.out.println(exit);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(lineBreak);
                for (int i = 0 ; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ". " + taskList.get(i) );
                }
                System.out.println(lineBreak);
            }else {
                if (wordsArray[0].equals("mark")){
                    int index = Integer.parseInt(wordsArray[1]) - 1;
                    Task currentTask = taskList.get(index);
                    currentTask.markTask();
                    System.out.println(lineBreak);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(currentTask.toString());
                    System.out.println(lineBreak);

                } else if (wordsArray[0].equals("unmark")) {
                    int index = Integer.parseInt(wordsArray[1]) - 1;
                    Task currentTask = taskList.get(index);
                    currentTask.unmarkTask();
                    System.out.println(lineBreak);
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(currentTask.toString());
                    System.out.println(lineBreak);
                } else {
                    System.out.println(lineBreak);
                    Task newTask = new Task(userInput);
                    taskList.add(newTask);
                    System.out.println(" added " + userInput);
                    System.out.println(lineBreak);
                }
            }
        }
        scanner.close();
    }
}
