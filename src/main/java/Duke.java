import java.util.Scanner;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            System.out.println("-------------------------------\n"
                    + "added: " + this.description
                    + "\n-------------------------------\n");
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); //mark done task with X
        }

        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
            + "[X] " + this.desc());
        }

        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
            + "[ ] " + this.desc());
        }
        public String desc() {
            return this.description;
        }
    }
    public Scanner sc = new Scanner(System.in).useDelimiter("[\\s,]+");
    public Task[] taskArr = new Task[100];
    public void add(String s) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Task(s);
                break;
            }
        }
    }
    public void listOut(Task[] arr) {
        System.out.println("-------------------------------"
        + "\nHere are the tasks in your list:");
        for (int i = 0; i < 100; i++) {
            if (arr[i] != null) {
                Task curr = arr[i];
                int num = i + 1;
                System.out.println(num + ".[" + curr.getStatusIcon() + "] " + curr.desc());
            }
        }
        System.out.println("-------------------------------\n");
    }

    public static void main(String[] args) {

        Duke bot = new Duke();

        String greeting = "-------------------------------\n"
                + "Hello! I'm Skog.\n"
                + "What can I do for you?\n"
                + "-------------------------------\n";

        String exit = "-------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-------------------------------\n";

        System.out.println(greeting);

        while (bot.sc.hasNext()) {
            String str = bot.sc.next();
            if (str.equals("bye")) {
                System.out.println(exit);
                bot.sc.close();
                break;
            } else if (str.equals("list")) {
                bot.listOut(bot.taskArr);
            } else if (str.equals("mark")) {
                int num = bot.sc.nextInt();
                bot.taskArr[num - 1].mark();
            } else if (str.equals("unmark")) {
                int num = bot.sc.nextInt();
                bot.taskArr[num - 1].unmark();
            } else {
                str = str + bot.sc.nextLine();
                bot.add(str);
            }
        }
    }
}
