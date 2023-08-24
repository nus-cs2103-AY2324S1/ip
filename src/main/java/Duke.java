import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            protected String name;
            protected boolean isDone = false;

            public Task(String name) {
                this.name = name;
            }

            public void mark(){
                isDone = true;
                System.out.println("Nice! I've marked this task as done:\n [X] " + name +"\n");
                System.out.println("____________________________________________________________\n");
            }

            public void unmark(){
                isDone = false;
                System.out.println("OK, I've marked this task as not done yet:\n [ ] " + name + "\n");
                System.out.println("____________________________________________________________\n");
            }

            public String printTask() {
                return (isDone) ? "[X] " + name + "\n": "[ ] " + name + "\n";
            }
        }

        Scanner scanner = new Scanner(System.in);
        Task tasks[] = new Task[100];

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm CarrotCake\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        String input = scanner.nextLine();
        int count = 0;

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (input.toLowerCase().equals("list")) {
                //Print tasks
                for (int i = 0; i < count; i++) {
                    System.out.print(Integer.toString(i+1) + ". " + tasks[i].printTask());
                }
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            }
            if (input.toLowerCase().startsWith("mark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                tasks[taskNumber].mark();
                input = scanner.nextLine();
                continue;
            }
            if (input.toLowerCase().startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                tasks[taskNumber].unmark();
                input = scanner.nextLine();
                continue;
            }


            System.out.println("added: " + input +
                    "\n____________________________________________________________\n");

            tasks[count] = new Task(input);
            input = scanner.nextLine();
            count++;
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
