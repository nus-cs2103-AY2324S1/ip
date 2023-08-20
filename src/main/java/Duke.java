import java.util.Scanner;
public class Duke {
    static Task[] storage;
    static int storagePointer;

    static public class Task {
        private boolean isComplete;
        private String name;
        public Task(String name, boolean isComplete) {
            this.isComplete = isComplete;
            this.name = name;
        }

        public boolean isComplete() {
            return this.isComplete;
        }

        public void markDone() {
            this.isComplete = true;
        }

        public void markUndone() {
            this.isComplete = false;
        }
        public String getName() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        // Initialise the data storage
        Duke.storage = new Task[100];
        Duke.storagePointer = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatbotName = "notDuke";
        String introMessage = "Hello! I'm " + chatbotName + "\n"
                        + "What can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(introMessage);
        Duke.echo();
        System.out.println(exitMessage);
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            Task task = new Task(userInput, false);
            String command = userInput.split("\\s+")[0];
            int choice;
            switch (command) {
                case "list":
                    for (int i = 1; i <= Duke.storagePointer; i++) {
                        Task item = Duke.storage[i - 1];
                        String itemMarked = " ";
                        if (item.isComplete()) {
                            itemMarked = "X";
                        }
                        System.out.printf("%d.[%s] %s\n", i, itemMarked, item.getName());
                    }
                    userInput = scanner.nextLine();
                    break;
                case "mark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage[choice - 1].markDone();
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "  [X] %s\n", Duke.storage[choice - 1].getName());
                    userInput = scanner.nextLine();
                    break;
                case "unmark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage[choice - 1].markUndone();
                    System.out.printf("OK, I've marked this task as not done yet:\n" +
                            "  [ ] %s\n", Duke.storage[choice - 1].getName());
                    userInput = scanner.nextLine();
                    break;
                default:
                    Duke.storage[Duke.storagePointer] = task;
                    Duke.storagePointer ++;
                    System.out.println("added: " + userInput + "\n");
                    userInput = scanner.nextLine();
            }
        }
    }
}
