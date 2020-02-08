package back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
        
        Iterator cardStart = databaseArray.iterator();
        Iterator cardIterator = cardStart;

        System.out.println("Provide a name to search the database:");
        Scanner s = new Scanner(System.in);
        String cardNameSearch = s.next();
        while (cardIterator.hasNext())
        {
        	JSONObject cardObj = (JSONObject) cardIterator.next();
        	String cardName = cardObj.get("name").toString();
        	if (cardName.toLowerCase().contains(cardNameSearch.toLowerCase()))
        	{
        		System.out.println(cardName);
        	}
        }
	}

}
