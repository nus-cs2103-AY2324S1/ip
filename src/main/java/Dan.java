import java.util.Scanner;

public class Dan {
    private final static String greets = "\nDan: \n";
    private static MyList tasks = new MyList(100);
    private static int n = tasks.size();


    public static void main(String[] args) {
        hello();
        chat();
        goodbye();
    }



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
                        currTask.toString() + "\n"
                );
            } else if (text.matches("unmark [0-9]+")) {
                currTask = markTask(text, 1);
                if (currTask == null) {
                    System.out.println(greets + " 这个没标记过哦！\n");
                }
                System.out.println(
                        greets + " 啊？没做完啊 是不小心手滑了么？\n " +
                        currTask.toString() + "\n"
                );
            } else {
                addTask(text);
            }
        }
    }

    public static void addTask(String text) {
        String[] texts = text.split("/");
        for (int i = 0; i < texts.length; i++) {
            texts[i] = texts[i].trim();
        }
        Task newTask = null;
        String description;
        switch (texts.length) {
            case 1 :
                description = texts[0].substring(5);
                newTask = new ToDo(description);
                break;
            case 2 :
                description = texts[0].substring(9);
                String deadline = texts[1].substring(3);
                newTask = new Deadline(description, deadline);
                break;
            case 3 :
                description = texts[0].substring(5);
                String start = texts[1].substring(5);
                String end = texts[2].substring(3);
                newTask = new Event(description, start, end);
                break;
        }
        tasks.add(newTask);
        System.out.println(
                greets + " 新任务：\n " + newTask +
                "!\n 现在有" + n + "项任务哦！\n");
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
