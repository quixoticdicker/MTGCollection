package front;

import java.util.List;
import java.util.function.Predicate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import back.cardDatabase;

/**
 * This singleton class is the main runner for the application and
 * intermediary for the front and back ends.
 * @author cdicker
 */
public class World {
	
	static World world;
	cardDatabase database;
	
	private World()
	{
		// should only ever have one instance of world
		assert(null == world);

		// fetch latest json database
        boolean checkForOracleUpdates = false;
        String oracleDatabaseJSON = "";
        if (checkForOracleUpdates)
        {
            oracleDatabaseJSON = utility.URLHelper.getURLSource("https://archive.scryfall.com/json/scryfall-oracle-cards.json");
        }
        else
        {
        	oracleDatabaseJSON = utility.FileHelper.readTextFile("scryfall-oracle-cards.json");
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

        database = new cardDatabase(databaseArray);
	}
	
	public static World instance()
	{
		if (null != world)
		{
			return world;
		}
		else
		{
			return world = new World();
		}
	}
	
	public void search(List<Predicate<JSONObject>> comparators)
	{
		//searchResultsPane.update(database.search(comparators));
	}
}
