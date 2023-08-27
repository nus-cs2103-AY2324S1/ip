import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ChatBot {
    private static List<Task> list = new ArrayList<>();
    private static final String lineBreak = "____________________________________________________________\n";

    public static void main(String[] args) {
        // Introduction message
        System.out.println(lineBreak +
                " Hello! I'm Desolute\n" +
                " What can I do for you?\n" +
                lineBreak);

<<<<<<< HEAD
        // Checks if data directory is available
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        // Checks if file is available
        file = new File("./data/list.txt");
        if (!file.exists()) {
            try {
                createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Reads all the tasks on the file
        try {
            readAllTasks();
        } catch (FileNotFoundException f) {
            System.out.println("\n Task save file is missing!!! ");
        } catch (DukeException d) {
            System.out.println(lineBreak +
                    d.getMessage() +
                    lineBreak);
        }

=======
>>>>>>> origin/master
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // Loops till bye command is given
        while (!next.equals("bye")) {
            try {
                nextCommand(next);
            } catch (DukeException e) {
                System.out.println(lineBreak +
                        e.getMessage()+
                        lineBreak);
            }
            next = sc.nextLine();
        }

        // Exit message
        System.out.println(lineBreak +
                " Bye. Hope to see you again soon!\n" +
                lineBreak);
    }

    private static void nextCommand(String str) throws DukeException {
        // Splitting of string adapted from
        // https://stackoverflow.com/questions/9378394/remove-first-word-from-a-string-in-java
        String[] temp = str.split(" ", 2);

        // Checks if any pre determined commands are given
        switch (temp[0]) {
            case "list":
                showList();
                break;
            case "mark":
                markDone(temp[1]);
                break;
            case "unmark":
                unmarkDone(temp[1]);
                break;
            case "todo":
                if (temp.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                Task toDo = new ToDo(temp[1]);
                list.add(toDo);
                System.out.println(lineBreak +
                        " Got it. I've added this task:\n  " +
                        toDo.toString() +
                        getTaskCount() +
                        lineBreak);
                break;
            case "deadline":
                if (temp.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.\n");
                }
                String[] temp2 = temp[1].split(" /by");
                if (temp2.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The date of a deadline cannot be empty.\n");
                }
                Task deadline = new Deadline(temp2[0], temp2[1]);
                list.add(deadline);
                System.out.println(lineBreak +
                        " Got it. I've added this task:\n  " +
                        deadline.toString() +
                        getTaskCount() +
                        lineBreak);
                break;
            case "event":
                if (temp.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.\n");
                }
                String[] temp3 = temp[1].split(" /from");
                if (temp3.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The date and time of an event cannot be empty.\n");
                }
                String[] temp4 = temp3[1].split("/to");
                if (temp4.length != 2) {
                    throw new DukeException(" ☹ OOPS!!! The end time of an event cannot be empty.\n");
                }
                Task event = new Event(temp3[0], temp4[0], temp4[1]);
                list.add(event);
                System.out.println(lineBreak +
                        " Got it. I've added this task:\n  " +
                        event.toString() +
                        getTaskCount() +
                        lineBreak);
                break;
            case "delete":
                delete(temp[1]);
                break;
            default:
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    private static void showList() {
        System.out.print(lineBreak);
        if (list.isEmpty()) {
            System.out.println(" The list is empty. Please add tasks in!");
        } else {
            System.out.println(" Here are the tasks in your list:");
        }
        for (int i = 0; i < list.size(); i++) {
            Task temp = list.get(i);
            String current = String.format(" %d.%s", i + 1, temp.toString());
            System.out.println(current);
        }
        System.out.println(lineBreak);
    }

    private static void markDone(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException(" The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException(" This task number is not available. Please try again.\n");
        }
        Task curr = list.get(num);
        curr.markDone();
    }

    private static void unmarkDone(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException(" The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException(" This task number is not available. Please try again.\n");
        }
        Task curr = list.get(num);
        curr.unmarkedDone();

    }

    private static void delete(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException(" The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException(" This task number is not available. Please try again.\n");
        }
        Task curr = list.get(num);
        list.remove(num);
        System.out.println(lineBreak +
                " Noted. I've removed this task:\n  " +
                curr.toString() +
                getTaskCount() +
                lineBreak);
    }

    private static String getTaskCount() {
        return String.format("\n Now you have %d tasks in the list.\n", list.size());
    }
}
