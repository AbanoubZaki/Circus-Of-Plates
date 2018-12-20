package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.ModeFactory;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.SnapShot;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	static volatile World game;
	static volatile JFrame frame;

	public static void main(String[] args) {

		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		JMenuItem newMenuItem = new JMenuItem("New Zeft Game");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		options.add(newMenuItem);
		options.addSeparator();
		options.add(pauseMenuItem);
		options.add(resumeMenuItem);
		menuBar.add(options);
		game = new OurGame(GameDifficulty.hard,
				ModeFactory.getInstance(GameMode.robot, GameDifficulty.hard).createMode());
		Controller.getInstance().setGameController(GameEngine.start("Circus of plates", game, menuBar));
		frame = (JFrame) menuBar.getParent().getParent().getParent();
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				game = new OurGame(GameDifficulty.hard,
						ModeFactory.getInstance(GameMode.robot, GameDifficulty.hard).createMode());
				Controller.getInstance().setGameController(GameEngine.start("Circus of plates", game, menuBar));
				Controller.getInstance().changeWorld(game);
				frame = (JFrame) menuBar.getParent().getParent().getParent();
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().pause();
				((OurGame) game).setState(CurrentState.paused);
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().resume();
				((OurGame) game).setState(CurrentState.running);
			}
		});

		SnapShot.getSnapShot().saveGame((OurGame) game, "bebo");
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
