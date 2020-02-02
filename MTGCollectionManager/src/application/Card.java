package application;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Card
{
    enum eColor
    {
        BLACK(0),
        BLUE(1),
        GREEN(2),
        RED(3),
        WHITE(4),
        length(5);

        public final int value;

        eColor(final int i) {
            value = i;
        }
    }
    
    enum eFormat
    {
        STANDARD(0),
        FUTURE(1),
        HISTORIC(2),
        PIONEER(3),
        MODERN(4),
        LEGACY(5),
        PAUPER(6),
        VINTAGE(7),
        PENNY(8),
        COMMANDER(9),
        BRAWL(10),
        DUEL(11),
        OLDSCHOOL(12),
        length(13);
        
        public final int value;
        
        eFormat(final int i) {
            value = i;
        }
    }
    
    enum eLegality
    {
        LEGAL,
        RESTRICTED,
        BANNED
    }
    
    String name;
    String imgThumbnailURL;
    boolean[] color;
    boolean[] color_identity;
    eLegality[] legalities;

    public Card(String cardJSONObj)
    {
        Integer lastPass = 0;

        // populate name
        name = getArgument("\"name\":\"", cardJSONObj, lastPass);

        // populate image url
        imgThumbnailURL = getArgument("\"image_uris\":{\"small\":\"", cardJSONObj, lastPass);

        // populate card color
        color = new boolean[eColor.length.value];
        String[] colors = getStringArray("\"colors\":", cardJSONObj, lastPass);
        for (String clr : colors)
        {
            switch (clr)
            {
            case "B":
                color[eColor.BLACK.value] = true; 
                break;
            case "U":
                color[eColor.BLUE.value] = true; 
                break;
            case "G":
                color[eColor.GREEN.value] = true; 
                break;
            case "R":
                color[eColor.RED.value] = true;
                break;
            case "W":
                color[eColor.WHITE.value] = true;
                break;
            default:
                System.err.println("Unknown color: " + clr);
            }
        }

        // populate card color identity
        color_identity = new boolean[eColor.length.value];
        String[] color_identities = getStringArray("\"color_identity\":", cardJSONObj, lastPass);
        for (String clr : color_identities)
        {
            switch (clr)
            {
            case "B":
                color_identity[eColor.BLACK.value] = true; 
                break;
            case "U":
                color_identity[eColor.BLUE.value] = true; 
                break;
            case "G":
                color_identity[eColor.GREEN.value] = true; 
                break;
            case "R":
                color_identity[eColor.RED.value] = true;
                break;
            case "W":
                color_identity[eColor.WHITE.value] = true;
                break;
            default:
                System.err.println("Unknown color: " + clr);
            }
        }
        
        // populate legality by format
        Map<String, String> legalityMap = getNestedObj("\"legalities\":", cardJSONObj, lastPass);
        legalityMap.forEach((formatString, legalityString) ->
        {
            legalities[eFormat.valueOf(formatString.toUpperCase()).value] = eLegality.valueOf(legalityString.toUpperCase());
        });
    }
    
    private String getArgument(String preceeding, String json, Integer start)
    {
        int argStart = json.indexOf(preceeding, start) + preceeding.length();
        int argEnd   = json.indexOf("\"", argStart + 1);
        start = argEnd;
        return json.substring(argStart, argEnd);
    }
    
    private String[] getStringArray(String preceeding, String json, Integer start)
    {
        int argStart = json.indexOf(preceeding + "[", start) + preceeding.length() + 1;
        int argEnd   = json.indexOf("]", argStart);
        start = argEnd;
        StringTokenizer stringTkn = new StringTokenizer(json.substring(argStart, argEnd), "\",");
        String[] stringArray = new String[stringTkn.countTokens()];
        int i = 0;
        while (stringTkn.hasMoreTokens())
        {
            String nextColorToken = stringTkn.nextToken();
            System.out.println(nextColorToken);
            stringArray[i++] = nextColorToken;
        }
        return stringArray;
    }
    
    private Map<String, String> getNestedObj(String preceeding, String json, Integer start)
    {
        Map nestedObj = new HashMap<String, String>();
        
        int argStart = json.indexOf(preceeding, start) + preceeding.length();
        int argEnd = json.indexOf("}", argStart);
        start = argEnd;
        StringTokenizer stringTkn = new StringTokenizer(json.substring(argStart, argEnd), "{}:,\"");
        while (stringTkn.hasMoreTokens())
        {
            nestedObj.put(stringTkn.nextToken(), stringTkn.nextToken());
        }
        return nestedObj;
    }
}
