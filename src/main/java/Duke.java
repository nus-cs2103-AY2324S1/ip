import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static void writeToDisk(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm iP");
        ArrayList<Task> tasks = new ArrayList<>(100);
        Scanner input = new Scanner(System.in);
        String response = "";
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException();
                }
            }
            File diskValues = new File("data/duke.txt");
            if (!diskValues.exists()) {
                if (!diskValues.createNewFile()) {
                    throw new IOException();
                }
            } else {
                Scanner s = new Scanner(diskValues);
                while (s.hasNext()) {
                    String oneTask = s.nextLine();
                    char TaskType = oneTask.charAt(1);
                    String[] splitTask = oneTask.split(" ");
                    String taskName = splitTask[2];
                    if (TaskType == 'T') {
                        tasks.add(new Todo(taskName));
                    } else if (TaskType == 'D') {
                        StringBuilder deadline = new StringBuilder();
                        String mode = "none";
                        for (String command : splitTask) {
                            if (Objects.equals(command, "(by:")) {
                                mode = "by";
                                continue;
                            }
                            if (Objects.equals(mode, "by")) {
                                if (deadline.length() != 0) {
                                    deadline.append(" ");
                                }
                                deadline.append(command);
                            }
                        }
                        if (deadline.length() != 0) {
                            deadline.deleteCharAt(deadline.length() - 1); // Remove last ).
                        }
                        tasks.add(new Deadline(taskName, deadline.toString()));
                    } else if (TaskType == 'E') {
                        StringBuilder from = new StringBuilder();
                        StringBuilder to = new StringBuilder();
                        String mode = "none";
                        for (String command : splitTask) {
                            if (Objects.equals(command, "(from:")) {
                                mode = "from";
                                continue;
                            }
                            if (Objects.equals(command, "to:")) {
                                mode = "to";
                                continue;
                            }
                            if (Objects.equals(mode, "from")) {
                                if (from.length() != 0) {
                                    from.append(" ");
                                }
                                from.append(command);
                            } else if (Objects.equals(mode, "to")) {
                                if (to.length() != 0) {
                                    to.append(" ");
                                }
                                to.append(command);
                            }
                        }
                        if (to.length() != 0) {
                            to.deleteCharAt(to.length() - 1); // Remove last ).
                        }
                        tasks.add(new Event(taskName, from.toString(), to.toString()));
                    } else {
                        throw new DukeException("Input file corrupted.");
                    }
                    if (oneTask.charAt(4) == 'X') {
                        tasks.get(tasks.size() - 1).completeTask();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in reading files: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println("DukeException in reading files: " + e.getMessage());
        }
        while (!Objects.equals(response, "bye")) {
            response = input.nextLine();
            try {
                if (Objects.equals(response, "bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (Objects.equals(response, "list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                } else if (response.startsWith("delete")) {
                    String[] array = response.split(" ");
                    String lastVal = array[array.length - 1];
                    int taskToMark = Integer.parseInt(lastVal);
                    if (taskToMark <= tasks.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(taskToMark - 1).toString());
                        tasks.remove(taskToMark - 1);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        writeToDisk(tasks);
                    } else {
                        throw new DukeException("☹ OOPS!!! The delete command needs to be followed by an existing task number.");
                    }
                } else if (response.startsWith("mark")) {
                    // Assumption: "mark" is not allowed as a task name & you can mark already done tasks.
                    // Issue: "mark" by itself crashes.
                    String[] array = response.split(" ");
                    String lastVal = array[array.length - 1];
                    int taskToMark = Integer.parseInt(lastVal);
                    if (taskToMark <= tasks.size()) {
                        tasks.get(taskToMark - 1).completeTask();
                        System.out.println("Nice! I've marked this task as done:");
                        writeToDisk(tasks);
                    } else {
                        throw new DukeException("☹ OOPS!!! The mark command needs to be followed by an existing task number.");
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
                        tasks.add(new Todo(title.toString()));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        writeToDisk(tasks);
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
                        try {
                            LocalDate fromDate = LocalDate.parse(deadline);
                            deadline = new StringBuilder(fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                        } catch (DateTimeException ignored) {
                        }
                        tasks.add(new Deadline(title.toString(), deadline.toString()));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        writeToDisk(tasks);
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
                        try {
                            LocalDate fromDate = LocalDate.parse(from);
                            from = new StringBuilder(fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                        } catch (DateTimeException ignored) {
                        }
                        try {
                            LocalDate fromDate = LocalDate.parse(to);
                            to = new StringBuilder(fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                        } catch (DateTimeException ignored) {
                        }
                        tasks.add(new Event(title.toString(), from.toString(), to.toString()));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        writeToDisk(tasks);
                    } else {
                        throw new DukeException("☹ OOPS!!! The title, from and to sections cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException in Main: " + e.getMessage());
            }
        }
    }
}
