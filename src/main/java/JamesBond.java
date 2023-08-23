import java.util.Scanner;
public class JamesBond {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "YO! The name's Bond, James Bond.  \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n" ;
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        User user = new User(sc);
        }
    }
