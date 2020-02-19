package front;

import org.json.simple.JSONObject;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CardListPane extends GridPane {

	public CardListPane(JSONObject cardData)
	{
		Label cardName = new Label((String) cardData.get("name"));
		Label cmc = new Label("" + cardData.get("cmc"));
		Label rules = new Label((String) cardData.get("oracle_text"));
		
		add(cardName, 0, 0);
		add(cmc, 1, 0);
		add(rules, 0, 1, 2, 1);
	}
}
