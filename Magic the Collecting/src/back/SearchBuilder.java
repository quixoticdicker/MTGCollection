package back;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchBuilder {

	/**
	 * The list of conditions that need to evaluate to true in order for
	 * a card to be included in the found set of cards.
	 */
	private List<Predicate<JSONObject>> comparators;
	public List<Predicate<JSONObject>> searchConditions()
	{
		return comparators;
	}
	
	/**
	 * Initialize a SearchBuilder given a json object. Might be useful if
	 * you want to save search parameters.
	 * @param searchParameters 
	 */
	public SearchBuilder(JSONObject searchParameters)
	{
		boolean ignoreCase = true;
		if (searchParameters.containsKey("ignore_case"))
		{
			ignoreCase = "true".equalsIgnoreCase((String)searchParameters.get("ignore_case"));
		}

		// construct search function
		comparators = new ArrayList<Predicate<JSONObject>>();
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
				AddStringSearch(paramString, searchString, ("name".equalsIgnoreCase(paramString) || "oracle_text".equalsIgnoreCase(paramString)) && ignoreCase);
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
	}

	/**
	 * Initialize an empty SearchBuilder
	 */
	public SearchBuilder()
	{
		comparators = new ArrayList<Predicate<JSONObject>>();
	}
	
	/**
	 * @param searchString is the string that you're looking for within the
	 * 		name field of the card.
	 * @return The same SearchBuilder that you're calling this on with the
	 * 		added condition that the name contains the given searchString.
	 */
	public SearchBuilder NameContains(String searchString)
	{
		return AddStringSearch("name", searchString, true);
	}
	
	/**
	 * @param searchString is the string that you're looking for within the
	 * 		oracle_text field of the card.
	 * @return The same SearchBuilder that you're calling this on with the
	 * 		added condition that the rules contain the given searchString.
	 */
	public SearchBuilder RulesContain(String searchString)
	{
		return AddStringSearch("oracle_text", searchString, true);
	}
	
	/**
	 * @param paramString is the parameter you're searching within.
	 * @param searchString is the string that you're searching for
	 * @param ignoreCase whether to cast the strings' contents to lower-case
	 * 		before searching
	 * @return The same SearchBuilder that you're calling this on with the
	 * 		added condition that the parameter contains the given searchString.
	 */
	public SearchBuilder AddStringSearch(String paramString, String searchString, boolean ignoreCase)
	{
		if (ignoreCase)
		{
			comparators.add(card -> ((JSONObject)card).get(paramString).toString().toLowerCase().contains(searchString.toLowerCase()));
		}
		else
		{
			comparators.add(card -> ((JSONObject)card).get(paramString).toString().contains(searchString));
		}
		return this;
	}
}
