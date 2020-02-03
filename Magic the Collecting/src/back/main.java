package back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
                StringBuilder lines = new StringBuilder();
                int i = 0;
                while ((line = reader.readLine()) != null)
                {
	                lines.append(line + '\n');
                }
                reader.close();
                oracleDatabaseJSON = lines.toString();
            }
            catch (Exception e)
            {
                System.err.println("Couldn't read cached oracle text.");
                e.printStackTrace();
            }
        }

        System.out.println("Finished extracting database");

        // deserializing json
        JSONArray databaseArray;
        try {
			Object databaseObj = new JSONParser().parse(oracleDatabaseJSON);
			databaseArray = (JSONArray) databaseObj;
		} catch (ParseException e) {
			System.err.println("Error deserializing json");
			e.printStackTrace();
			return;
		}
        
        Iterator cardIterator = databaseArray.iterator();
        while (cardIterator.hasNext())
        {
        	JSONObject cardObj = (JSONObject) cardIterator.next();
        	System.out.println(cardObj.get("name"));
        }
        System.out.println("extracted cards");
	}

}
