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
				// to implement
//				System.out.println(t.writeToFile());
				fw.write(t.writeToFile());
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("not good");
		}

	}

	public void readFile() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
			StringBuilder br = new StringBuilder();
			String line;
			int i = 0;
			while((line = in.readLine()) != null) {
				br.append(fileToString(line, i));
				i++;
				br.append("\n");
			}
			System.out.println(br.toString());
		}
//		catch(IOException e) {
//			System.out.println(e.getMessage());
//		}
	}
	public String fileToString(String fileLine, int idx) throws IOException {
		String[] str = fileLine.split("\\|");
		for (int i  = 0; i < str.length; i++) {
			String s = str[i].trim();
			str[i] = s;
		}
		StringBuilder br = new StringBuilder();
		String taskType = str[0];
		String isDone = str[1];
		int idxList = idx + 1;
		br.append(idxList).append(". ");
		switch(taskType) {
		case "T":
			br.append("[T]");
			if (Integer.parseInt(isDone) == 1) {
				br.append("[X]");
			} else {
				br.append("[ ]");
			}
			br.append(" ").append(str[2]);
			break;
		case "D":
			br.append("[D]");
			if (Integer.parseInt(isDone) == 1) {
				br.append("[X]");
			} else {
				br.append("[ ]");
			}
			br.append(" ").append(str[2]).append(" ");
			br.append("(by: ").append(str[3]).append(")");
			break;
		case "E":
			br.append("[E]");
			if (Integer.parseInt(isDone) == 1) {
				br.append("[X]");
			} else {
				br.append("[ ]");
			}
			br.append(" ").append(str[2]).append("(from: ").append(str[3]);
			br.append(" to: ").append(str[4]).append(")");
			break;
		default:
			throw new IOException("file saved has errors");
		}
		return br.toString();
	}
}
