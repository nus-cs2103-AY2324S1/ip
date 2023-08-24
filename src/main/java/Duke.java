import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void marking(Boolean checked) {
            this.isDone = checked;
        }

        //...
    }

    public static class Storage {
        protected ArrayList<Task> taskList;

        public Storage(int size) {
            this.taskList = new ArrayList<Task>(size);
        }

        public void addList(String task) {
            Task newTask = new Task(task);
            this.taskList.add(newTask);
        }

        public void listPrinter() {
            for (int i = 0; i < this.taskList.size(); i++) {
                int index = i + 1;
                Task t = this.taskList.get(i);
                System.out.println("     " + index + ".[" + t.getStatusIcon() + "] " + t.description);
            }
        }

        public void printMarking(int i) {
            Task t = this.taskList.get(i);
            System.out.println("       " + "[" + t.getStatusIcon() + "] " + t.description);
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

        // initialise
        Storage storage = new Storage(100);
        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);

        while (true) {
            String input = getInput();
            switch (input) {
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
                default:
                    if (input.startsWith("mark")) {
                        String[] parts = input.split(" ");
                        int id = Integer.parseInt(parts[1]) - 1;
                        System.out.println(horizontalLine);
                        System.out.println(markString);
                        storage.taskList.get(id).marking(true);
                        storage.printMarking(id);
                        System.out.println(horizontalLine);
                        break;
                    } else if (input.startsWith("unmark")) {
                        String[] parts = input.split(" ");
                        int id = Integer.parseInt(parts[1]) - 1;
                        storage.taskList.get(id).marking(false);
                        System.out.println(horizontalLine);
                        System.out.println(unmarkString);
                        storage.printMarking(id);
                        System.out.println(horizontalLine);
                        break;
                    } else {
                        System.out.println(horizontalLine);
                        System.out.println("     added: " + input);
                        System.out.println(horizontalLine);
                        storage.addList(input);
                        break;
                    }

            }
        }
    }
}