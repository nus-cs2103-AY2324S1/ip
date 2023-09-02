import java.util.ArrayList;
import java.util.Scanner;

public class Dan {
    public static void main(String[] args) {
        hello();
        chat();
        goodbye();
    }


    private final static String greets = "\nDan: \n";

    private static MyList strList = new MyList(100);
    private static void chat() {
        String text;
        while (true) {
            text = new Scanner(System.in).nextLine();
            switch (text.toLowerCase()) {
                case "bye" : break;

                case "list" :
                    System.out.println(
                            greets +
                            "你还要做的事情有：" + strList.toString()
                    );
            }
            System.out.println(greets + text);
        }
//        !text.toLowerCase().equals("bye")
    }
    public static void hello() {
        System.out.println(
                greets +
                " 我是小丹！\n" +
                " 有什么可以要帮忙可以跟我说！\n"
        );
    }
    public static void goodbye() {
        System.out.println(
                greets +
                " 拜拜啦！下次见！\n"
        );
    }
}
