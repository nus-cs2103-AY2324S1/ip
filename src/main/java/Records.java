import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Records {
	private String filePath;
	private ArrayList<Task> tasks;
	public Records(String filePath, ArrayList<Task> tasks) {
		this.filePath = filePath;
		this.tasks = tasks;
	}
	public void writeFile() throws IOException {

		try {
			FileWriter fw = new FileWriter(filePath, true);
			for (Task t: tasks) {
				fw.write(t.writeToFile());
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			throw new IOException("write fail");
		}

	}


	public ArrayList<Task> readFile() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
			StringBuilder br = new StringBuilder();
			String fileLine;
			while ((fileLine = in.readLine()) != null) {
				// append raw unformatted version
				br.append(fileLine);
				br.append("\n");
				addTask(tasks, fileLine);
			}
		}
		return tasks;
	}
	public void addTask(ArrayList<Task> t, String fileLine) throws IOException {
		String[] str = fileLine.split("\\|");
		for (int i  = 0; i < str.length; i++) {
			String s = str[i].trim();
			str[i] = s;
		}
		StringBuilder br = new StringBuilder();
		String taskType = str[0];
		String isDone = str[1];
		switch(taskType) {
		case "T":
			t.add(new ToDos(str[2]));
			break;
		case "D":
			t.add(new Deadline(str[2], str[3]));
			break;
		case "E":
			t.add(new Event(str[2], str[3], str[4]));
			break;
		default:
			throw new IOException("read fail");
		}
	}

//	public String fileToString(String fileLine, int idx) throws IOException {
//		String[] str = fileLine.split("\\|");
//		for (int i  = 0; i < str.length; i++) {
//			String s = str[i].trim();
//			str[i] = s;
//		}
//		StringBuilder br = new StringBuilder();
//		String taskType = str[0];
//		String isDone = str[1];
//		int idxList = idx + 1;
//		br.append(idxList).append(". ");
//		switch(taskType) {
//			case "T":
//				br.append("[T]");
//				if (Integer.parseInt(isDone) == 1) {
//					br.append("[X]");
//				} else {
//					br.append("[ ]");
//				}
//				br.append(" ").append(str[2]);
//				break;
//			case "D":
//				br.append("[D]");
//				if (Integer.parseInt(isDone) == 1) {
//					br.append("[X]");
//				} else {
//					br.append("[ ]");
//				}
//				br.append(" ").append(str[2]).append(" ");
//				br.append("(by: ").append(str[3]).append(")");
//				break;
//			case "E":
//				br.append("[E]");
//				if (Integer.parseInt(isDone) == 1) {
//					br.append("[X]");
//				} else {
//					br.append("[ ]");
//				}
//				br.append(" ").append(str[2]).append("(from: ").append(str[3]);
//				br.append(" to: ").append(str[4]).append(")");
//				break;
//			default:
//				throw new IOException("file saved has errors");
//		}
//		return br.toString();
//	}
}
