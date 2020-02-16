package front;

import back.cardDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args)
	{
		world = World.instance();
		launch(args);
	}
	
	private static World world;

	private cardDatabase database;
	
	
	@Override
	public void start(Stage primaryStage) {
		try
		{
			BorderPane window = new BorderPane();
			ToolBar toolBar = new ToolBar(
					new Button("New Deck"),
					new Button("Open Deck"),
					new Separator(),
					new Button("Search"),
					new Separator(),
					new Button("Update Database")
					);
			HBox statusBar = new HBox();
			window.setLeft(new SearchPane(world));
			window.setTop(toolBar);
			window.setBottom(statusBar);
			Scene scene = new Scene(window, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
