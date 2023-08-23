import java.util.Scanner;

public class Cracker {


    private static class Reply {
        private String line = "____________________________________________________________";
        private String content;
        Reply(String s){
            this.content = s;
        }

        public void echo(){
            System.out.println(line);
            System.out.println(content);
            System.out.println(line);
        }
    }
    public static void main(String[] args) {
        boolean talking = true;
        Reply greet = new Reply("What can I do for you?");
        Reply bye = new Reply("Bye. Hope to see you again soon!");

        greet.echo();

        while(talking){
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            if(input.equals("bye")){
                sc.close();
                break;
            }
            Reply something = new Reply(input);
            something.echo();

        }



        bye.echo();



        
    }

}
