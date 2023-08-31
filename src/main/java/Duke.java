import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Harry Potter";
        String question = "What can I do for you?";
        System.out.println("Hello! I'm " + name + "\n" + question);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Task> tasks = Task.loadTasks("src/data/Duke.txt");

        try {
            while (!str.equals("bye")) {
                if (!str.equals("list")) {
                    if (str.startsWith("mark ")) {
                        String num = str.substring(5);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1; //index for task list
                        Task done = tasks.get(index);
                        done.markAsDone();
                        System.out.println("\t" + "Nice! I've marked this task " +
                                "as done:" + "\n" +
                                "\t " + done.taskString());
                    } else if (str.startsWith("unmark ")) {
                        String num = str.substring(7);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1; //index for task list
                        Task notDone = tasks.get(index);
                        notDone.markAsNotDone();
                        System.out.println("\t" + "OK, I've marked this task " +
                                "as not done yet:" + "\n" + "\t" + " " +
                                notDone.taskString());
                    } else if (str.startsWith("delete ")) {
                        String num = str.substring(7);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1;
                        Task toBeDeleted = tasks.remove(index);
                        System.out.println("\tNoted. I've removed this task:\n\t " + toBeDeleted.taskString()
                        + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                    } else {
                        if (str.startsWith("todo")) {
                            String todo = str.substring(4);
                            //remove any leading and trailing whitespace characters and
                            // check whether there is a task after the instruction
                            if (todo.trim().isEmpty()) {
                                //this would mean the instruction is incomplete
                                throw new ToDoCommandUseException(str);
                            }
                            String string = str.substring(5);
                            Task task = new ToDo(string);
                            tasks.add(task);
                            int len = tasks.size();
                            String output = "\tGot it. I've added this task:\n\t\t"
                                    + task.taskString();
                            String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                                    "Now you have " + len + " tasks in the list.";
                            System.out.println(output
                                    + "\n\t" + listLength);
                        } else if (str.startsWith("deadline")) {
                            if (!str.contains("/by ")) {
                                throw new DeadlineCommandUseException(str); //needs to check for /by
                            } else {
                                String byWhen = "/by ";
                                int index = str.indexOf(byWhen);
                                String deadline = str.substring(index + 4); //remove /by from the substring
                                if (deadline.trim().isEmpty()) { //needs to check whether there is anything after /by
                                    throw new DeadlineCommandUseException(str);
                                }
                                String workToDo = str.substring(9, index);
                                Task task = new Deadline(workToDo, deadline);
                                tasks.add(task);
                                int len = tasks.size();
                                String output = "\tGot it. I've added this task:\n\t\t"
                                        + task.taskString();
                                String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                                        "Now you have " + len + " tasks in the list.";
                                System.out.println(output
                                        + "\n\t" + listLength);
                            }
                        } else if (str.startsWith("event")) {
                            if (!str.contains("from")) {
                                throw new EventCommandUseException(str);
                            } else {
                                String fromMarker = "/from "; //mark the /from index of the string
                                int firstIndex = str.indexOf(fromMarker);
                                int secondIndex;
                                String fromWhen;
                                String toWhen;
                                String workToDo = str.substring(6, firstIndex);
                                String afterFirstIndex = str.substring(firstIndex + 6);
                                if (!afterFirstIndex.contains("/to ")) { //to check the input of /to after /from
                                    throw new EventCommandUseException(str);
                                } else {
                                    String toMarker = "/to "; //mark the /to index of the string
                                    secondIndex = afterFirstIndex.indexOf(toMarker); //to make sure we get the /to after the /from
                                    fromWhen = afterFirstIndex.substring(0, secondIndex); //get the from timing
                                    toWhen = afterFirstIndex.substring(secondIndex + 4); //get the to timing
                                    if (fromWhen.trim().isEmpty() ||
                                            toWhen.trim().isEmpty()) { //needs to check whether there is anything after /by
                                        throw new EventCommandUseException(str);
                                    }
                                    Task task = new Event(workToDo, fromWhen, toWhen);
                                    tasks.add(task);
                                    int len = tasks.size();
                                    String output = "\tGot it. I've added this task:\n\t\t"
                                            + task.taskString();
                                    String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                                            "Now you have " + len + " tasks in the list.";
                                    System.out.println(output
                                            + "\n\t" + listLength);
                                }
                            }
                        } else {
                            throw new InvalidInputException(str);
                        }
                    }
                }
            else {
                Task.listTasks(tasks);
                System.out.println();
            }
            str = sc.nextLine();
            }
            Task.saveTasks("src/data/Duke.txt", tasks);
            System.out.println("\t" + "Bye. Hope to see you again soon!");
        } catch (InvalidInputException | EventCommandUseException|
                 DeadlineCommandUseException| ToDoCommandUseException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

