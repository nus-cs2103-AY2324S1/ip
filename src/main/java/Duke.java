import java.util.Scanner;
public class Duke {
    public class Task {
        public String name;
        public boolean isComplete;

        public Task(String name) {
            this.name = name;
            this.isComplete = false;
        }

        public String getMark() {
            return (isComplete ? "X" : " ");
        }

    }

    Task[] taskList = new Task[100];
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
            taskList[taskCount] = new Task(input);
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
                    System.out.println("Task " + (i + 1) + ") " + taskList[i].name + " [" + taskList[i].getMark() + "]");
                }
                System.out.println("\n" + "Get to work NOW!\n");
                System.out.println(line);
            }
        }

        public void chadMarkTask(int index) {
            taskList[index - 1].isComplete = true;
            System.out.println(line);
            System.out.println("Good job! Task fulfilled!");
            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(line);
        }

        public void chadUnmarkTask(int index) {
            taskList[index - 1].isComplete = false;
            System.out.println(line);
            System.out.println("Boooo! Task is not done!");
            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(line);
        }




    public static void main(String[] args) {
        Duke chad = new Duke();
        chad.chadGreet();

        Scanner scanObj = new Scanner(System.in);

        while(true) {
            String input = scanObj.nextLine();
            String[] inputArray = input.split(" ", 2);
            if (inputArray[0].equals("bye")) {
                chad.chadBye();
                break;

            } else if (inputArray[0].equals("list")) {
                chad.chadListTask();

            } else if (inputArray[0].equals("mark")) {
                Integer index = Integer.valueOf(inputArray[1]);
                chad.chadMarkTask(index);

            } else if (inputArray[0].equals("unmark")) {
                Integer index = Integer.valueOf(inputArray[1]);
                chad.chadUnmarkTask(index);

            } else {
                chad.chadAddList(input);
            }
        }
        scanObj.close();

    }
}
