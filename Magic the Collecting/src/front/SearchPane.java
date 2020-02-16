package front;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import back.SearchBuilder;

public class SearchPane extends ScrollPane {

	private class ColorCheckBox extends CheckBox
	{
		public ColorCheckBox(String colorName)
		{
			super(colorName);
			setAllowIndeterminate(true);
			setIndeterminate(true);
		}
	}
	
	public SearchPane(World parent)
	{
		GridPane parametersGrid = new GridPane();
		
		parametersGrid.setHgap(3);
		parametersGrid.setVgap(3);
		
		Button searchBtn = new Button("Search");
		
		Label nameLabel = new Label("Name");
		TextField nameField = new TextField();
		
		Label rulesLabel = new Label("Rules Text");
		TextField rulesField = new TextField();
		
		Label colorLabel = new Label("Color");
		CheckBox blackColorBox = new ColorCheckBox("Black");
		CheckBox blueColorBox  = new ColorCheckBox("Blue");
		CheckBox greenColorBox = new ColorCheckBox("Green");
		CheckBox redColorBox   = new ColorCheckBox("Red");
		CheckBox whiteColorBox = new ColorCheckBox("White");
		
		Label colorIdentityLabel = new Label("Color Identity");
		CheckBox blackCIBox = new ColorCheckBox("Black");
		CheckBox blueCIBox  = new ColorCheckBox("Blue");
		CheckBox greenCIBox = new ColorCheckBox("Green");
		CheckBox redCIBox   = new ColorCheckBox("Red");
		CheckBox whiteCIBox = new ColorCheckBox("White");
		
		{
			int row = 0;
			parametersGrid.add(searchBtn, 0, row++, 2, 1);
			
			parametersGrid.add(nameLabel, 0, row);
			parametersGrid.add(nameField, 1, row++);
			
			parametersGrid.add(rulesLabel, 0, row);
			parametersGrid.add(rulesField, 1, row++);
			
			parametersGrid.add(colorLabel,    0, row++, 2, 1);
			parametersGrid.add(blackColorBox, 0, row++, 2, 1);
			parametersGrid.add(blueColorBox,  0, row++, 2, 1);
			parametersGrid.add(greenColorBox, 0, row++, 2, 1);
			parametersGrid.add(redColorBox,   0, row++, 2, 1);
			parametersGrid.add(whiteColorBox, 0, row++, 2, 1);
			
			parametersGrid.add(colorIdentityLabel, 0, row++, 2, 1);
			parametersGrid.add(blackCIBox,         0, row++, 2, 1);
			parametersGrid.add(blueCIBox,          0, row++, 2, 1);
			parametersGrid.add(greenCIBox,         0, row++, 2, 1);
			parametersGrid.add(redCIBox,           0, row++, 2, 1);
			parametersGrid.add(whiteCIBox,         0, row++, 2, 1);
			
			this.setContent(parametersGrid);
			this.setFitToHeight(true);
			this.setFitToWidth(false);
			this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		}
		
		searchBtn.setOnAction(actionEvent ->
		{
			SearchBuilder sb = new SearchBuilder();
			if (!nameField.getText().isEmpty())
			{
				sb.NameContains(nameField.getText());
			}
			
			if (!rulesField.getText().isEmpty())
			{
				sb.RulesContain(rulesField.getText());
			}
			
			parent.search(sb.searchConditions());
		});
		
	}
	
}
