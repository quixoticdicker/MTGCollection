package back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// fetch latest json database
        boolean checkForOracleUpdates = false;
        String oracleDatabaseJSON = "";
        if (checkForOracleUpdates)
        {
            oracleDatabaseJSON = utility.URLHelper.getURLSource("https://archive.scryfall.com/json/scryfall-oracle-cards.json");
        }
        else
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader("scryfall-oracle-cards.json"));
                String line;
                ArrayList<String> lines = new ArrayList<String>();
                int i = 0;
                while ((line = reader.readLine()) != null)
                {
                    lines.add(line);
                   if ((++i) % 100 == 0)
                   {
                       System.out.println("Read line: " + i);
                   }
                }
                reader.close();
            }
            catch (Exception e)
            {
                System.out.println("Couldn't read cached oracle text.");
                e.printStackTrace();
            }
        }
	}

}
