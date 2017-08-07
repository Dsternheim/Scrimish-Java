
// David Sternheim
// 110245274
// CSE 114
// Scrimish GUI

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ScrimishGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private VBox v = new VBox();
	private HBox h = new HBox();
	private TextArea t = new TextArea();
	private TextArea t1 = new TextArea();
	private TextField userInput = new TextField("");
	private TextField aiInput = new TextField("");
	private Button battleButton = new Button("Battle");
	private Button btRemove = new Button("Remove Card");
	private Button btNextTurn = new Button("Next Turn");

	@Override
	public void start(Stage primaryStage) throws Exception {

		t.setText(Scrimish.stateToString());
		v.getChildren().add(t);

		h.getChildren().add(new Label("User Pile"));
		h.getChildren().add(userInput);
		h.getChildren().add(new Label("AI Pile"));
		h.getChildren().add(aiInput);
		h.getChildren().add(battleButton);
		h.getChildren().add(btRemove);

		battleButton.setOnAction(e -> userBattleSequence());
		btRemove.setOnAction(e -> removeCard());

		v.getChildren().add(h);

		Scene scene = new Scene(v);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void userBattleSequence() {
		try {
			Scrimish.userIndex = Integer.parseInt(userInput.getText()) - 1;
			Scrimish.aiIndex = Integer.parseInt(aiInput.getText()) - 1;
			if (Integer.toString(Integer.parseInt(userInput.getText())-1).equals("S")){
				//Scrimish.userTurn = false;	
			}	
			else {
				Scrimish.userTurn = false;
				Scrimish.battle();
				v.getChildren().remove(t1);
				t.setText(Scrimish.stateToString());
				h.getChildren().remove(battleButton);
				h.getChildren().remove(btRemove);
				h.getChildren().add(btNextTurn);
				btNextTurn.setOnAction(e -> nextTurn());
			}
		} catch (IndexOutOfBoundsException e) {
			t.setText(Scrimish.stateToString());
			t1.setText("Invalid Pile Selection. Select a new pile.");
			v.getChildren().add(t1);
		}

	}

	private void removeCard() {
		try {
			Scrimish.userIndex = Integer.parseInt(userInput.getText());
			Scrimish.removeCard();
			v.getChildren().remove(t1);
			t.setText(Scrimish.stateToString());
			h.getChildren().remove(btRemove);
			h.getChildren().remove(battleButton);
			h.getChildren().add(btNextTurn);
			btNextTurn.setOnAction(e -> nextTurn());
		} catch (IndexOutOfBoundsException e) {
			t.setText(Scrimish.stateToString());
			t1.setText("Invalid Pile Selection. Select a new pile.");
			v.getChildren().add(t1);
		}
	}

	private void nextTurn() {
		Scrimish.userTurn = true;
		Scrimish.doComputerTurn();
		Scrimish.battle();
		t.setText(Scrimish.stateToString());

		h.getChildren().remove(btNextTurn);
		h.getChildren().add(battleButton);
		h.getChildren().add(btRemove);
	}
}
