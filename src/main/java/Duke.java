import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Harry Potter";
        String question = "What can I do for you?";
        System.out.println("Hello! I'm " + name + "\n" + question);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Task> tasks = new ArrayList<>();

        while (!str.equals("bye")) {
            if (!str.equals("list")) {
                if (str.startsWith("mark ")) {
                    String num = str.substring(5);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task done = tasks.get(index);
                    done.markAsDone();
                    System.out.println("\t" + "Nice! I've marked this task " +
                            "as done:" + "\n" +
                            "\t" + "\t" + done.taskString());
                } else if (str.startsWith("unmark ")) {
                    String num = str.substring(7);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task notDone = tasks.get(index);
                    notDone.markAsNotDone();
                    System.out.println("\t" + "OK, I've marked this task " +
                            "as not done yet:" + "\n" + "\t" + "\t" +
                            notDone.taskString());
                } else {
                    if (str.startsWith("todo ")) {
                        Task task = new ToDo(str);
                        tasks.add(task);
                        int len = tasks.size();
                        String output = "\tGot it. I've added this task:\n\t\t"
                                + task.taskString();
                        String listLength = "Now you have " + len + " tasks in the list.";
                        System.out.println(output
                                + "\n\t" + listLength);
                    }
                    else if (str.startsWith("deadline ") && str.contains("/by ")){
                        String byWhen = "/by ";
                        int index = str.indexOf(byWhen);
                        String deadline = str.substring(index + 4); //remove /by from the substring
                        String workToDo = str.substring(0, index);
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
                    else if (str.startsWith("event ") && str.contains("/from ")) {
                        String fromMarker = "/from "; //mark the /from index of the string
                        int firstIndex = str.indexOf(fromMarker);
                        int secondIndex;
                        String fromWhen;
                        String toWhen;
                        String workToDo = str.substring(0, firstIndex);
                        String afterFirstIndex = str.substring(firstIndex + 6);
                        if (!afterFirstIndex.contains("/to ")) { //to check the input of /to after /from
                            System.out.println("\tInvalid input as the event end time " +
                                    "has not been specified. Please enter /from what time, /to what time " +
                                    "the event would last.");
                        } else {
                            String toMarker = "/to "; //mark the /to index of the string
                            secondIndex = afterFirstIndex.indexOf(toMarker); //to make sure we get the /to after the /from
                            fromWhen = afterFirstIndex.substring(0, secondIndex); //get the from timing
                            toWhen = afterFirstIndex.substring(secondIndex + 4);
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
                }
            } else {
                listTasks(tasks);
                System.out.println();
            }
            str = sc.nextLine();
        }
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public static void listTasks(List<Task> tasks) {
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println();
            System.out.print("\t" + i + "." + task.taskString());
            i++;
        }
    }
}
