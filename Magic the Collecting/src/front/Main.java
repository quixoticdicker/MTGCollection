package front;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args)
	{
		world = World.instance();
		launch(args);
	}
	
	private static World world;

	@Override
	public void start(Stage primaryStage) {
		try
		{
			Scene scene = new Scene(world.updateWindow(), 600, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
