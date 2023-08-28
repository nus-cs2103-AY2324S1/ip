import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

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

        //create the directory to store tasks
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        //create the file to store tasks
        File taskList = new File("./data/duke.txt");
        if (!taskList.exists()) {
            try {
                taskList.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created!");
            }
        }

        //initial greeting
        System.out.println(lines + "\n" + greeting + lines);
        //getting user input
        userCommand = sc.nextLine();

        /**
         * public class CreateFile {
         *   public static void main(String[] args) {
         *     try {
         *       File myObj = new File("filename.txt");
         *       if (myObj.createNewFile()) {
         *         System.out.println("File created: " + myObj.getName());
         *       } else {
         *         System.out.println("File already exists.");
         *       }
         *     } catch (IOException e) {
         *       System.out.println("An error occurred.");
         *       e.printStackTrace();
         *     }
         *   }
         * }
         */



        while (!userCommand.equals("bye")) {
            String[] splitted = userCommand.split(" ", 2);
            switch (splitted[0]) {
                case "list":
                    System.out.println(lines + "\n        Here are the tasks in your list:\n");
                    for (int i = 0; i < toDo.size(); i++) {
                        Task currTask = toDo.get(i);
                        String description = currTask.getDescription();
                        System.out.println("        " + Integer.toString(i + 1) + "." + description);
                    }
                    System.out.println(lines);
                    break;
                case "mark":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please say which task to mark (⋟﹏⋞)\n" + lines);
                    } else {
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
                    }
                    break;
                case "unmark":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please say which task to unmark (⋟﹏⋞)\n" + lines);
                    } else {
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
                    }
                    break;
                case "todo":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please add a description for the todo! (⋟﹏⋞)\n" + lines);
                    } else {
                        String todoTask = splitted[1];
                        Todo newTodo = new Todo(todoTask);
                        toDo.add(newTodo);
                        String description = newTodo.getDescription();
                        System.out.println(lines + "\n         Got it. I've added this new todo:\n           "
                                + description + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                    }
                    break;
                case "deadline":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please add a description for the deadline! (⋟﹏⋞)\n" + lines);
                    } else {
                        String[] deadTask = splitted[1].split("/by");
                        if (deadTask.length == 1) {
                            System.out.println(lines + "\n          Please add a deadline! (⋟﹏⋞)\n" + lines);
                        } else {
                            String deadDescription = deadTask[0];
                            String deadTime = deadTask[1];
                            Deadline deadlineTask = new Deadline(deadDescription, deadTime);
                            toDo.add(deadlineTask);
                            String message = deadlineTask.getDescription();
                            System.out.println(lines + "\n         Got it. I've added this new deadline:\n           "
                                    + message + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                        }
                    }
                    break;
                case "event":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please add a description for the event! (⋟﹏⋞)\n" + lines);
                    } else {
                        String[] eventTask = splitted[1].split("/from");
                        if (eventTask.length == 1) {
                            System.out.println(lines + "\n          Please add a start time for the event! (⋟﹏⋞)\n" + lines);
                        } else {
                            String eventDescription = eventTask[0];
                            String[] startEnd = eventTask[1].split("/to");
                            if (startEnd.length == 1) {
                                System.out.println(lines + "\n          Please add a end time for the event! (⋟﹏⋞)\n" + lines);
                            } else {
                                String eventStart = startEnd[0];
                                String eventEnd = startEnd[1];
                                Event newEvent = new Event(eventDescription, eventStart, eventEnd);
                                toDo.add(newEvent);
                                String msg = newEvent.getDescription();
                                System.out.println(lines + "\n         Got it. I've added this new event:\n           "
                                        + msg + "\n         Now you have " + toDo.size() + " tasks in the list.\n" + lines);
                            }
                        }
                    }
                    break;
                case "delete":
                    if (splitted.length <= 1) {
                        System.out.println(lines + "\n          Please say which task to delete! (⋟﹏⋞)\n" + lines);
                    } else {
                        int target = Integer.parseInt(splitted[1]);
                        if (target <= toDo.size()) {
                            Task toDelete = toDo.get(target - 1);
                            String description = toDelete.getDescription();
                            toDo.remove(target - 1);
                            System.out.println(lines + "\n          Noted, I've removed this task:\n                "
                                    + description + "\n         Now you have " + toDo.size() + " tasks in the list"
                                    + "\n" + lines);
                        }
                    }
                    break;
                default:
                    System.out.println(lines
                            + "\n        Huhhhhhhh??? (o_O) ? Please use one of the command words: todo, event, deadline, list, mark, unmark, delete, bye\n"
                            + lines);
            }

            userCommand = sc.nextLine();
        }
        //when the command is "bye", exit
        System.out.println(lines + "\n" + goodbye + lines);
    }
}
