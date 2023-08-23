public class ListCommand extends Command {
	@Override
	public void execute(Printer out) {
		Object toPrint[] = new Object[Task.tasks.size() + 1];
		toPrint[0] = "Here are the tasks in your list:";
		for (int i = 0; i < Task.tasks.size(); ++i)
			toPrint[i + 1] = String.format("%d.%s", i + 1, Task.tasks.get(i));
		out.print(toPrint);
	}
}
