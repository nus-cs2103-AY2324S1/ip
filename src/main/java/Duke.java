import java.io.*;
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
        File file = new File("file.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] out=st.split(",");
                switch (out[0]){
                    case "T":
                        Todo e =new Todo(out[2]);
                        if(out[1].equals("1"))
                            e.setDone();
                        list.add(e);
                        break;
                    case "E":
                        Event f =new Event(out[2],out[3],out[4]);
                        if(out[1].equals("1"))
                            f.setDone();
                        list.add(f);
                        break;
                    case "D":
                        Deadline g =new Deadline(out[2],out[3]);
                        if(out[1].equals("1"))
                            g.setDone();
                        list.add(g);
                        break;

                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        while (!str.equals("bye")) {
            try {
                if (str.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < list.size(); i++) {
                        int j = i + 1;
                        System.out.println(j + "." + list.get(i) + "");
                    }
                    System.out.println("____________________________________________________________");
                } else if (str.contains("unmark")) {
                    System.out.println("____________________________________________________________");
                    String val = str.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(val);
                    list.get(index-1).setUndone();
                    updateFile(index,false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + list.get(index-1) + "\n");
                    System.out.println("____________________________________________________________");

                } else if (str.contains("mark")) {
                    System.out.println("____________________________________________________________");
                    String val = str.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(val);
                    list.get(index-1).setDone();
                    updateFile(index,true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + list.get(index-1) + "\n");
                    System.out.println("____________________________________________________________");

                } else if (str.startsWith("todo ")) {
                    str = str.substring(5);
                    if (str.isEmpty())
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    list.add(new Todo(str));
                    addToFile("T,0,"+str);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in your list");
                    System.out.println("____________________________________________________________");
                } else if (str.startsWith("deadline ")) {
                    str = str.substring(9);
                    if (str.isEmpty())
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                                "____________________________________________________________");
                    String[] arr = str.split(" /by ");
                    if (arr.length<2)
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! Insufficient parameters passed to deadline.\n" +
                                "____________________________________________________________");
                    list.add(new Deadline(arr[0], arr[1]));
                    addToFile("D,0,"+arr[0]+","+arr[1]);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in your list");
                    System.out.println("____________________________________________________________");
                } else if (str.startsWith("event ")) {
                    str = str.substring(6);
                    if (str.isEmpty())
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a event cannot be empty.\n" +
                                "____________________________________________________________");
                    String[] arr = str.split(" /from ");
                    if (arr.length<2)
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! Insufficient parameters passed to event.\n" +
                                "____________________________________________________________");
                    String[] time = arr[1].split(" /to ");
                    if (time.length<2)
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! Insufficient parameters passed to event.\n" +
                                "____________________________________________________________");
                    list.add(new Event(arr[0], time[0], time[1]));
                    addToFile("E,0,"+arr[0]+","+time[0]+","+time[1]);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println("\t" + list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in your list");
                    System.out.println("____________________________________________________________");
                } else if (str.startsWith("delete ")) {
                    str = str.substring(7);
                    String val = str.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(val);
                    if (str.isEmpty())
                        throw new DukeException("\n____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a delete cannot be empty.\n" +
                                "____________________________________________________________");

                    System.out.println("____________________________________________________________\n" +
                            "Noted. I've removed this task:");
                    System.out.println("\t" + list.get(index - 1));
                    list.remove(index - 1);
                    deleteFromFile(index);
                    System.out.println("Now you have " + list.size() + " tasks in your list");
                    System.out.println("____________________________________________________________");

                } else {
                    throw new DukeException("\n____________________________________________________________\n" +
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "____________________________________________________________");
                }
                str = scanner.nextLine();
            }
            catch (DukeException e){
                System.out.println(e.getMessage());
                str = scanner.nextLine();
            }
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");

    }

    public static void addToFile(String text){
        try {
            File file = new File("file.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.append(text);
            fw.close();
        }
        catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }
    }
    public static void updateFile(int num,boolean val){
        File file = new File("file.txt");
        try {
            Scanner sc = new Scanner(file);
            String line="";
            String out="";
            int count=0;
            while(sc.hasNextLine()){
                line=sc.nextLine();
                count+=1;
                if(count!=num)
                    out+=line+"\n";
                else{
                    if(val)
                        out+=line.substring(0,2)+"1"+line.substring(3)+"\n";
                    else
                        out+=line.substring(0,2)+"0"+line.substring(3)+"\n";
                }
            }
            FileWriter fw=new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
    public static void deleteFromFile(int num) {
        File file = new File("file.txt");
        try {
            Scanner sc = new Scanner(file);
            String line="";
            String out="";
            int count=0;
            while(sc.hasNextLine()){
                line=sc.nextLine();
                count+=1;
                if(count!=num)
                    out+=line+"\n";
            }
            FileWriter fw=new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }


    }

}
