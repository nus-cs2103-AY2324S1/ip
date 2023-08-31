import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> myList = new ArrayList<>();
    static int numOfItems = 0;

    public TaskList() {
        myList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    public static void add(String task) throws MaxException {
        // Parse command based on type of task (Todo, Deadline, Event)
        if (task.startsWith("todo")) {
            // Error checking: empty fields
            if (task.length() < 6) {
                throw new MaxException("     Watch out -- todo description cannot be empty.");
            }

            String description = task.substring(5).trim();

            myList.add(new Todo(description));
        } else if (task.startsWith("deadline")) {
            int byIndex = task.indexOf("/by");

            // Error checking: no /by tag
            if (byIndex == -1) {
                throw new MaxException("     Try again... deadline must include a '/by' tag!");
            }

            String item = task.substring(8, byIndex - 1).trim();
            String by = task.substring(byIndex + 3).trim();

            LocalDate byDate = LocalDate.parse(by);

            // Error checking: empty fields
            if (item.isEmpty() || by.isEmpty()) {
                throw new MaxException("     Oops... Deadline item or 'by' date cannot be empty.");
            }

            myList.add(new Deadline(item, byDate));
        } else if (task.startsWith("event")) {
            int fromIndex = task.indexOf("/from");
            int toIndex = task.indexOf("/to");

            // Error checking: no /from or /to tag
            if (fromIndex == -1 || toIndex == -1) {
                throw new MaxException("     Hey! Event must contain '/from' and '/to' tags.");
            }

            String item = task.substring(5, fromIndex - 1).trim();
            String from = task.substring(fromIndex + 5, toIndex -1).trim();
            String to = task.substring(toIndex + 3).trim();


            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);

            // Error checking: empty fields
            if (item.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new MaxException("     Oh no! Event item, 'from' date, or 'to' date cannot be empty.");
            }

            myList.add(new Event(item, fromDate, toDate));
        }

        // Visual feedback from chatbot
        System.out.println("     I gotchu. I've added this task:");
        System.out.println("       " + myList.get(numOfItems));

        // Increment pointer
        numOfItems++;
        if (numOfItems == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + numOfItems + " tasks in the list.");
        }
    }

    public static void list() {
        System.out.println("     Here are all your tasks:");

        // Iterate through ArrayList of tasks and enumerate them
        for (int i = 0; i < numOfItems; i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + myList.get(i));
        }
    }
    public static void delete(int taskNumber) throws MaxException {
        if (taskNumber > numOfItems) {
            throw new MaxException("     Seems like that number is out of range. Check again!");
        }

        Task toDelete = myList.get(taskNumber - 1);
        myList.remove(toDelete);
        numOfItems--;

        // Visual feedback
        System.out.println("     Alright. I have removed this task:");
        System.out.println("       " + toDelete);
        if (numOfItems == 1) {
            System.out.println("     Now you have 1 task left.");
        } else {
            System.out.println("     Now you have " + numOfItems + " tasks left.");
        }
    }
}
