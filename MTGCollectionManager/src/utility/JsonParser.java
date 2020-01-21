package utility;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JsonParser {

    static Map toObject(String jsonObj)
    {
        Map dictObj = new HashMap();
        
        StringTokenizer jsonTokenizer = new StringTokenizer(jsonObj, "[]{}:,", true);
        
        while (jsonTokenizer.hasMoreTokens())
        {
            switch (jsonTokenizer.nextToken())
            {
            case "[": // beginning of array
                break;
            case "]": // end of array
                break;
            case "{": // beginning of object
                break;
            case "}": // end of object
                break;
            case ":": // divides name and value
                break;
            case ",": // separates elements of an array or object
                break;
            default:
                break;
            }
        }
        
        return dictObj;
    }
}
