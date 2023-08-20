import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskStorage = new Task[100];
        int storageIndex = 0;

        String chatBotName = "Benedit Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");

        boolean dontTerminate = true;

        while (dontTerminate) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dontTerminate = false;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storageIndex; i++) {
                    int itemNumber = i + 1;
                    System.out.println(itemNumber + ". " + taskStorage[i].toString());
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Character.getNumericValue(input.charAt(5));
                Task selectedTask = taskStorage[taskNumber - 1];
                selectedTask.completeTask();
            } else if (input.startsWith("unmark ")) {
                int taskNumber = input.charAt(5);
                Task selectedTask = taskStorage[taskNumber - 1];
                selectedTask.undoTask();
            }  else {
                taskStorage[storageIndex] = new Task(input);
                storageIndex++;
                System.out.println("added: " + input);
            }
        }



    }
}
