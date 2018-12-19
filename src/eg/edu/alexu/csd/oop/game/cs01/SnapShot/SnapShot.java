package eg.edu.alexu.csd.oop.game.cs01.SnapShot;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import eg.edu.alexu.csd.oop.game.World;

public class SnapShot implements SnapShotInterface{

	@Override
	public void saveGame(World ourGame, String fileName) {
		
		try {
			FileOutputStream fileStream = new FileOutputStream(new File("D:\\College\\CSED02\\OOP\\circusofplates\\" + fileName + ".xml"));
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
