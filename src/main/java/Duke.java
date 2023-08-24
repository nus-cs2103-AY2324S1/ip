import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected TaskType type;
        protected String start;
        protected String end;

        public Task(String description, TaskType type, String start, String end) {
            this.description = description;
            this.isDone = false;
            // set to-do as the default type
            this.type = type;
            this.start = start;
            this.end = end;

        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTypeIcon() {
            switch (this.type) {
                case TODO:
                    return "T";
                case DEADLINE:
                    return "D";
                case EVENT:
                    return "E";
            }
            return null;
        }

        public void marking(Boolean checked) {
            this.isDone = checked;
        }

        public void descriptionString() {
            String initStatement = "     Got it. I've added this task:";
            System.out.println(initStatement);
            this.printMarking();
        }

        public void printMarking() {
            System.out.printf("       [%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.description);

            if (!Objects.equals(this.start, "") && !Objects.equals(this.end, "")) {
                System.out.printf(" (from: %s to: %s)", this.start, this.end);
            } else if  (!Objects.equals(this.start, "")) {
                System.out.printf(" (by: %s)", this.start);
            } else {
                return;
            }
        }
        //...
    }

    public static class Storage {
        protected ArrayList<Task> taskList;

        public Storage() {
            this.taskList = new ArrayList<Task>();
        }

        public void addList(Task t) {
            this.taskList.add(t);
        }

        public void delete(int id) {
            System.out.println("     Noted. I've removed this task:");
            Task t = this.taskList.get(id);
            t.printMarking();
            this.taskList.remove(id);
            int size = this.taskList.size();
            System.out.printf("\n     Now you have %d tasks in the list.\n", size);
        }

        public void listPrinter() {
            for (int i = 0; i < this.taskList.size(); i++) {
                int index = i + 1;
                Task t = this.taskList.get(i);
                System.out.printf("     %d.[%s][%s] %s", index, t.getTypeIcon(), t.getStatusIcon(), t.description);
                if (!Objects.equals(t.start, "") && !Objects.equals(t.end, "")) {
                    System.out.printf(" (from: %s to: %s)%n", t.start, t.end);
                } else if  (!Objects.equals(t.start, "")) {
                    System.out.printf(" (by: %s)%n", t.start);
                } else {
                    System.out.print("\n");
                }
            }
        }

        public void printMarking(int i) {
            Task t = this.taskList.get(i);
            t.printMarking();
        }

        public void printEntry(Task t) {
            t.descriptionString();
            int size = this.taskList.size();
            System.out.printf("\n     Now you have %d tasks in the list.\n", size);
        }
    }

    //function to retrieve string that the user input
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        //to mimic chatBot
        System.out.print(" ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {

        // standard response
        String horizontalLine = "   ---------------------------------------------------";
        String intro = "    Hello! I'm iPbot \n    What can I do for you?";
        String outro = "    Bye. Hope to see you again soon!";
        String markString = "    Nice! I've marked this task as done:";
        String unmarkString = "     OK, I've marked this task as not done yet:";
        String noDescError = "     ☹ OOPS!!! The description of a todo cannot be empty.";
        String noCommandError = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        // initialise
        Storage storage = new Storage();
        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);

        while (true) {
            String input = getInput();
            String[] parts = input.split(" ");
            switch (parts[0]) {
                case "bye":
                    System.out.println(horizontalLine);
                    System.out.println(outro);
                    System.out.println(horizontalLine);
                    return;
                case "list":
                    System.out.println(horizontalLine);
                    storage.listPrinter();
                    System.out.println(horizontalLine);
                    break;
                case "mark" :
                    System.out.println(horizontalLine);
                    int id = Integer.parseInt(parts[1]) - 1;
                    System.out.println(markString);
                    storage.taskList.get(id).marking(true);
                    storage.printMarking(id);
                    System.out.println(horizontalLine);
                    break;
                case "unmark" :
                    int id2 = Integer.parseInt(parts[1]) - 1;
                    storage.taskList.get(id2).marking(false);
                    System.out.println(horizontalLine);
                    System.out.println(unmarkString);
                    storage.printMarking(id2);
                    System.out.println(horizontalLine);
                    break;
                case "delete" :
                    int id3 = Integer.parseInt(parts[1]) - 1;
                    System.out.println(horizontalLine);
                    storage.delete(id3);
                    System.out.println(horizontalLine);
                    break;
                case "todo" :
                    String taskDesc = input.replace("todo", "");
                    if (Objects.equals(taskDesc, "")) {
                        System.out.println(horizontalLine);
                        System.out.println(noDescError);
                        System.out.println(horizontalLine);
                        break;
                    }
                    Task task = new Task(taskDesc, TaskType.TODO, "", "");
                    System.out.println(horizontalLine);
                    storage.addList(task);
                    storage.printEntry(task);
                    System.out.println(horizontalLine);
                    break;
                case "deadline" :
                    int indexOfDeadline = input.indexOf("deadline");
                    int indexOfBy = input.indexOf("/by");
                    taskDesc = input.substring(indexOfDeadline + 9, indexOfBy);
                    String deadlinePart = "";
                    deadlinePart = input.substring(indexOfBy + 3).trim();
                    System.out.println(horizontalLine);
                    task = new Task(taskDesc, TaskType.DEADLINE, deadlinePart, "");
                    storage.addList(task);
                    storage.printEntry(task);

                    System.out.println(horizontalLine);
                    break;
                case "event" :
                    int indexOfEvent = input.indexOf("event");
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    taskDesc = input.substring(indexOfEvent + 6, indexOfFrom);
                    String fromPart = "";
                    fromPart = input.substring(indexOfFrom + 5, indexOfTo);
                    String toPart = "";
                    toPart = input.substring(indexOfTo +3).trim();
                    System.out.println(horizontalLine);
                    task = new Task(taskDesc, TaskType.EVENT, fromPart, toPart);
                    storage.addList(task);
                    storage.printEntry(task);

                    System.out.println(horizontalLine);
                    break;
                default:
                    System.out.println(horizontalLine);
                    System.out.println(noCommandError);
                    System.out.println(horizontalLine);
            }
        }
    }
}