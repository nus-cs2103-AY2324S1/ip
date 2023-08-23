import java.util.Scanner;
import java.util.ArrayList;

public class Hong {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String line = "---------------------------------------------------------";
    public static void main(String[] args) {
        sayHello();
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myObj.close();
                break;
            } else if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.startsWith("mark")) {
                handleMark(userInput);
            } else if (userInput.startsWith("deadline")) {
                createDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                createEvent(userInput);
            } else if (userInput.startsWith("todo")) {
                createTodo(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else {
                Task currentTask = new Task(userInput);
                String currentMessage = line + "\n" + "added: " + userInput + "\n" + line;
                System.out.println(currentMessage);
                tasks.add(currentTask);
            }
        }
        sayBye();
    }
    private static void sayHello() {
        String firstMessage = line + "\nHello! I'm Hong\nWhat can I do for you?\n" + line;
        System.out.println(firstMessage);
    }

    private static void sayBye() {
        String exitMessage = line + "\n" + "Bye. Hope to see you again soon!\n" + line;
        System.out.println(exitMessage);
    }

    private static void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentItem = (i + 1) + "." + currentTask.toString();
            System.out.println(currentItem);
        }
        System.out.println(line);
    }

    private static void handleMark(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {
            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            currentTask.markDone();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            String currentItem = currentTask.toString();
            System.out.println(currentItem);
            System.out.println(line);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }

    }

    private static void createDeadline(String userInput) {
        String newInput = userInput.substring(9);
        String[] arrInput = newInput.split("/by ");
        if (arrInput.length != 2) {
            System.out.println("Error! There is an issue with the format of your message. ");
        } else {
            Deadline newDeadline = new Deadline(arrInput[1], arrInput[0]);
            tasks.add(newDeadline);
            addedMessage(newDeadline.toString());
        }

    }

    private static void createTodo(String userInput) {
        String newInput = userInput.substring(5);
        Todo newTodo = new Todo(newInput);
        tasks.add(newTodo);
        addedMessage(newTodo.toString());
    }

    private static void createEvent(String userInput) {
        String newInput = userInput.substring(6);
        String[] arrInput = newInput.split("/from ");
        String eventDetails = arrInput[0];
        String[] fromToArr = arrInput[1].split(" /to ");
        Event newEvent = new Event(fromToArr[0], fromToArr[1], eventDetails);
        tasks.add(newEvent);
        addedMessage(newEvent.toString());
    }

    private static void addedMessage(String taskMessage) {
        String message = line + "\nGot it. I've added this task:\n" + taskMessage + "\nNow you have " + tasks.size() +
                " tasks in the list.\n" + line;
        System.out.println(message);
    }

    private static void deleteTask(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {

            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            tasks.remove(Integer.valueOf(arrInput[1]) - 1);
            System.out.println(line);
            System.out.println("Noted. I've removed this task:");
            String currentItem = currentTask.toString();
            System.out.println(currentItem);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }
    }

}
