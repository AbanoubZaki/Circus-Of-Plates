package eg.edu.alexu.csd.oop.game.cs01.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.GameStates.CurrentState;
import eg.edu.alexu.csd.oop.game.cs01.ModeFactory.ModeFactory;
import eg.edu.alexu.csd.oop.game.cs01.Music.Track;
import eg.edu.alexu.csd.oop.game.cs01.ObjectPool.FallenObjectsGenerator;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.Controller;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;
import eg.edu.alexu.csd.oop.game.cs01.SnapShot.SnapShot;

public class MenuBarManager {

	private static MenuBarManager manager;

	private JMenuBar menuBar;
	private static volatile World game;
	private static volatile JFrame frame;

	public static World getGame() {
		return game;
	}

	public static void setGame(World game) {
		MenuBarManager.game = game;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		MenuBarManager.frame = frame;
	}

	private MenuBarManager() {
		menuBar = new JMenuBar();
	}

	public static MenuBarManager getInstance() {
		if (manager == null) {
			manager = new MenuBarManager();
		}
		return manager;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JMenu setMainMenu() {
		// new game, pause, resume, exit.
		JMenu mainMenu = new JMenu("Main Menu");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem resume = new JMenuItem("Resume");
		JMenuItem exit = new JMenuItem("Exit");
		mainMenu.add(newGame);
		mainMenu.add(pause);
		mainMenu.add(resume);
		mainMenu.addSeparator();
		mainMenu.add(exit);
		menuBar.add(mainMenu);

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					FallenObjectsGenerator.getInstance().clear();
				} catch (Exception e1) {
				}
				Track.getInstance().getTrack("theme").stop();

				game = new OurGame(
						((OurGame) game).getDifficulty(), ModeFactory.getInstance(((OurGame) game).getMode(),
								((OurGame) game).getDifficulty()).createMode(),((OurGame) game).getName());

				Controller.getInstance().setGameController(
						GameEngine.start("Circus of plates", game, MenuBarManager.getInstance().getMenuBar()));
				frame = (JFrame) MenuBarManager.getInstance().getMenuBar().getParent().getParent().getParent();
			}
		});

		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().pause();
				((OurGame) game).setState(CurrentState.paused);
				Track.getInstance().getTrack("theme").pause();
			}
		});

		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().resume();
				((OurGame) game).setState(CurrentState.running);
				Track.getInstance().getTrack("theme").play();

			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Track.getInstance().getTrack("theme").stop();
				frame.dispose();
				System.exit(0);
			}
		});
		return mainMenu;
	}

	public JMenu setFileMenu() {
		// mode, difficulty, leader board.
		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save Game");
		JMenuItem load = new JMenuItem("Load Game");
		file.add(save);
		file.add(load);
		menuBar.add(file);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// add a edit text field to take file name.
				if (((OurGame) game).getState() != CurrentState.gameOver) {
					Date date = new Date();
				    String strDateFormat = "hh-mm-ss a";
				    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
				    String formattedDate= dateFormat.format(date);
				    System.out.println(formattedDate);
					SnapShot.getSnapShot().saveGame(game, ((OurGame)game).getName() + " " + formattedDate);
				}
			}
		});

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					FallenObjectsGenerator.getInstance().clear();
				} catch (Exception e1) {
				}
				Track.getInstance().getTrack("theme").stop();
				game = SnapShot.getSnapShot().loadGame(((OurGame)game).getName() + new Date().getTime());
				
				Controller.getInstance().setGameController(
						GameEngine.start("Circus of plates", game, MenuBarManager.getInstance().getMenuBar()));
				Controller.getInstance().changeWorld(game);
				frame = (JFrame) MenuBarManager.getInstance().getMenuBar().getParent().getParent().getParent();
				Track.getInstance().getTrack("theme").seek(((OurGame) game).getDuration());
				Track.getInstance().getTrack("theme").play();
			}
		});

		return file;
	}

	public void setMenuBar() {
		setMainMenu();
		setFileMenu();
	}
}
