package application;
    
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Main extends Application
{
    @Override
    public void start(Stage mainWindow)
    {
        try
        {
            Button searchBtn = new Button("Search");
            searchBtn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    System.out.println("searching...");
                }
            });

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));
            
            int row = 0;
            grid.add(searchBtn, 0, row++);
            
            grid.add(new TextField(), 0, row++);
            
            grid.add(new Label("Color Identity"), 0, row++);
            grid.add(new CheckBox("Black"), 0, row++);
            grid.add(new CheckBox("Blue") , 0, row++);
            grid.add(new CheckBox("Green"), 0, row++);
            grid.add(new CheckBox("Red")  , 0, row++);
            grid.add(new CheckBox("White"), 0, row++);
            
            Canvas searchResults = new Canvas(200, 500);
            fillBackground(searchResults.getGraphicsContext2D(), Color.DARKGRAY);
            grid.add(searchResults, 1, 0, 1, row + 1);
            
            Canvas collectionPane = new Canvas(200, 500);
            fillBackground(collectionPane.getGraphicsContext2D(), Color.DARKSLATEGRAY);
            grid.add(collectionPane, 2, 0, 1, row + 1);

            Scene scene = new Scene(grid, 800, 500);
            mainWindow.setTitle("Collection Manager");
            mainWindow.setScene(scene);
            mainWindow.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void fillBackground(GraphicsContext gc, Color c)
    {
        gc.setFill(c);
        gc.fillRect(0, 0, 100, 100);
    }
    
    public static void main(String[] args)
    {
        // fetch latest json database
        boolean checkForOracleUpdates = false;
        String oracleDatabaseJSON = "";
        if (checkForOracleUpdates)
        {
            oracleDatabaseJSON = utility.URLHelper.getURLSource("https://archive.scryfall.com/json/scryfall-oracle-cards.json");
        }
        else
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader("scryfall-oracle-cards.json"));
                String line;
                ArrayList<String> lines = new ArrayList<String>();
                int i = 0;
                while ((line = reader.readLine()) != null)
                {
                    lines.add(line);
                   if ((++i) % 100 == 0)
                   {
                       System.out.println("Read line: " + i);
                   }
                }
                reader.close();
            }
            catch (Exception e)
            {
                System.out.println("Couldn't read cached oracle text.");
                e.printStackTrace();
            }
        }
        
        launch(args);
    }
}
