import java.util.Scanner;

public class Duke {

    enum TaskType {
        ToDo ("T"),
        Deadline ("D"),
        Event ("E");
        private final String sr;
        TaskType(String sr){
            this.sr = sr;
        }

    }

    static Scanner sc = new Scanner(System.in);
    static String[] store = new String[100];
    static boolean[] completed = new boolean[100];
    static TaskType[] tt = new TaskType[100];
    static String[] dl = new String[100];
    static int counter = 0;

    public static String getString(String[] a, int x, int y){
        String res = "";
        for(int i=x;i<y;i++){
            if(a[i].charAt(0) == '/') res += a[i].substring(1) + ":";
            else res += a[i];
            if(i!=y-1) res += " ";
        }
        return res;
    }
    public static String formatItem(int x){
        String cBox = "[" + (completed[x] ? "X" : " ") + "] ";
        String tBox = "[" + tt[x].sr + "]";
        String taco = dl[x] == null ? "" : " (" + dl[x] + ")";
        return  tBox + cBox + " " + store[x] + taco;
    }
    public static void main(String[] args) {

        System.out.println("─────────────────────");
        System.out.println("Hello I'm Robot!");
        System.out.println("What can I do for you?");
        System.out.println("─────────────────────");


        while(true){
            String userInput = sc.nextLine();
            String[] splitStr = userInput.split("\\s+");
            System.out.println("─────────────────────");
            if(userInput.equals("bye")) break;

            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<counter;i++){
                     System.out.println(Integer.toString(i+1)  + "." + formatItem(i));
                }
            }else if(splitStr.length == 2 && splitStr[0].equals("mark")){
                int x = Integer.parseInt(splitStr[1])-1;
                completed[x] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(formatItem(x));

            }else if(splitStr.length == 2 && splitStr[0].equals("unmark")){

                int x = Integer.parseInt(splitStr[1])-1;
                completed[x] = false;
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(formatItem(x));

            }else if(splitStr[0].equals("todo")){
                tt[counter] = TaskType.ToDo;
                store[counter++] = getString(splitStr, 1, splitStr.length);;
                System.out.println("Got it, I've added this task:");
                System.out.println(formatItem(counter-1));
                System.out.println("Now you have " + counter + " tasks in the list.");

            }else if(splitStr[0].equals("deadline")){
                for(int i=1;i<splitStr.length;i++){
                    if(splitStr[i].equals("/by")) {
                        tt[counter] = TaskType.Deadline;
                        dl[counter] = getString(splitStr, i, splitStr.length);
                        store[counter++] = getString(splitStr, 1, i);
                        break;
                    }
                }
                System.out.println(formatItem(counter-1));
                System.out.println("Now you have " + counter + " tasks in the list.");

            }else if(splitStr[0].equals("event")){
                for(int i=1;i<splitStr.length;i++){
                    if(splitStr[i].equals("/from")) {
                        tt[counter] = TaskType.Event;
                        dl[counter] = getString(splitStr, i, splitStr.length);
                        store[counter++] = getString(splitStr, 1, i);
                        break;
                    }
                }
                System.out.println(formatItem(counter-1));
                System.out.println("Now you have " + counter + " tasks in the list.");
            }
            System.out.println("─────────────────────");

        }


        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("─────────────────────");
    }
}
