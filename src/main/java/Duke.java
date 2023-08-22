import java.util.Scanner;
public class Duke {
    private static final String name = "Bartholomew Hamish Montgomery";
    private static final String line = "__________________________________\n";
    public static void main(String[] args) {

        greet();
        startChat();
    }

    private static void greet() {
        String greeting = line + "I extend to you my utmost felicitations, User! I am " + name + "." + "\n" + "What may I do for you?" + "\n" + line;
        System.out.println(greeting);
    }

    private static void startChat() {
        Task[] tasks = new Task[5];
        int taskId = 0;
        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();
        String goodbye = line + "Until we meet once more in the near future, I bid you farewell." + "\n" + line;

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                if (tasks[0] == null) {
                    System.out.println("There are no items in the list!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < taskId; i++){
                        System.out.println(tasks[i].getTask());
                    }
                }
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[taskIndex].mark();
                System.out.println("Okay, I have marked this task as completed!" + "\n" + tasks[taskIndex].checkBox());
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[taskIndex].unMark();
                System.out.println("Okay, I have marked this task as incomplete!" + "\n" + tasks[taskIndex].checkBox());
            } else {
                addToList(userInput, tasks, taskId);
                if (taskId < tasks.length) {
                    taskId++; // don't increment, array might be full
                }
            }
            userInput = scanner.nextLine();
        }
        System.out.println(goodbye);
        scanner.close();
    }

    private static void addToList(String command, Task[] tasks, int taskId) {
        if (taskId == tasks.length) {
            System.out.println("List is full!");
        } else{
            Task task = new Task(command, taskId + 1);
            String response = line + "added: [ ] " + command + "\n" + line;
            tasks[taskId] = task;
            System.out.println(response);
        }
    }
}
