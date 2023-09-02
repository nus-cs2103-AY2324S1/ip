package duke.utilities;

/**
 * Deals with storing input data
 */
public class Input {
	private String command;
	private String fullInput;
	private int wordLength;

	public Input(String command, String fullInput, int wordLength) {
		this.command = command;
		this.fullInput = fullInput;
		this.wordLength = wordLength;
	}

	public String getCommand() {
		return this.command;
	}

	public int getLength() {
		return this.wordLength;
	}
	
	public String getFullInput() {
		return this.fullInput;
	}
}
