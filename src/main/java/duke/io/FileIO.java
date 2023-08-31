package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	private String filepath;
	private String filename;
	private String description;

	public FileIO(String filepath, String filename, String description) {
		this.filepath = filepath;
		this.filename = filename;
		this.description = description;
	}

	public String read() throws FileNotFoundException {
		File f = new File(filepath + filename);
		Scanner in = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while (in.hasNext()) {
			sb.append(in.nextLine().trim());
			sb.append('\n');
		}
		return sb.toString().trim();
	}

	public void write(String s) throws IOException {
		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}

		f = new File(filepath + filename);
		if (!f.isFile()) {
			f.createNewFile();
		}

		FileWriter out = new FileWriter(f);
		out.write(s);
		out.close();
	}

	public String getFilename() {
		return String.format("%s%s", filepath, filename);
	}
}
