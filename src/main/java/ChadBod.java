    package main.java;
    import java.util.Scanner;
    import java.util.ArrayList;


    public class ChadBod {

        public static void main(String[] args) {
            System.out.println("Hello! I'm ChadBod.\n");
            System.out.println("What can I do for you?\n");
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            boolean shouldExit = false;

            while (!shouldExit) {
                String input = sc.nextLine();
                String[] command = input.split(" ", 2);
                switch (command[0]) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!\n");
                        shouldExit = true;
                        break;
                    case "list":
                        for (int i = 0; i < tasks.size(); i ++) {
                            System.out.printf("%d: %s\n", i + 1, tasks.get(i));
                        }
                        break;
                    case "mark":
                        try {
                            int taskNumber = Integer.parseInt(command[1]);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                Task task = tasks.get(taskNumber - 1);
                                task.markDone();
                                System.out.println("Nice! I've marked this task as done:\n");
                                System.out.printf("%s\n", task);
                            } else {
                                System.out.println("Invalid task index.\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for marking task.\n");
                        }
                        break;
                    case "unmark":
                        try {
                            int taskNumber = Integer.parseInt(command[1]);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                Task task = tasks.get(taskNumber - 1);
                                task.markUndone();
                                System.out.println("OK, I've marked this task as not done yet:\n");
                                System.out.printf("%s\n", task);
                            } else {
                                System.out.println("Invalid task index.\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for unmarking task.\n");
                        }
                        break;
                    default:
                        tasks.add(new Task(input));
                        System.out.printf("added: %s\n", input);
                }
            }
        }
    }
