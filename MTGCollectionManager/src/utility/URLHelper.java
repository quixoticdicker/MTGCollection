package utility;

import java.net.*;
import java.io.*;

public class URLHelper {
    public static String getURLSource(String urlString)
    {
        String webpageSourceText = "";
        try
        {
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()));
            
            String pageLine;
            while ((pageLine = in.readLine()) != null)
            {            
                webpageSourceText += pageLine;
            }
            in.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return webpageSourceText;
    }
}
