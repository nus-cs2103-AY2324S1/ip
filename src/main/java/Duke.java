import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    static String horizontal_line = "____________________________________________________________\n";
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        handleStart();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userCommand = scanner.nextLine();
            if ("bye".equals(userCommand)) {
                handleBye(scanner);
                break;
            } else if ("list".equals(userCommand)) {
               handleList(tasks);
            } else if (userCommand.startsWith("delete ")) {
                handleDelete(userCommand, tasks);
            } else if (userCommand.startsWith("mark ")) {
                handleMark(userCommand, tasks);
            } else if (userCommand.startsWith("unmark ")) {
               handleUnmark(userCommand, tasks);
            } else {
                handleAddTask(userCommand, tasks);
            }
        }
    }

    public static String updateNumMessage(String numTasks) {
        return "Now you have " + numTasks + " task(s) in the list";
    }

    public static void handleList(List<Task> tasks) {
        System.out.println(horizontal_line);
        System.out.println("here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getString());
        }
        System.out.println(horizontal_line);
    }

    public static void handleDelete(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ", 2);
        Task removedTask;
        try {
            if (parts.length != 2) {
                throw new DukeException("invalid delete command");
            }
            int num = Integer.parseInt(parts[1]);
            if (num > tasks.size()) {
                throw new DukeException("invalid delete command (this task number does not exist)");
            }
            removedTask = tasks.remove(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.getString());
            System.out.println(updateNumMessage(String.valueOf(tasks.size())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleMark(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ");
        try {
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getString());
            } else {
                throw new DukeException("invalid mark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleUnmark(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ");
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Okay. I see you haven't done this task yet");
                System.out.println(task.getString());
            } else {
                throw new DukeException("invalid unmark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleStart() {
        String welcomeMessage = horizontal_line +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
    }

    public static void handleAddTask(String userCommand, List<Task> tasks) {
        Task currTask;
        String[] parts = userCommand.split(" ", 2);
        try {
            if (userCommand.startsWith("todo")) {
                String description ="";
                if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                    description = parts[1];
                }
                currTask = new ToDos(description);
            } else if (userCommand.startsWith("deadline")) {
                String description = "";
                String date = "";
                String secondPart ="";
                if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                    secondPart = parts[1];
                }
                String[] finalParts = secondPart.split(" /by ", 2);
                if (finalParts.length >= 2) {
                    description = finalParts[0];
                    date = finalParts[1];
                }
                currTask = new Deadlines(description, date);
            } else if (userCommand.startsWith("event")) {
                String description ="";
                String fromDate = "";
                String byDate = "";
                if (parts.length >= 2) {
                    String[] secondPartSplits = parts[1].split(" /from ", 2);
                    if (secondPartSplits.length >= 2) {
                        String[] dates = secondPartSplits[1].split(" /to ", 2);
                        if (dates.length >= 2) {
                            fromDate = dates[0].trim();
                            byDate = dates[1].trim();
                            description = secondPartSplits[0];
                        }
                    }
                }
                currTask = new Events(description, fromDate, byDate);
            } else {
                throw new DukeException("This command does not exist! >:(");
            }
                tasks.add(currTask);
                String numTasks = String.valueOf(tasks.size());
                System.out.println("Got it I have added this task:" + "\n" +  currTask.getString());
                System.out.println(updateNumMessage(numTasks));
                System.out.println(horizontal_line);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleBye(Scanner scanner) {
        scanner.close();
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}
