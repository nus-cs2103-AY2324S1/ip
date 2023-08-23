import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Siri");
        System.out.println("What can I do for you?");
        program();
        exit();
    }

    private static class Task {
        private final String taskName;
        private boolean isComplete = false;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        public void markComplete() {
            this.isComplete = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }

        public void markIncomplete() {
            this.isComplete = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            char mark = isComplete ? 'X' : ' ';
            return "[" + mark + "] " + this.taskName;
        }
    }

    private static void program() {
        Task[] store = new Task[100];
        int size = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String word = sc.nextLine();
            if (word.equals("bye")) {
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < size; ++i) {
                    System.out.println(i + 1 + ". " + store[i]);
                }
            } else if (word.startsWith("mark ")) {
                int target = Integer.parseInt(word.substring(5)) - 1;
                store[target].markComplete();
            } else if (word.startsWith("unmark ")) {
                int target = Integer.parseInt(word.substring(7)) - 1;
                store[target].markIncomplete();
            } else {
                store[size++] = new Task(word);
                System.out.println("added: " + word);
            }
        }
    }

    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
