package front;

import java.util.List;

import org.json.simple.JSONObject;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SearchResultsPane extends ScrollPane {

	VBox cardResultsList;
	
	public SearchResultsPane(World parent)
	{
		cardResultsList = new VBox();
	}
	
	public void updateResults(List<JSONObject> cardsFound)
	{
		cardResultsList.getChildren().clear();
		for (JSONObject jsonCard : cardsFound)
		{
			cardResultsList.getChildren().add(new CardListPane(jsonCard));
		}
		
		this.setContent(cardResultsList);
		this.setFitToHeight(true);
		this.setFitToWidth(false);
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}
}
