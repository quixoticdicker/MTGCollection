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

	public List<JSONObject> search(List<Predicate<JSONObject>> comparators)
	{
		List<JSONObject> foundCards = (List<JSONObject>) jsonDatabase.parallelStream().collect(Collectors.toCollection(() -> new ArrayList<JSONObject>()));
		for (Predicate<JSONObject> comparator : comparators)
		{
			foundCards = (List<JSONObject>) foundCards.stream().filter(comparator).collect(Collectors.toCollection(() -> new ArrayList<JSONObject>()));
		}
		
		return foundCards;
	}
}
