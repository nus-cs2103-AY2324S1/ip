    package main.java;
    import java.util.Scanner;
    import java.util.ArrayList;


    public class ChadBod {
        public static void main(String[] args) {
            System.out.println("Hello! I'm ChadBod.");
            System.out.println("What can I do for you?");
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            boolean shouldExit = false;

            while (!shouldExit) {
                String input = sc.nextLine();
                // may need try catch here
                String[] command = input.split(" ", 2);
                switch (command[0]) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        shouldExit = true;
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i ++) {
                            System.out.printf("%d: %s\n", i + 1, tasks.get(i));
                        }
                        break;
                    case "mark":
                        try {
                            int taskNumber = Integer.parseInt(command[1]);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                Task task = tasks.get(taskNumber - 1);
                                task.markDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.printf("%s\n", task);
                            } else {
                                System.out.println("Invalid task index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for marking task.");
                        }
                        break;
                    case "unmark":
                        try {
                            int taskNumber = Integer.parseInt(command[1]);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                Task task = tasks.get(taskNumber - 1);
                                task.markUndone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.printf("%s\n", task);
                            } else {
                                System.out.println("Invalid task index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for unmarking task.");
                        }
                        break;
                    case "todo":
                        Todo newTodo = new Todo(command[1]);
                        tasks.add(newTodo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineDetails = command[1].split(" /by ", 2);
                        Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    case "event":
                        String[] eventDetails = command[1].split(" /from ", 2);
                        String[] eventTiming = eventDetails[1].split(" /to ", 2);
                        Event newEvent = new Event(eventDetails[0], eventTiming[0], eventTiming[1]);
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        break;
                    default:
                        tasks.add(new Task(input));
                        System.out.printf("added: %s\n", input);
                }
            }
        }
    }
