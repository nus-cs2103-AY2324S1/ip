import java.util.Scanner;

public class Noel {

    static int maxSize = 100;
    static String[] taskList = new String[maxSize];

    // headPointer points to the first item in the taskList
    static int headPointer = -1;

    // tailPointer points to the next free space in the taskList
    static int tailPointer = 0;


    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public static boolean checkFull(){
        if (headPointer == -1 && tailPointer == -1) {
            return false;
        }
        return tailPointer == headPointer;
    }

    public static void addTaskList(String task){
        if (checkFull()) {
            System.out.println("Array is full!");
        } else {
            taskList[tailPointer] = task;
            tailPointer = (tailPointer+1)%maxSize;
            String updateAdd = "added: " + task;
            printFunction(updateAdd);
            if (headPointer == -1) {
                headPointer = 0;
            }
        }
    }

    public static void printTaskList(){
        if (tailPointer == -1) {
            System.out.println("List is empty!");
        } else {
            String filler = "____________________________________________________________";
            System.out.println(filler);
            int j = 1;
            for (int i = headPointer; i != tailPointer; i = (i + 1) % maxSize) {
                System.out.println(j + ". " + taskList[i]);
                j++;
            }
            System.out.println(filler);
        }
    }

    public static void main(String[] args) {

        String helloMsg = " Hello! I'm Noel!\nWhat can I do for you?";
        String byeMsg = " Bye. Hope to see you again soon!";

        printFunction(helloMsg);

        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();

        while (!nextLine.equals("bye")){

            if (nextLine.equals("list")){
                printTaskList();
            } else {
                addTaskList(nextLine);
            }
            nextLine = in.nextLine();
        }

        printFunction(byeMsg);
    }
}
