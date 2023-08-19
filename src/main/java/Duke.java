import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello I'm iP");
        Task[] tasks = new Task[100];
        int nextTask = 0;
        Scanner input = new Scanner(System.in);
        String response = "";
        while (!Objects.equals(response, "bye")) {
            response = input.nextLine();
            try {
                if (Objects.equals(response, "bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (Objects.equals(response, "list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < nextTask; i++) {
                        System.out.println(i + 1 + "." + tasks[i].toString());
                    }
                } else if (response.startsWith("mark")) {
                    // Assumption: "mark" is not allowed as a task name & you can mark already done tasks.
                    // Issue: "mark" by itself crashes.
                    String[] array = response.split(" ");
                    String lastVal = array[array.length - 1];
                    int taskToMark = Integer.parseInt(lastVal);
                    if (taskToMark <= nextTask) {
                        tasks[taskToMark - 1].completeTask();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[taskToMark - 1].toString());
                    } else {
                        throw new DukeException("☹ OOPS!!! The mark command needs to be followed by a task number.");
                    }
                } else if (response.startsWith("todo")) {
                    String[] array = response.split(" ");
                    StringBuilder title = new StringBuilder();
                    for (String command : array) {
                        if (Objects.equals(command, "todo")) {
                            continue;
                        }
                        if (title.length() != 0) {
                            title.append(" ");
                        }
                        title.append(command);
                    }
                    if (title.length() != 0) {
                        tasks[nextTask++] = new Todo(title.toString());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[nextTask - 1]);
                        System.out.println("Now you have " + nextTask + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The title of a todo cannot be empty.");
                    }
                } else if (response.startsWith("deadline")) {
                    String[] array = response.split(" ");
                    StringBuilder title = new StringBuilder();
                    StringBuilder deadline = new StringBuilder();
                    String mode = "title";
                    for (String command : array) {
                        if (Objects.equals(command, "deadline")) {
                            continue;
                        }
                        if (Objects.equals(command, "/by")) {
                            mode = "deadline";
                            continue;
                        }
                        if (Objects.equals(mode, "title")) {
                            if (title.length() != 0) {
                                title.append(" ");
                            }
                            title.append(command);
                        } else {
                            if (deadline.length() != 0) {
                                deadline.append(" ");
                            }
                            deadline.append(command);
                        }
                    }
                    if (title.length() != 0 || deadline.length() != 0) {
                        tasks[nextTask++] = new Deadline(title.toString(), deadline.toString());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[nextTask - 1]);
                        System.out.println("Now you have " + nextTask + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The title and given deadline cannot be empty.");
                    }
                } else if (response.startsWith("event")) {
                    String[] array = response.split(" ");
                    StringBuilder title = new StringBuilder();
                    StringBuilder from = new StringBuilder();
                    StringBuilder to = new StringBuilder();
                    String mode = "title";
                    for (String command : array) {
                        if (Objects.equals(command, "event")) {
                            continue;
                        }
                        if (Objects.equals(command, "/from")) {
                            mode = "from";
                            continue;
                        }
                        if (Objects.equals(command, "/to")) {
                            mode = "to";
                            continue;
                        }
                        if (Objects.equals(mode, "title")) {
                            if (title.length() != 0) {
                                title.append(" ");
                            }
                            title.append(command);
                        } else if (Objects.equals(mode, "from")) {
                            if (from.length() != 0) {
                                from.append(" ");
                            }
                            from.append(command);
                        } else {
                            if (to.length() != 0) {
                                to.append(" ");
                            }
                            to.append(command);
                        }
                    }
                    if (title.length() != 0 || from.length() != 0 || to.length() != 0) {
                        tasks[nextTask++] = new Event(title.toString(), from.toString(), to.toString());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[nextTask - 1]);
                        System.out.println("Now you have " + nextTask + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The title, from and to sections cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
