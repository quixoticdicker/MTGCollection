package front;

import java.util.List;
import java.util.function.Predicate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import back.SearchBuilder;
import back.cardDatabase;
import javafx.scene.layout.GridPane;

/**
 * This singleton class is the main runner for the application and
 * intermediary for the front and back ends.
 * @author cdicker
 */
public class World {
	
	static World world;
	
	cardDatabase database;
	
	SearchPane searchParams;
	SearchResultsPane searchResults;
	
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
	
	public GridPane updateWindow()
	{
		GridPane window = new GridPane();

		searchParams = new SearchPane(world);
		window.add(searchParams, 0, 1);
		
		searchResults = new SearchResultsPane(world);
		window.add(searchResults, 1, 1);
		
		return window;
	}
	
	public void search(List<Predicate<JSONObject>> comparators)
	{
		List<JSONObject> results = database.search(comparators);
		System.out.println("found " + results.size() + " cards.");
		searchResults.updateResults(results);
	}
}
