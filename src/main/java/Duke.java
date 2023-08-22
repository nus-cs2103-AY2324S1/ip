import java.util.Scanner;
public class Duke {
    private static final String name = "Faiz";
    private static final String line = "__________________________________\n";
    public static void main(String[] args) {

        greet();
        startChat();
    }

    private static void greet() {
        String greeting = line + "Hello! I'm " + name + "\n" + "What can I do for you?" + "\n" + line;
        System.out.println(greeting);
    }

    private static void startChat() {
        String[] tasks = new String[100];
        int taskId = 1;
        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();
        String goodbye = line + "Bye. Hope to see you again soon!" + "\n" + line;

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                System.out.println(line);
                for(int i = 1; i < taskId; i++){
                    System.out.println(i + ". " + tasks[i-1]);
                }
                System.out.println(line + "\n");
            }
            else {
                addToList(userInput, tasks, taskId);
                taskId++;
            }
            userInput = scanner.nextLine();
        }
        System.out.println(goodbye);
        scanner.close();
    }

    private static void addToList(String command, String[] tasks, int numTasks) {
        String task = line + "added: " + command + "\n" + line;
        tasks[numTasks - 1] = command;
        System.out.println(task);
    }
}
