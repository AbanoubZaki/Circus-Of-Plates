package eg.edu.alexu.csd.oop.game.cs01.Music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Track {

	private Map<String, Media> mediaPlayers;
	private static Track instance;
	private MediaPlayer theme;

	private Track() {
		mediaPlayers = new HashMap<String, Media>();
		theme = null;
	}

	public static Track getInstance() {
		if (instance == null) {
			instance = new Track();
		}
		return instance;
	}

	public void addTrack(File trackFile) {
		try {
			Media media = new Media(trackFile.toURI().toString());
			if (trackFile.getName().substring(0, trackFile.getName().lastIndexOf(".")).equals("theme")) {
				theme = new MediaPlayer(media);
				theme.setOnEndOfMedia(new Runnable() {

					@Override
					public void run() {
						theme.seek(Duration.ZERO);
					}
				});
			} else {
				mediaPlayers.put(trackFile.getName().substring(0, trackFile.getName().lastIndexOf(".")), media);
			}
		} catch (Exception e) {
			OurLogger.error(getClass(), e.getMessage());
		}
	}

	public MediaPlayer getTrack(String name) {
		try {
			if (name.equals("theme")) {
				return theme;
			}
			return new MediaPlayer(mediaPlayers.get(name));
		} catch (Exception e) {
			OurLogger.error(getClass(), e.getMessage());
		}
		return null;
	}

	public void clear() {
		mediaPlayers.clear();
		theme = null;
	}

}
