import java.util.Scanner;
import java.util.ArrayList;

//emoticons taken from: https://kaomojikuma.com/ and https://www.emoticonstext.com/

public class Duke {
    private static String line = "--------------------------------------------------------------------";
    private static ArrayList<Task> list = new ArrayList<>();

    public static void newTaskAdded(Task task) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
    }

    public static void getNumberOfTasks(ArrayList<Task> list) {
        System.out.println(list.size() == 1
                ? "Now you have " + list.size() + " task in the list!\n" + line
                : "Now you have " + list.size() + " tasks in the list!\n" + line);
    }

    //credit: https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
    private static boolean isNumber(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    public static void listTasks() {
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
    }

    public static boolean isValidTaskID(int taskID) {
        if (taskID > list.size() - 1 || taskID < 0) {
            System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
            return false;
        }
        return true;
    }

    public static boolean deleteTask(int taskID) {
        if (!isValidTaskID(taskID)) {
            return false;
        } else {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            System.out.println("ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:");
            System.out.println(toRemove);
            getNumberOfTasks(list);
            return true;
        }
    }

    public static void markTask(int taskID) {
        list.get(taskID).mark();
    }

    public static void unmarkTask(int taskID) {
        list.get(taskID).unmark();
    }

    public static boolean isValidCommand(String input) throws InvalidCommandException,
            NoDescException,
            NoDateException, NoStartException, NoEndException {
        boolean isValid = true;
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        if (input.equals("list")) {
            listTasks();
        } else if (command.equals("delete")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    isValid = deleteTask(index);
                } else {
                    //case where a number was not provided
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("mark")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (isValidTaskID(index)) {
                        markTask(index);
                    } else {
                        isValid = false;
                    }
                } else {
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("unmark")) {
            if (inputArr.length == 1) {
                System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
            } else {
                String strIndex = inputArr[1];
                if (isNumber(strIndex)) {
                    int index = Integer.parseInt(strIndex) - 1; //because index starts from 1
                    if (isValidTaskID(index)) {
                        unmarkTask(index);
                    } else {
                        isValid = false;
                    }
                } else {
                    System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
                    isValid = false;
                }
            }
        } else if (command.equals("todo")) {
            isValid = isValidToDo(input);

        } else if (command.equals("deadline")) {
            isValid = isValidDeadline(input);

        } else if (command.equals("event")) {
            isValid = isValidEvent(input);
        } else {
            throw new InvalidCommandException();
        }
        return isValid;
    }

    public static boolean isValidToDo(String input) throws NoDescException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            if (inputArr[1].isBlank()) {
                throw new NoDescException();
            } else {
                //0 for unmarked, any other number for marked
                ToDo toDo = new ToDo(0, inputArr[1]);
                list.add(toDo);
                newTaskAdded(toDo);
                getNumberOfTasks(list);
            }
        }
        return true;
    }

    public static boolean isValidDeadline(String input) throws NoDescException, NoDateException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            //now we check whether there is a deadline
            String[] arr = afterCommand.split("/by ", 2);
            if (arr[0].isBlank()) {
                throw new NoDescException();
            } else if (arr.length == 1) {
                throw new NoDateException();
            } else {
                String task = arr[0];
                String date = arr[1];
                if (date.isBlank()) {
                    throw new NoDateException();
                } else {
                    Deadline deadline = new Deadline(0, task, date);
                    list.add(deadline);
                    newTaskAdded(deadline);
                    getNumberOfTasks(list);
                }
            }
        }
        return true;
    }

    public static boolean isValidEvent(String input) throws
            NoDescException, NoStartException, NoEndException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            throw new NoDescException();
        } else if (inputArr[1].isBlank()) {
            throw new NoDescException();
        } else {
            String afterCommand = inputArr[1];
            String[] arr = afterCommand.split("/from ", 2);
            if (arr[0].isBlank()) {
                throw new NoDescException();
            } else if (arr.length == 1) { //no start date added
                throw new NoStartException();
            } else {
                String task = arr[0];
                String start = arr[1].split("/to ", 2)[0];
                if (start.isBlank()) {
                    throw new NoStartException();
                } else {
                    String[] arrWithEnd = afterCommand.split("/to ", 2);
                    if (arrWithEnd.length == 1) { //no end date added
                        throw new NoEndException();
                    } else {
                        String end = arrWithEnd[1];
                        if (end.isBlank()) {
                            throw new NoEndException();
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
        return true;
    }

    public static void main(String[] args) {
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";

        System.out.println(line + "\n" + intro + line); //greeting
        Scanner scanner = new Scanner(System.in); //create Scanner object

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else {
                    isValidCommand(input);
                }
            } catch (InvalidCommandException | NoDescException | NoDateException |
                     NoStartException | NoEndException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(bye + line);
        scanner.close();
    }
}