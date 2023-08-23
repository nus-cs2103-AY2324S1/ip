import java.util.Scanner;

public class BenBen {
    //private static String[] arr;
    private static Task[] arr;
    //private static boolean[] bool;
    private static final String line ="_______________________________________\n";
    private static int counter = 0;

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

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

        public String description() {
            return this.description;
        }
    }

    public static void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!\n" + line);
        System.exit(0);
    }

    public static void add(String str) {
        Task t = new Task(str);
        arr[counter] = t;
        counter++;
        System.out.println(line);
        System.out.println("added: " + t.description());
        System.out.println(line);
    }

    public static void iterList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list: ");
      for(int i = 0; i < counter; i++) {
              System.out.println((i + 1) + "." + arr[i].toString());
      }
        System.out.println(line);
    }

    public static void mark(String str) {
        String[] strSplit = str.split("\\s+");
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            arr[x - 1].mark();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n" +
                    "    " + arr[x - 1].toString());
            System.out.println(line);
        } catch(NumberFormatException e) {
            System.out.println("Please indicate the integer index of the task that you want to mark");
        } catch(NullPointerException e) {
            System.out.println("Please indicate the integer index of the task that you want to mark");
        }


    }

    public static void unmark(String str) {

        String[] strSplit = str.split("\\s+");
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            arr[x - 1].unmark();

            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    "    " + arr[x - 1].toString());
            System.out.println(line);
        } catch(NumberFormatException e) {
            System.out.println("Please indicate the integer index of the task that you want to unmark");
        } catch(NullPointerException e) {
            System.out.println("Please indicate the integer index of the task that you want to unmark");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new Task[100];
        System.out.println(line);
        System.out.println("Hello! I'm BenBen.\n" +
                "What can I do for you?");
        System.out.println(line);;

        while(sc.hasNext()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                exit();
                break;
            }

            if (next.equals("list")) {
                iterList();
                continue;
            }

            if (next.startsWith("mark")) {
                mark(next);
                continue;
            }

            if (next.startsWith("unmark")) {
                unmark(next);
                continue;
            }

            add(next);
        }
        System.exit(0);
    }
}
