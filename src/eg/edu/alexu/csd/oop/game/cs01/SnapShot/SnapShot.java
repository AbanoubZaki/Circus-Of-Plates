package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;

public class SnapShot implements SnapShotInterface {

	private static SnapShot snapShot;

	private SnapShot() {
	}

	public static SnapShot getSnapShot() {
		if (snapShot == null) {
			snapShot = new SnapShot();
		}
		return snapShot;
	}

	@Override
	public void saveGame(World ourGame, String fileName) {
		OurLogger.info(this.getClass(), fileName + "'s game has been saved");
		File folder = new File("SavedGames");
		System.out.println(folder.mkdirs());
		File savedFile = new File("SavedGames\\" + fileName + ".json");
		Gson gsonT = new Gson();
		String json = gsonT.toJson(((OurGame) ourGame).getSnapShot());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		String prettyJsonString = gson.toJson(je);

		try {
			PrintWriter writer = new PrintWriter(savedFile);
			writer.println(prettyJsonString);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public World loadGame(String fileName) {
		OurLogger.info(this.getClass(), fileName + "'s game has been loaded");
		FileReader fileReader;
		BufferedReader bufferReader;
		StringBuilder builder = new StringBuilder();
		try {
			fileReader = new FileReader(new File("SavedGames\\" + fileName + ".json"));
			bufferReader = new BufferedReader(fileReader);
			String line = bufferReader.readLine();
			while (line != null) {
				builder.append(line);
				line = bufferReader.readLine();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		Gson gson = new Gson();
		OurGame loadedGame = new OurGame();
		loadedGame.loadGame(gson.fromJson(builder.toString(), GameSnapShot.class));
		return loadedGame;
	}

}
