import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class for Echo Chatbot
 *
 * @author Jason Ray
 */
public class Echo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Task> list = new ArrayList<>();

		String LINE_BREAK = "----------------------------------------------------------";
		System.out.println(LINE_BREAK);
		System.out.println("Welcome. My name is Echo");
		System.out.println("What will you do today?");
		System.out.println(LINE_BREAK);
		System.out.println("\n");

		while (true) {
            String input = sc.nextLine().trim();
			String[] split = input.toLowerCase().split(" ");
			if (split[0].equals("list")) {
				if (split.length > 1) {
					System.out.println("Please type only 'list' to view the list of tasks");
				}
				System.out.println(LINE_BREAK);
				if (list.size() == 0) {
					System.out.println("List is empty");
				} else {
					for (int i = 1; i < list.size() + 1; i++) {
						Task current = list.get(i - 1);
						if (current.getType().equals("TODO")) {
							ToDo task = (ToDo) current;
							System.out.println(i + ". [T]"
							+ "[" + (task.getDone() ? "X" : " ") + "] "
							+ task.getName());
						} else if (current.getType().equals("DEADLINE")) {
							Deadline task = (Deadline) current;
							System.out.println(i + ". [D]"
							+ "[" + (task.getDone() ? "X" : " ") + "] "
							+ task.getName() + " (by: "+ task.getDeadline() + ")");
						} else if (current.getType().equals("EVENT")){
							Event task = (Event) current;
							System.out.println(i + ". [E]"
							+ "[" + (task.getDone() ? "X" : " ") + "] "
							+ task.getName() + " (from: "+ task.getStart() + " to: " + task.getEnd() + ")");
						}
					}
				}
				System.out.println(LINE_BREAK);
				System.out.println("\n");
			} else if (split[0].equals("mark"))  {
				if (split.length == 1) {
					System.out.println(LINE_BREAK);
					System.out.println("Please specify a task number");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
					continue;
				}
				if (list.size() < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have not added any tasks yet");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
					continue;
				}
				int taskNum = Integer.parseInt(input.substring(5));
				if (taskNum > list.size() || taskNum < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have typed in an invalid number, please try again");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				} else {
					list.get(taskNum - 1).markAsDone();
				}
			} else if (split[0].equals("unmark")) {
				if (split.length == 1) {
					System.out.println(LINE_BREAK);
					System.out.println("Please specify a task number");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
					continue;
				}
				if (list.size() < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have not added any tasks yet");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
					continue;
				}
				int taskNum = Integer.parseInt(input.substring(7));
				if (taskNum > list.size() || taskNum < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have typed in an invalid number, please try again");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				} else {
					list.get(taskNum - 1).markAsNotDone();
				}
			} else if (split[0].equals("todo")) {
				String taskName = input.substring(5);
				ToDo.printTaskAdded(taskName, list);
			} else if (split[0].equals("deadline")) {
				String[] taskDesc = input.substring(9).split("/by");
				if (taskDesc.length < 2) {
					System.out.println("Please specify a deadline by adding /by tag");
					continue;
				}
				String taskName = taskDesc[0].trim();
				String deadline = taskDesc[1].trim();
				Deadline.printTaskAdded(taskName, deadline, list);
			} else if (split[0].equals("event")) {
				String[] taskDesc = input.substring(6).split("/from");
				if (taskDesc.length < 2) {
					System.out.println("Please specify both start and end times by adding /from and /to tags");
					continue;
				} else if (taskDesc.length > 2) {
					System.out.println("Please specify only one start time");
					continue;
				}
				String taskName = taskDesc[0].trim();
				String[] fromAndTo = taskDesc[1].split("/to");
				if (fromAndTo.length < 2) {
					System.out.println("Please specify both start and end times by adding /from and /to tags");
					continue;
				} else if (taskDesc.length > 2) {
					System.out.println("Please specify only one end time");
					continue;
				}
				String start = fromAndTo[0].trim();
				String end = fromAndTo[1].trim();
				Event.printTaskAdded(taskName, start, end, list);
			} else if (split[0].equals("bye")) {
				System.out.println(LINE_BREAK);
				System.out.println("I hope you enjoy my service. Thank you and goodbye");
				System.out.println(LINE_BREAK);
				break;
			} else {
				System.out.println(LINE_BREAK);
				System.out.println("Please type in a valid command");
				System.out.println(LINE_BREAK);
				System.out.println("\n");
			} 
        }
	}
}
