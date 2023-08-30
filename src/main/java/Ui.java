import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui(){
        scanner=new Scanner(System.in);
    }
    public String readCommand(){
        return scanner.nextLine();
    }
    public void showWelcome(){
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Mathan\n" +
                " What can I do for you?");
    }
    public void showExit(){
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
    public void showLine(){
        System.out.println("____________________________________________________________");
    }
    public void print(String str){
        System.out.println(str);
    }
    public void handleError(DukeException e){
        System.out.println(e.getMessage());
    }
}
