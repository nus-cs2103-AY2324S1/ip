import java.util.Scanner;

public class Dan {
    public static void main(String[] args) {
        hello();
        chat();
        goodbye();
    }


    private final static String greets = "\nDan: \n";

    private static MyList tasks = new MyList(100);
    private static void chat() {
        String text;
        Task currTask;
        while (true) {
            text = new Scanner(System.in).nextLine().toLowerCase();
            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                System.out.println(
                        greets + " 你还有些要做的事情呢 我看看有什么吧！\n" +
                        tasks.toString() + "\n"
                );
            } else if (text.matches("mark [0-9]+")) {
                currTask = markTask(text, 0);
                if (currTask == null) {
                    System.out.println(greets + " 这个已经做完了哦！\n");
                }
                System.out.println(
                        greets + " 哟 做完啦？帮你标记好了！\n " +
                        currTask.toStringAll() + "\n"
                );
            } else if (text.matches("unmark [0-9]+")) {
                currTask = markTask(text, 1);
                if (currTask == null) {
                    System.out.println(greets + " 这个没标记过哦！\n");
                }
                System.out.println(
                        greets + " 啊？没做完啊 是不小心手滑了么？\n " +
                        currTask.toStringAll() + "\n"
                );
            } else {
                Task newTask = new Task(text);
                tasks.add(newTask);
                System.out.println(greets + " 新任务：" + newTask + "!\n");
            }
        }
    }

    private static Task markTask(String text, int id) {
        String[] texts = text.split(" ");
        int taskIndex = Integer.parseInt(texts[1]) - 1;
        Task currTask = tasks.get(taskIndex);
        boolean succ = currTask.mark(id);
        if (!succ) {
            return null;
        }
        return currTask;
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
