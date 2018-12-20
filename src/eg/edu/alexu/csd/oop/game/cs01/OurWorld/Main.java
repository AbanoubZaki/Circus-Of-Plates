package eg.edu.alexu.csd.oop.game.cs01.OurWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.Difficulty.GameDifficulty;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.GameMode;
import eg.edu.alexu.csd.oop.game.cs01.objects.cs01.ModeFactory.ModeFactory;

public class Main {
	
	static volatile World game;
	
	public static void main(String[] args) {

		JMenuBar menuBar = new JMenuBar();
		;
		JMenu options = new JMenu("Options");
		JMenuItem newMenuItem = new JMenuItem("New Zeft Game");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		options.add(newMenuItem);
		options.addSeparator();
		options.add(pauseMenuItem);
		options.add(resumeMenuItem);
		menuBar.add(options);

		game = new OurGame(GameDifficulty.hard, ModeFactory.getInstance(GameMode.christmass, GameDifficulty.hard).createMode());
		Controller.getInstance().setGameController(GameEngine.start("Circus of plates", game, menuBar));

		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new OurGame(GameDifficulty.easy, ModeFactory.getInstance(GameMode.christmass, GameDifficulty.easy).createMode());
				Controller.getInstance().setGameController(GameEngine.start("Circus of plates", game, menuBar));
				Controller.getInstance().changeWorld(game);
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

		// SnapShot.getSnapShot().saveGame(game, "bebo");
	}

}
