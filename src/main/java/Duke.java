import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Mathan";
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm "+name+"\n" +
                " What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        ArrayList<Task> list = new ArrayList<Task>();
        while (!str.equals("bye")){
            if(str.equals("list")){
                System.out.println("____________________________________________________________");
                for(int i=0;i<list.size();i++){
                    int j=i+1;
                    System.out.println(j+"."+list.get(i)+"");
                }
                System.out.println("____________________________________________________________");
            }
            else if(str.contains("unmark")){
                System.out.println("____________________________________________________________");
                String val= str.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(val);
                index -=1;
                list.get(index).setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                int j=index+1;
                System.out.println("\t"+list.get(index)+"\n");
                System.out.println("____________________________________________________________");

            }
            else if(str.contains("mark")){
                System.out.println("____________________________________________________________");
                String val= str.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(val);
                index -=1;
                list.get(index).setDone();
                System.out.println("Nice! I've marked this task as done:");
                int j=index+1;
                System.out.println("\t"+list.get(index)+"\n");
                System.out.println("____________________________________________________________");

            }

            else {
                list.add(new Task(str));

                System.out.println("____________________________________________________________\n" +
                        str + "\n" +
                        "____________________________________________________________");
            }
            str = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

}
