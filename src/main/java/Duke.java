import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greetFunction("Jack");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        ArrayList<String> textArray = new ArrayList<>();

        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println(horiLine);
                for(int i = 0; i < textArray.size(); i++ ){
                    int index = i + 1;
                    System.out.println(index +": " + textArray.get(i));
                }
                System.out.println(horiLine);
            }else{
                textArray.add(input);
                addSuccess(input);
            }

            input = scanner.nextLine();
        }
        byeFunction();
        scanner.close();



    }

    public static void greetFunction(String name){
        String greetings = horiLine +"\nHello! I'm " + name + "\n"
                + "What can I do for you?\n" + horiLine;
        System.out.println(greetings);
    }
    public static void byeFunction(){

        String byeword = horiLine + "\nBye. Hope to see you again soon\n" + horiLine;
        System.out.println(byeword);
    }
    public static void repeatFunction(String text){
        String byeword = horiLine +"\n" + text + "\n" + horiLine;
        System.out.println(byeword);
    }
    public static void addSuccess(String text){

        String word = horiLine +"\nadded:" + text + "\n" + horiLine;

        System.out.println(word);
    }



}
