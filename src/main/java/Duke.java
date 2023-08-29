import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        String logo = "Bacon Pancake";
        System.out.println(" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n");
        Task[] lists = new Task[100];
        int length = 0;
        Scanner sc = new Scanner(System.in);
        String filename = "src/save.txt";
        File f = new File(filename);
        while(true) {
            System.out.println("Enter your input : ");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bacon Pancake : \n" + "Bye! See you again soon ");
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("Bacon Pancake : Below are the lists\n");
                int curr = 1;
                for (int i = 0; i < length; i++) {
                    if (lists[i] != null) {
                        Task t = lists[i];
                        System.out.println((curr++) + ". " + t.getStatus());
                    }
                }
            } else if(input.startsWith("mark ")) {
                System.out.println("Bacon Pancake : Nice! I've marked this task as done:");
                int current = Integer.parseInt(input.substring(5)) - 1;
                if (lists[current] != null ) {
                    System.out.println(lists[current].mark());
                } else {
                    System.out.println("Nothing to mark");
                }
            } else {
                try {
                    Task curr = new Task(input.substring(input.indexOf(" ")), input.substring(0, input.indexOf(" ")));
                    lists[length++] = curr;
                    System.out.println("Bacon Pancake : \n Added : " + curr.description);
                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch(Exception e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            try {
                int curr = 1;
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    if (lists[i] != null) {
                        Task t = lists[i];
                        s.append((curr++) + ". " + t.getStatus() + "\n");
                    }
                }
                writeToFile(filename, s.toString());
            } catch(IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

    }
}
