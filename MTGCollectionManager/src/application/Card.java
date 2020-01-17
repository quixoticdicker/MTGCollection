package application;

import java.util.regex.*;

public class Card
{
    enum color
    {
        BLACK,
        BLUE,
        GREEN,
        RED,
        WHITE
    }
    
    enum formats
    {
        STANDARD,
        FUTURE,
        HISTORIC,
        PIONEER,
        MODERN,
        LEGACY,
        PAUPER,
        VINTAGE,
        PENNY,
        COMMANDER,
        BRAWL,
        DUEL,
        OLDSCHOOL
    }
    
    String name;
    String imgThumbnailURL;
    boolean[] color_identity;
    boolean[] color;
    boolean[] legalities;
    
    //private static String regexParsePattern = ".*\"name\":\"(.*?)\".+\"image_urls\":\\{\"small\":\"(.*?)\"";
    
    public Card(String cardJSONObj)
    {
        Integer lastPass = 0;
        
        name = getArgument("\"name\":\"", cardJSONObj, lastPass);
        
        imgThumbnailURL = getArgument("\"image_uris\":{\"small\":\"", cardJSONObj, lastPass);
        /* Regex approach is too processor intensive
        Pattern parsePattern = Pattern.compile(regexParsePattern);
        Matcher cardMatcher = parsePattern.matcher(cardJSONObj);
        while (cardMatcher.find())
        {
            name = cardMatcher.group(1);
            imgThumbnailURL = cardMatcher.group(2);
        }
        */
    }
    
    private String getArgument(String preceeding, String json, Integer start)
    {
        int argStart = json.indexOf(preceeding, start) + preceeding.length();
        int argEnd   = json.indexOf("\"", argStart + 1);
        start = argEnd;
        return json.substring(argStart, argEnd);
    }
}
