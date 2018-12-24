package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import eg.edu.alexu.csd.oop.game.cs01.OurWorld.OurGame;

public class SnapShot implements ISnapShot {

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
		Gson gsonT = new Gson();
		String json = gsonT.toJson(((OurGame) ourGame).getSnapShot());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		String prettyJsonString = gson.toJson(je);

		try {
			File folder = new File("SavedGames");
			folder.mkdirs();
			File file = new File("SavedGames\\" + fileName + ".json");
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file);
			writer.println(prettyJsonString);
			writer.close();
		} catch (Exception e) {
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
			File folder = new File("SavedGames");
			folder.mkdirs();
			File file = new File("SavedGames\\" + fileName + ".json");
			file.createNewFile();
			fileReader = new FileReader(file);
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
