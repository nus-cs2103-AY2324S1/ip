import java.util.Scanner;
public class Duke {
    private static final String name = "Bartholomew Hamish Montgomery";
    private static final String line = "______________________________________________________________________________________\n";
    public static void main(String[] args) {

        greet();
        startChat();
    }

    private static void greet() {
        String greeting = line + "I extend to you my utmost felicitations, User! I am " + name + "." + "\n" + "What may I do for you?" + "\n" + line;
        System.out.println(greeting);
    }

    // issues --> list not working
    private static void startChat() {
        Task[] tasks = new Task[5];
        int taskCount = 0;
        int taskId = 1;
        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();
        String goodbye = line + "Until we meet once more in the near future, I bid you farewell." + "\n" + line;

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                displayList(tasks, taskCount);
            } else if (userInput.startsWith("mark ")) {
                mark(userInput, tasks);
            } else if (userInput.startsWith("unmark ")) {
                unMark(userInput, tasks);
            } else if (userInput.startsWith("todo ")) {
                String nameOfTask = userInput.substring(5);
                ToDos task = new ToDos(nameOfTask, taskId);
                addToList(task, tasks, taskCount);
                if (taskCount < tasks.length) {
                    taskCount++;
                    taskId++;
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.split("/");
                String nameOfTask = parts[0].trim().substring(9);
                Deadlines task = new Deadlines(nameOfTask, taskId, parts[1].trim());
                addToList(task, tasks, taskCount);
                if (taskCount < tasks.length) {
                    taskCount++;
                    taskId++;
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.split("/");
                String start = parts[1].trim();
                String end = parts[2].trim();
                String nameOfTask = parts[0].trim().substring(5);
                Events task = new Events(nameOfTask, taskId, start, end);
                addToList(task, tasks, taskCount);
                if (taskCount < tasks.length) {
                    taskCount++;
                    taskId++;
                }
            } else {
                System.out.println("invalid task!");
            }
            userInput = scanner.nextLine();
        }
        System.out.println(goodbye);
        scanner.close();
    }

    private static void displayList(Task[] tasks, int taskCount){
        if (tasks[0] == null) {
            System.out.println(line + "There are no items in the list!" + "\n" + line);
        } else {
            System.out.println(line + "Here are the tasks in your list:");
            for(int i = 0; i < taskCount; i++){
                System.out.println(tasks[i].getTask());
            }
            System.out.println(line);
        }
    }

    public static void mark(String input, Task[] tasks) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        tasks[taskIndex].mark();
        System.out.println(line + "Okay, I have marked this task as completed!" + "\n" + tasks[taskIndex].checkBox());
        System.out.println(line);
    }

    public static void unMark(String input, Task[] tasks) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        tasks[taskIndex].unMark();
        System.out.println(line + "Okay, I have marked this task as incomplete!" + "\n" + tasks[taskIndex].checkBox());
        System.out.println(line);
    }

    private static void addToList(Task task, Task[] tasks, int taskId) {
        if (taskId == tasks.length) {
            System.out.println("List is full!");
        } else{
            int taskCount = taskId + 1;
            String response = line + "Got it! I've added this task:" + "\n" + task.checkBox() + "\n"
                    + "You now have " + taskCount + " task(s) in the list" + "\n" + line;
            tasks[taskId] = task;
            System.out.println(response);
        }
    }
}
