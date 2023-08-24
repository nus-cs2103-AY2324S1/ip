import java.util.Scanner;

public class JamesBond {
    public static void main(String[] args) throws EmptyDescException {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(logo);
        try {
            while (sc.hasNextLine()) {
                String firstWord = sc.next();
                if (firstWord.equalsIgnoreCase("mark")) {
                    if (sc.hasNextInt()) {
                        int taskNumber = sc.nextInt();
                        taskList.markTask(taskNumber);
                    } else {
                        System.out.println("Invalid Input, re-enter Mark followed by task number");
                        sc.nextLine();
                    }
                } else if (firstWord.equalsIgnoreCase("unmark")) {
                    int taskNumber = sc.nextInt();
                    taskList.unMarkTask(taskNumber);
                }
                else if (firstWord.equalsIgnoreCase("delete")) {
                    int taskNumber = sc.nextInt();
                    taskList.deleteTask(taskNumber);
                }
                else {
                    String input = sc.nextLine().trim();
                    if (firstWord.equalsIgnoreCase("todo")) {
                        taskList.addToDo(input);
                    } else if (firstWord.equalsIgnoreCase("deadline")) {
                        int byIndex = input.indexOf("/by");
                        if (byIndex != -1) {
                            String taskDescription = input.substring(0, byIndex).trim();
                            System.out.println(taskDescription);
                            String dueDate = input.substring(byIndex + 4).trim();
                            System.out.println(dueDate);
                            taskList.addDead(taskDescription, dueDate);
                        } else {
                            throw new IllegalArgumentException("Deadline not formatted correctly, type again in the format /by (deadline)");
                        }
                    } else if (firstWord.equalsIgnoreCase("event")) {
                        int fromIndex = input.indexOf("/from");
                        int toIndex = input.indexOf("/to");
                        if (fromIndex != -1 && toIndex != -1) {
                            String taskDescription = input.substring(0, fromIndex).trim();
                            System.out.println(taskDescription);
                            String startTime = input.substring(fromIndex + 6, toIndex).trim();
                            String endTime = input.substring(toIndex + 4).trim();
                            taskList.addEvent(taskDescription, startTime, endTime);
                        } else {
                            throw new IllegalArgumentException("Event format incorrect, type again in the format /from (timing) /to (timing)");
                        }
                    } else if (firstWord.equalsIgnoreCase("bye")) {
                        System.out.println("Bye. Till the next time.");
                        break;
                    } else if (firstWord.equalsIgnoreCase("list")) {
                        taskList.listOut();
                    } else {
                        if (input.isEmpty()) {
                            taskList.addInput(firstWord);
                        } else {
                            taskList.addInput(firstWord + " " + input);
                        }
                    }
                }
            }
        } catch (EmptyDescException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid task number: " + e.getMessage());
        }
    }
}
