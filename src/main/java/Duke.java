import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            else if(str.startsWith("todo ")){
                str=str.substring(5);
                if(str.isEmpty())
                    throw new DukeException("\n____________________________________________________________\n" +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                list.add(new Todo(str));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:");
                System.out.println("\t"+list.get(list.size()-1));
                System.out.println("Now you have "+ list.size()+" tasks in your list");
                System.out.println("____________________________________________________________");
            }
            else if(str.startsWith("deadline ")){
                str=str.substring(9);
                if(str.isEmpty())
                    throw new DukeException("\n____________________________________________________________\n" +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                String[] arr=str.split(" /by ");
                list.add(new Deadline(arr[0],arr[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:");
                System.out.println("\t"+list.get(list.size()-1));
                System.out.println("Now you have "+ list.size()+" tasks in your list");
                System.out.println("____________________________________________________________");
            }
            else if(str.startsWith("event ")){
                str=str.substring(6);
                if(str.isEmpty())
                    throw new DukeException("\n____________________________________________________________\n" +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                String[] arr=str.split(" /from ");
                String[] time=arr[1].split(" /to ");
                list.add(new Event(arr[0],time[0],time[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:");
                System.out.println("\t"+list.get(list.size()-1));
                System.out.println("Now you have "+ list.size()+" tasks in your list");
                System.out.println("____________________________________________________________");
            }
            else if(str.startsWith("delete ")){
                str=str.substring(7);
                String val= str.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(val);
                if(str.isEmpty())
                    throw new DukeException("\n____________________________________________________________\n" +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");

                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task:");
                System.out.println("\t"+list.get(index-1));
                list.remove(index-1);
                System.out.println("Now you have "+ list.size()+" tasks in your list");
                System.out.println("____________________________________________________________");

            }
            else {
                throw new DukeException("\n____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
            }
            str = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

}
