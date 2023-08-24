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
        ArrayList<String> list = new ArrayList<>();
        while (!str.equals("bye")){
            if(str.equals("list")){
                System.out.println("____________________________________________________________");
                for(int i=0;i<list.size();i++){
                    int j=i+1;
                    System.out.println(j+". "+list.get(i)+"");
                }
                System.out.println("____________________________________________________________");
            }
            else {
                list.add(str);

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
