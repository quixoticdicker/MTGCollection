package utility;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileHelper {

	public static String readTextFile(String path)
	{
		String fileContents = "";
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			StringBuilder lines = new StringBuilder();
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				lines.append(line + '\n');
			}
			reader.close();
			fileContents = lines.toString();
		}
		catch (Exception e)
		{
			System.err.println("Couldn't read file at: " + path);
			e.printStackTrace();
		}
		return fileContents;
	}
	
}
