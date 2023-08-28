import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class James {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    public static void main(String[] args) {
        new James().start();
    }
    public ArrayList<Task> items = new ArrayList<Task>();

    public void start() {
        String line = "____________________________________________________________";
        String sadFace = "\u2639";

        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        // User Input
        Scanner in = new Scanner(System.in);


        String input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(line);
                    for (int i = 0; i < this.items.size(); i++) {
                        System.out.println(i + 1 + "." + this.items.get(i));
                    }
                } else if (input.contains("unmark")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.items.get(taskIdx - 1);
                    task.unmark();
                    System.out.println(line + "\n" + "OK! I've marked this task as not done yet:");
                    System.out.println(task + "\n" + line);
                } else if (input.contains("mark")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.items.get(taskIdx - 1);
                    task.mark();
                    System.out.println(line + "\n" + "Nice! I've marked this task as done:");
                    System.out.println(task + "\n" + line);
                } else if (input.contains("delete")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = this.items.get(taskIdx - 1);
                    this.items.remove(task);
                    System.out.println(line + "\n" + "Noted. I've removed this task:\n" + task + "\n" + line);
                    System.out.println("Now you have " + this.items.size() + " tasks in the list.");
                } else {
                    // Add Task
                    TaskType taskType = null;
                    if (input.contains("todo")) {
                        taskType = TaskType.TODO;
                    } else if (input.contains("deadline")) {
                        taskType = TaskType.DEADLINE;
                    } else if (input.contains("event")) {
                        taskType = TaskType.EVENT;
                    } else {
                        throw new JamesException(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    Task task;

                    if (taskType == TaskType.TODO) {
                        String[] description = input.split("todo ");
                        if (description.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDoTask(description[1]);
                    } else if (taskType == TaskType.DEADLINE) {
                        String[] parsed = input.split("deadline ");
                        if (parsed.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] param = parsed[1].split(" /by ");
                        if (param.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The time of a deadline cannot be empty.");
                        }
                        String description = param[0];
                        String time = param[1];
                        task = new DeadlineTask(description, time);
                    } else if (taskType == TaskType.EVENT) {
                        String[] parsed = input.split("event ")[1].split(" /from ");
                        if (parsed.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a event cannot be empty.");
                        }
                        String description = parsed[0];
                        String[] params = parsed[1].split(" /to ");
                        if (params.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The time of a event cannot be empty.");
                        }
                        String startTime = params[0];
                        String endTime = params[1];
                        task = new EventTask(description, startTime, endTime);
                    } else {
                        throw new JamesException(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    this.items.add(task);
                    System.out.println(line + "\n" + "Got it. I've added this task:\n" + task + "\n" + line);
                    System.out.println("Now you have " + this.items.size() + " tasks in the list.");
                    this.save();
                }

            } catch (JamesException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
            }
            input = in.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);

    }

    public void save() {
        String path = "../data/james.txt";
        String directory = "../data";
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file);
            for (Task task : this.items) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred when saving: " + e.getMessage());
        }
    }

}
