import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveFile {
	private String filepath;

	public SaveFile(String filepath) {
		this.filepath = filepath;
	}

	public List<Task> readFromSaveFile() throws FileNotFoundException, DukeException {
		List<Task> tasks = new ArrayList<>();
		File f = new File(filepath);
		Scanner in = new Scanner(f);
		while (in.hasNext()) {
			CommandStructure cs = CommandStructure.parse(in.nextLine().trim());
			tasks.add(Task.createTask(cs.command, cs.name, cs.arguments));
		}
		return tasks;
	}

	public void saveToSaveFile(TaskList taskList) {
		File f = new File(filepath);
		if(!f.isFile()) {
			f.mkdir();
			try {
				f.createNewFile();
			} catch (IOException e) {

			}
		}

		try {
			FileWriter out = new FileWriter(f);
			out.write(taskList.getTasksAsCommands());
			out.close();
		} catch(IOException e) {
		}
	}

}
