package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import eg.edu.alexu.csd.oop.game.World;

public class SnapShot implements SnapShotInterface{

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
		
		try {
			File folder = new File("SavedGames");
			System.out.println(folder.mkdirs());
			File savedFile = new File("SavedGames\\" + fileName + ".xml");
			System.out.println(savedFile.createNewFile());
			System.out.println(savedFile.exists());
			FileOutputStream fileStream = new FileOutputStream(savedFile);
			XMLEncoder encoder = new XMLEncoder(fileStream);
			
			encoder.writeObject(ourGame);
			encoder.close();
			fileStream.close();
		} catch (Exception e) {
		}
	}

	@Override
	public World loadGame(String path) {
		try {
			FileInputStream fileStream = new FileInputStream(new File(path));
			XMLDecoder decoder = new XMLDecoder(fileStream);
			World loadWorld = (World) decoder.readObject();
			decoder.close();
			fileStream.close();
			return loadWorld;
		} catch (Exception e) {
			//chain of responsibility.
			return null;
		}
	}
	
}
