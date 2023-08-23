    import exceptions.DukeException;
    import exceptions.InvalidArgumentException;
    import exceptions.UnknownCommandException;


    import java.util.Scanner;

    public class Duke {
        protected static final String chatBotName = "Cristiano";

        public static void main(String[] args) {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            greetUser();
            run();
        }

        protected static void greetUser() {
            printLine();
            System.out.println("Hello! I'm " + chatBotName + "! SUIIII!!!");
            System.out.println("What can I do for you?");
        }

        protected static void exit() {
            printLine();
            System.out.println("Bye. Hope to see you again soon!");
            printLine();
        }

        protected static void run() {
            TaskManager taskManager = new TaskManager();
            Scanner scanner = new Scanner(System.in);
            String input = "";
            while (!input.equals("bye")) {
                try {
                    input = scanner.nextLine();
                    String command = getCommand(input);
                    switch (command) {
                        case "list":
                            taskManager.list();
                            break;
                        case "mark":
                        case "unmark":
                            handleMarking(input, taskManager);
                            break;
                        case "todo":
                            handleTodo(input, taskManager);
                            break;
                        case "deadline":
                            handleDeadline(input, taskManager);
                            break;
                        case "event":
                            handleEvent(input, taskManager);
                            break;
                        default:
                            throw new UnknownCommandException("I may be the GOAT but I don't know what that means.");
                    }
                } catch (DukeException e) {
                    printLine();
                    System.out.println(e.getMessage());
                    printLine();
                }
            }
            exit();
        }

        protected static void printLine() {
            System.out.println("____________________________________________________________");
        }

        private static String getCommand(String input) {
            return input.split(" ")[0];
        }

        private static void handleMarking(String input, TaskManager taskManager) throws InvalidArgumentException {
            String[] words = input.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                if (words[0].equals("mark")) {
                    taskManager.mark(index);
                } else if (words[0].equals("unmark")) {
                    taskManager.unmark(index);
                }
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a numerical index!");
            }

        }

        private static void handleTodo(String input, TaskManager taskManager) throws InvalidArgumentException {
            try {
                int indexOfSpace = input.indexOf(" ");
                if (indexOfSpace == -1 || indexOfSpace == input.length() - 1) {
                    throw new InvalidArgumentException("Please enter a task description.");
                }
                String taskName = input.substring(input.indexOf(" ") + 1).trim();
                if(taskName.isEmpty()) {
                    throw new InvalidArgumentException("Please enter a task description.");
                }
                Task task = new ToDo(taskName);
                taskManager.add(task);
            } catch (InvalidArgumentException e) {
                throw new InvalidArgumentException("Please enter a task description.");
            }
        }

        private static void handleDeadline(String input, TaskManager taskManager) throws InvalidArgumentException {
            try {
                String suffix = input.substring(input.indexOf(" ") + 1);
                String[] parts = suffix.split(" /due ");
                if (parts.length != 2) {
                    throw new InvalidArgumentException("Invalid format for deadline. " +
                            "Please use: deadline task name /due due Date");
                }
                String taskName = parts[0].trim();
                String dueDate = parts[1].trim();
                Task task = new Deadline(taskName, dueDate);
                taskManager.add(task);
            } catch (InvalidArgumentException e) {
                throw new InvalidArgumentException("Invalid format for deadline. " +
                        "Please use: deadline task name /due due Date");
            }

        }

        private static void handleEvent(String input, TaskManager taskManager) throws InvalidArgumentException {
            try {
                String suffix = input.substring(input.indexOf(" ") + 1);
                String[] parts = suffix.split(" /from ");
                if (parts.length != 2) {
                    throw new InvalidArgumentException("Invalid format for event. " +
                            "Please use: event task_name /from start /to end");
                }
                String taskName = parts[0].trim();
                String[] timeParts = parts[1].split(" /to ");
                if (timeParts.length != 2) {
                    throw new InvalidArgumentException("Invalid format for event. " +
                            "Please use: event task_name /from start /to end");
                }
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                Task task = new Event(taskName, from, to);
                taskManager.add(task);
            } catch (InvalidArgumentException e) {
                throw new InvalidArgumentException("Invalid format for event. " +
                        "Please use: event task_name /from start /to end");
            }

        }
    }
