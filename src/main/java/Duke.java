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
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList);
            } else if (userInput.contains("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                System.out.println("Nice! I've marked this task as not done yet:");
                taskList.unmark(taskNumber - 1);
            } else if (userInput.contains("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done:");
                taskList.mark(taskNumber - 1);
            } else if (userInput.contains("todo")) {
                String[] todoInput = userInput.split(" ", 2);
                Todo todo = new Todo(todoInput[1]);
                System.out.println("added: " + todo);
                taskList.add(todo);
                taskList.message();
            } else if (userInput.contains("deadline")) {
                String[] commandAndDetails = userInput.split(" ", 2);
                String[] taskAndDate = commandAndDetails[1].split(" /by ", 2);
                String taskDescription = taskAndDate[0];
                String deadlineDate = taskAndDate[1];
                Deadline deadline = new Deadline(taskDescription, deadlineDate);
                System.out.println("added: " + deadline);
                taskList.add(deadline);
                taskList.message();
            } else if (userInput.contains("event")) {
                String[] commandAndDetails = userInput.split(" ", 2);
                String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", 3);
                String taskDescription = taskAndTimes[0];
                String startTime = taskAndTimes[1];
                String endTime = taskAndTimes[2];
                Event event = new Event(taskDescription, startTime, endTime);
                System.out.println("added: " + event);
                taskList.add(event);
                taskList.message();
            } else {
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                System.out.println("added: " + newTask);
            }
        }
    }
}
