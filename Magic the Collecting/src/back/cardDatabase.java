package back;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.simple.*;

public class cardDatabase {
	private JSONArray jsonDatabase;

	public cardDatabase(JSONArray jsonDatabase)
	{
		this.jsonDatabase = jsonDatabase;
	}

	public List<JSONObject> search(JSONObject searchParameters)
	{
		boolean ignoreCase = true;
		if (searchParameters.containsKey("ignore_case"))
		{
			ignoreCase = "true".equalsIgnoreCase((String)searchParameters.get("ignore_case"));
		}

		// construct search function
		List<Predicate<JSONObject>> comparators = new ArrayList<Predicate<JSONObject>>();
		for (Object param : searchParameters.keySet())
		{
			String paramString = (String)param;
			
			if ("ignore_case".equalsIgnoreCase(paramString))
			{
				continue;
			}
			
			Object searchValue = searchParameters.get(param);
			if (searchValue instanceof Integer)
			{
				// we want integers to match exactly
				int searchInt = (int)searchValue;
				comparators.add(card -> searchInt == (int)card.get(paramString));
			}
			else if (searchValue instanceof String)
			{
				// we want strings to be contained in the main string
				String searchString = (String)searchValue;
				if (("name".equalsIgnoreCase(paramString) || "oracle_text".equalsIgnoreCase(paramString)) && ignoreCase)
				{
					comparators.add(card -> searchString.toLowerCase() == ((String)((JSONObject)card).get(paramString)).toLowerCase());
				}
				else
				{
					comparators.add(card -> searchString == (String)((JSONObject)card).get(paramString));
				}
			}
			else if (searchValue instanceof JSONArray)
			{
				System.err.println("Arrays not supported yet");
			}
			else
			{
				System.err.println("Couldn't resolve type of parameter: " + paramString);
			}
		}

		List<JSONObject> foundCards = (List<JSONObject>) jsonDatabase.parallelStream().collect(Collectors.toCollection(() -> new ArrayList<JSONObject>()));
		for (Predicate<JSONObject> comparator : comparators)
		{
			foundCards = (List<JSONObject>) foundCards.stream().filter(comparator).collect(Collectors.toCollection(() -> new ArrayList<JSONObject>()));
		}
		
		return foundCards;
	}
}
