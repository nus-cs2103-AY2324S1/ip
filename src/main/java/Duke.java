import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[10];
        String horizontal_line = "____________________________________________________________\n";
        int count = 0;
        String welcomeMessage = horizontal_line +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userCommand = scanner.nextLine();

            if ("bye".equals(userCommand)) {
                scanner.close();
                break;
            } else if ("list".equals(userCommand)) {
                System.out.println(horizontal_line);
                System.out.println("here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    Task task = tasks[i];
                    System.out.println((i + 1) + ". " + task.getString());
                }
                System.out.println(horizontal_line);
            } else if (userCommand.startsWith("mark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    Task task = tasks[num - 1];
                    task.toggleCompleted();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.getString());
                }
            } else if (userCommand.startsWith("unmark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    Task task = tasks[num - 1];
                    task.toggleCompleted();
                    System.out.println("Okay. I see you haven't done this task yet");
                    System.out.println(task.getString());
                }
            } else {
                Task currTask = null;
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
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                if (currTask != null) {
                    tasks[count] = currTask;
                    String numTasks = String.valueOf(count + 1);
                    System.out.println("Got it I have added this task: " + "\n" +  currTask.getString());
                    System.out.println("Now you have " + numTasks + " task(s) in the list");
                    System.out.println(horizontal_line);
                    count++;
                }
            }
        }
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}
