public class Chatbot {

	private String name;

	Chatbot(String name) {
		this.name = name;
	}


	public void greet() {
		String greeting = String.format("____________________________________________________________\n" +
				" Hello! I'm %s\n" +
				" What can I do for you?\n" +
				"____________________________________________________________\n" +
				" Bye. Hope to see you again soon!\n" +
				"____________________________________________________________\n", this.name);
		System.out.println(greeting);
	}
}
