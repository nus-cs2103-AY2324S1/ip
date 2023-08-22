//public printer class that abstracts and does the printing of the outputs

public class Printer {

    static String line = "---------------------";

    static String greeting = " Hello! I'm Somebodyhaha\n" +
            " What can I do for you?";
    static String exiting = " Bye. Hope to See you again soon!";


    public static void printGreeting(){
        print(greeting);
    }

    public static void printExit() {
        print(exiting);
    }
    public static void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public static void printList(String[] lst){
        int count = 1;
        System.out.println(line);
        if(lst != null) {
            for (String item: lst) {
                if(item == null) {break;}
                String str = count + ". " + item;
                System.out.println(str);
                count++;
            }
        }
        System.out.println(line);
    }
}
