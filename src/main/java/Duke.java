import java.util.Scanner;
public class Duke {

    String[] taskList = new String[100];
    int taskCount = 0;

    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";
        public void chadGreet() {
            System.out.println(line);
            System.out.println("Yo! This is CHADbot\n");
            System.out.println("Need sum help?\n");
            System.out.println(line);
        }
        public void chadBye() {
            System.out.println(line);
            System.out.println("Cya l8r~\n");
            System.out.println(line);
        }

        public void chadOutput(String input) {
            System.out.println(line);
            System.out.println(input + "\n");
            System.out.println(line);
        }
        public void chadAddList(String input) {
            taskList[taskCount] = input;
            taskCount++;
            System.out.println(line);
            System.out.println(input + " has been added to yo list!\n");
            System.out.println(line);
        }
        public void chadListTask() {
            if (taskCount == 0) {
                System.out.println("Your task list is EMPTY!");
            } else {
                System.out.println(line);
                System.out.println("Your outstanding tasks are...");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("Task " + (i + 1) + ") " + taskList[i]);
                }
                System.out.println("\n" + "Get to work NOW!\n");
                System.out.println(line);
            }
        }


    public static void main(String[] args) {
        Duke chad = new Duke();
        chad.chadGreet();

        Scanner scanObj = new Scanner(System.in);

        while(true) {
            String input = scanObj.nextLine();
            if (input.equals("bye")) {
                chad.chadBye();
                break;
            }
            else if (input.equals("list")) {
                chad.chadListTask();
            }
            else {
                chad.chadAddList(input);
            }
        }
        scanObj.close();

    }
}
