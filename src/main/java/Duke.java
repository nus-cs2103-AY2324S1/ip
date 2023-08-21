import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //this is the new name for my chatbot
        String name = "dukey";
        //these are the lines
        String lines = "        ____________________________________________________________";
        //this will be the greeting
        String greeting = "        Hello! I'm " + name + "\n        What can I do for you?\n";
        //this will be goodbye
        String goodbye = "        Bye. Hope to see you again soon! :D\n";
        //scanner to get user input
        Scanner sc = new Scanner(System.in);
        //this is what the user typed in
        String userCommand;
        //fixed size array to store the items
        ArrayList<Task> toDo = new ArrayList<>();

        //initial greeting
        System.out.println(lines + "\n" + greeting + lines);
        //getting user input
        userCommand = sc.nextLine();
        //echoes the user input when the command is not "bye"
        while (!userCommand.equals("bye")) {
            String[] splitted = userCommand.split(" ", 2);
            switch (splitted[0]) {
                case "list":
                    System.out.println(lines + "\n      Here are the tasks in your list:\n");
                    for (int i = 0; i < toDo.size(); i++) {
                        Task currTask = toDo.get(i);
                        String description = currTask.getDescription();
                        System.out.println("        " + Integer.toString(i + 1) + "." + description);
                    }
                    System.out.println(lines);
                    break;
                case "mark":
                    int task_no = Integer.parseInt(splitted[1]);
                    if (task_no <= toDo.size()) {
                        Task target = toDo.get(task_no - 1);
                        target.mark();
                        String description = target.getDescription();
                        System.out.println(lines
                                + "\n        Nice! I've marked this task as done:\n          "
                                + description + "\n"
                                + lines);
                    } else {
                        System.out.println(lines
                                + "\n      You don't have that many tasks :(\n"
                                + lines);
                    }
                    break;
                case "unmark":
                    int taskNo = Integer.parseInt(splitted[1]);
                    if (taskNo <= toDo.size()) {
                        Task target = toDo.get(taskNo - 1);
                        target.unmark();
                        String description = target.getDescription();
                        System.out.println(lines
                                + "\n        OK, I've marked this task as not done yet:\n          "
                                + description + "\n"
                                + lines);
                    } else {
                        System.out.println(lines
                                + "\n      You don't have that many tasks :(\n"
                                + lines);
                    }
                    break;
                case "todo":
                    String todoTask = splitted[1];
                    Todo newTodo = new Todo(todoTask);
                    toDo.add(newTodo);
                    String description = newTodo.getDescription();
                    System.out.println(lines + "\n         Got it. I've added this new todo:\n           "
                            + description + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                    break;
                case "deadline":
                    String[] deadTask = splitted[1].split("/by");
                    String deadDescription = deadTask[0];
                    String deadTime = deadTask[1];
                    Deadline deadlineTask = new Deadline(deadDescription, deadTime);
                    toDo.add(deadlineTask);
                    String message = deadlineTask.getDescription();
                    System.out.println(lines + "\n         Got it. I've added this new deadline:\n           "
                            + message + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                    break;
                case "event":
                    String[] eventTask = splitted[1].split("/from");
                    String eventDescription = eventTask[0];
                    String[] startEnd = eventTask[1].split("/to");
                    String eventStart = startEnd[0];
                    String eventEnd = startEnd[1];
                    Event newEvent = new Event(eventDescription, eventStart, eventEnd);
                    toDo.add(newEvent);
                    String msg = newEvent.getDescription();
                    System.out.println(lines + "\n         Got it. I've added this new event:\n           "
                            + msg + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                    break;
                default:
                    System.out.println(lines + "\n        added: " + userCommand + "\n" + lines);
                    Task newTask = new Task(userCommand);
                    toDo.add(newTask);
            }

            userCommand = sc.nextLine();
        }
        //when the command is "bye", exit
        System.out.println(lines + "\n" + goodbye + lines);
    }
}
