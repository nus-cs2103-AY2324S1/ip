import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Task {
        protected String description;

        public Task(String description) {
            this.description = description;
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
                System.out.println("     " + index + ". " + t.description);
            }
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
        String horizontalLine = "   ---------------------------------------------------";
        String intro = "    Hello! I'm iPbot \n    What can I do for you?";
        String outro = "    Bye. Hope to see you again soon!";

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
                    System.out.println(horizontalLine);
                    System.out.println("     added: " + input);
                    System.out.println(horizontalLine);
                    storage.addList(input);
                    break;
            }
        }
    }
}