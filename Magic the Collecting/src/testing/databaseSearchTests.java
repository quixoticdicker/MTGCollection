package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import back.cardDatabase;

class databaseSearchTests {

	@Test
	void test()
	{
		cardDatabase searchDatabase = null;
		try
		{
			searchDatabase = new cardDatabase((JSONArray) new JSONParser().parse(utility.FileHelper.readTextFile("scryfall-oracle-cards.json")));
		}
		catch (Exception e)
		{
			fail("incorrectly parsed json database threw");
		}
		
		assertNotNull(searchDatabase);
		
		JSONArray testSearches = null;
		try
		{
			testSearches = (JSONArray) new JSONParser().parse(utility.FileHelper.readTextFile("test-search.json"));
		}
		catch (Exception e)
		{
			fail("incorrectly parsed json tests threw");
		}
		
		assertNotNull(testSearches);
		
		Iterator<?> cardSearches = testSearches.iterator();
		while (cardSearches.hasNext())
		{
			JSONObject nextSearch = (JSONObject) cardSearches.next();
			searchDatabase.search(nextSearch);
		}
	}

}
