import java.util.Scanner;
import java.util.ArrayList;

//emoticons taken from: https://kaomojikuma.com/ and https://www.emoticonstext.com/

public class Duke {
    private static String line = "--------------------------------------------------------------------";

    public static void newTaskAdded(Task task) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
    }

    public static void getNumberOfTasks(ArrayList<Task> list) {
        System.out.println(list.size() == 1
                ? "Now you have " + list.size() + " task in the list!\n" + line
                : "Now you have " + list.size() + " tasks in the list!\n" + line);
    }

    public static void main(String[] args) {
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";

        System.out.println(line + "\n" + intro + line); //greeting
        String command = "";
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); //create Scanner object

        while (true) {
            if (scanner.hasNext()) {
                command = scanner.next();

                if (command.equals("bye")) {
                    break;

                } else if (command.equals("list")) {
                    if (list.size() == 0) {
                        System.out.println("(o´ω`o)ﾉ You have no upcoming tasks!\n" + line);
                    } else {
                        String result = "";
                        for (int i = 0; i < list.size(); i++) {
                            int index = i + 1;
                            Task task = list.get(i);
                            result += index + ". " + task.toString() + "\n";
                        }
                        System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are your tasks for the day:");
                        System.out.println(result + line);
                    }
                } else if (command.equals("mark")) {
                    int taskID = scanner.nextInt() - 1; //because input index starts from 1
                    list.get(taskID).mark();
                    System.out.println(line);

                } else if (command.equals("unmark")) {
                    int taskID = scanner.nextInt() - 1;
                    list.get(taskID).unmark();
                    System.out.println(line);

                } else if (command.equals("todo")) {
                    if (scanner.hasNextLine()) {
                        String rest = scanner.nextLine();

                        if (rest.isBlank()) { //no task specified
                            System.out.println("(・´з`・) Uh oh... please add a description\n" + line);
                        } else {
                            ToDo toDo = new ToDo(0, rest.substring(1));
                            list.add(toDo);
                            newTaskAdded(toDo);
                            getNumberOfTasks(list);
                        }
                    } else {
                        System.out.println("(・´з`・) Uh oh... please add a description");
                    }

                } else if (command.equals("deadline")) {
                    if (scanner.hasNextLine()) {
                        String rest = scanner.nextLine();

                        if (rest.isBlank()) { //no task specified
                            System.out.println("(・´з`・) Uh oh... please add a description\n" + line);
                        } else {
                            String afterSpace = rest.substring(1);
                            String[] arr = afterSpace.split("/by ", 2);

                            if (arr.length == 1) { //cannot find /by ie. incorrect format
                                System.out.println("(・´з`・) Uh oh... please add a deadline after '/by '\n" + line);
                            } else {
                                String task = arr[0];
                                String date = arr[1];
                                if (date.isBlank()) { //no date added
                                    System.out.println("(・´з`・) Uh oh... please add a deadline after '/by '\n" + line);
                                } else {
                                    Deadline deadline = new Deadline(0, task, date);
                                    list.add(deadline);
                                    newTaskAdded(deadline);
                                    getNumberOfTasks(list);
                                }
                            }
                        }
                    }
                } else if (command.equals("event")) {
                    if (scanner.hasNextLine()) {
                        String rest = scanner.nextLine();
                        if (rest.isBlank()) { //no task specified
                            System.out.println("(・´з`・) Uh oh... please add a description\n" + line);
                        } else {
                            String afterSpace = rest.substring(1);
                            String[] arr = afterSpace.split("/from ", 2);

                            if (arr.length == 1) { //no start date added
                                System.out.println("(・´з`・) Uh oh... please add a start date\n" + line);
                            } else {
                                String task = arr[0];
                                String start = arr[1].split(" /to ", 2)[0];
                                if (start.isBlank()) {
                                    System.out.println("(・´з`・) Uh oh... please add a start date\n" + line);

                                } else {
                                    String[] arrWithEnd = afterSpace.split("/to ", 2);

                                    if (arrWithEnd.length == 1) { //no end date added
                                        System.out.println("(・´з`・) Uh oh... please add an end date\n" + line);
                                    } else {
                                        String end = arrWithEnd[1];
                                        if (end.isBlank()) {
                                            System.out.println("(・´з`・) Uh oh... please add an end date\n" + line);
                                        } else {
                                            Event event = new Event(0, task, start, end);
                                            list.add(event);
                                            newTaskAdded(event);
                                            getNumberOfTasks(list);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Sorry, I don't understand what you mean ><\n" + line);
                    if (scanner.hasNext()) {
                        String rest = scanner.nextLine(); //scan the rest of the line to get to next valid input
                    }
                }
            }
        }
        System.out.println(bye + line);
        scanner.close();
    }
}
