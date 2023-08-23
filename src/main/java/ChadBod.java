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
                try {
                    if (command.length == 0) {
                        throw new InvalidInputException();
                    }
                    switch (command[0]) {
                        case "bye":
                            System.out.println("Bye. Hope to see you again soon!");
                            shouldExit = true;
                            break;
                        case "list":
                            if (tasks.isEmpty()) {
                                System.out.println("There are no tasks in your list!");
                            } else {
                                System.out.println("Here are the tasks in your list:");
                                for (int i = 0; i < tasks.size(); i ++) {
                                    System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                                }
                            }
                            break;
                        case "mark":
                            try {
                                int taskNumber = Integer.parseInt(command[1]);
                                if (taskNumber < 1 || taskNumber > tasks.size()) {
                                    throw new TaskIndexOutOfBoundsException();
                                }
                                Task task = tasks.get(taskNumber - 1);
                                task.markDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.printf("%s\n", task);
                            } catch (NumberFormatException e) {
                                System.out.println("☹ OOPS!!! Invalid index input for marking task.");
                            } catch (TaskIndexOutOfBoundsException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "unmark":
                            try {
                                int taskNumber = Integer.parseInt(command[1]);
                                if (taskNumber < 1 || taskNumber > tasks.size()) {
                                    throw new TaskIndexOutOfBoundsException();
                                }
                                Task task = tasks.get(taskNumber - 1);
                                task.markUndone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.printf("%s\n", task);
                            } catch (NumberFormatException e) {
                                System.out.println("☹ OOPS!!! Invalid index input for marking task.");
                            } catch (TaskIndexOutOfBoundsException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "todo":
                            try {
                                if (command.length < 2 || command[1].isEmpty()) {
                                    throw new InvalidTaskException("Description of todo cannot be empty.");
                                }
                                Todo newTodo = new Todo(command[1]);
                                tasks.add(newTodo);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(newTodo);
                                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                            } catch (InvalidTaskException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "deadline":
                            try {
                                if (command.length < 2 || command[1].isEmpty()) {
                                    throw new InvalidTaskException("Description of deadline cannot be empty.");
                                }
                                String[] deadlineDetails = command[1].split(" /by ", 2);
                                if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
                                    throw new InvalidTaskException("Deadline due date cannot be empty.");
                                }
                                Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                                tasks.add(newDeadline);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(newDeadline);
                                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                            } catch (InvalidTaskException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "event":
                            try {
                                if (command.length < 2 || command[1].isEmpty()) {
                                    throw new InvalidTaskException("Description of event cannot be empty.");
                                }
                                String[] eventDetails = command[1].split(" /from ", 2);
                                if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
                                    throw new InvalidTaskException("Event timings cannot be empty.");
                                }
                                String[] eventTimings = eventDetails[1].split(" /to ", 2);
                                if (eventTimings.length < 2 || eventTimings[1].isEmpty()) {
                                    throw new InvalidTaskException("Event from and to timings cannot be empty.");
                                }
                                Event newEvent = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                                tasks.add(newEvent);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(newEvent);
                                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                            } catch (InvalidTaskException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        default:
                            throw new InvalidInputException();
                    }
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
