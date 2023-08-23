import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void addTask(Task task) throws IndexOutOfBoundsException, NullPointerException{
        tasks.add(task);
        System.out.println("Got it, I've added this task:\n    "  +
                task.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String LOGO = "   /\\_/\\  \n" +
                "  ( o.o ) \n" +
                "   > ^ <\n";
        String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";
        String EXIT = "Bye(T.T), Hope to see you again soon!";


        System.out.println(LOGO + GREETING);

        while (true) {
            try {
                String input = sc.nextLine().trim();

                String[] inputArr = input.split(" ", 2);
                String command = inputArr[0];
                if (Objects.equals(command, "exit")) {
                    break;
                } else if (Objects.equals(command, "list")) {
                    if (inputArr.length > 1) {
                        throw new DukeException("Invalid list command");
                    }
                    listTasks();
                } else if (Objects.equals(command, "mark")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
                    }
                    if (input.split(" ").length > 2) {
                        throw new DukeException("Invalid mark command ?_? " +
                                "this command should follow by only ONE INTEGER");
                    }
                    int i = Integer.parseInt(inputArr[1]);
                    tasks.get(i - 1).mark();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            "    " + tasks.get(i - 1).toString());
                } else if (Objects.equals(command, "unmark")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
                    }
                    if (input.split(" ").length > 2) {
                        throw new DukeException("Invalid unmark command ?_? " +
                                "this command should follow by only ONE INTEGER");
                    }
                    int i = Integer.parseInt(inputArr[1]);
                    tasks.get(i - 1).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            "    " + tasks.get(i - 1).toString());
                } else if (Objects.equals(command, "todo")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String des = inputArr[1];
                    addTask(new Todo(des));
                } else if (Objects.equals(command, "deadline")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String details = inputArr[1];
                    String[] detailsArr = details.split(" /by ", 2);
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Do you forget to add a deadline for the task?");
                    }
                    String des = detailsArr[0];
                    String by = detailsArr[1];
                    addTask(new Deadline(des, by));
                } else if (Objects.equals(command, "event")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String details = inputArr[1];
                    String[] detailsArr = details.split(" /from ", 2);
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Ouuuu, I think you miss some information, try again!");
                    }
                    String des = detailsArr[0];
                    String time = detailsArr[1];
                    String[] timeArr = time.split(" /to ", 2);
                    if (timeArr.length <= 1) {
                        throw new DukeException("Ouuuu, I think you miss some information, try again!");
                    }
                    String start = timeArr[0];
                    String end = timeArr[1];
                    addTask(new Event(des, start, end));
                } else if (Objects.equals(command, "delete")) {
                    if (inputArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");
                    }
                    if (input.split(" ").length > 2) {
                        throw new DukeException("Invalid delete command ?_? " +
                                "this command should follow by only ONE INTEGER");
                    }
                    int i = Integer.parseInt(inputArr[1]);
                    Task temp = tasks.get(i - 1);
                    tasks.remove(i - 1);
                    System.out.println("Noted. I've removed this task:\n" +
                            "    " + temp.toString() + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list.");

                } else if (Objects.equals(command, "")) {
                    continue;
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out the bounds, try another index");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please type in INTEGER after this command ^v^");
            }
            finally {

            }
        }
        System.out.println(EXIT);
    }
}
