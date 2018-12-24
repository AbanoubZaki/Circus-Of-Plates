package eg.edu.alexu.csd.oop.game.cs01.application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HandleDragging {
	private static HandleDragging instance;
	private double x;
	private double y;

	private HandleDragging() {
		x = 0;
		y = 0;
	}

	public static HandleDragging getInstance() {
		if (instance == null) {
			instance = new HandleDragging();
		}
		return instance;
	}

	public void makeStageDragebale(Parent parent, Stage stage) {
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				x = event.getSceneX();
				y = event.getSceneY();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				stage.setX(event.getScreenX() - x);
				stage.setY(event.getScreenY() - y);
				stage.setOpacity(.7f);
			}
		});
		parent.setOnDragDone((e) -> {
			stage.setOpacity(1f);
		});
		parent.setOnMouseReleased((e) -> {
			stage.setOpacity(1f);
		});
	}
}
