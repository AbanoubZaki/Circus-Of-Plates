package eg.edu.alexu.csd.oop.game.cs01.leaderboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import eg.edu.alexu.csd.oop.game.cs01.Logger4J.OurLogger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class LeaderboardManager {

	private static LeaderboardManager m;

	java.util.List<Player> players;

	private LeaderboardManager() {
		players = new ArrayList<Player>();
	}

	public static LeaderboardManager getInstance() {
		if (m == null) {
			m = new LeaderboardManager();
		}
		return m;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	@SuppressWarnings("unchecked")
	public void readPlayers() {
		OurLogger.info(this.getClass(), "loading leaderboard");
		FileReader fileReader;
		BufferedReader bufferReader;
		StringBuilder builder = new StringBuilder();
		try {
			File folder = new File("LeaderBoard");
			folder.mkdirs();
			File savedFile = new File("LeaderBoard\\board.json");
			savedFile.createNewFile();
			fileReader = new FileReader(savedFile);
			bufferReader = new BufferedReader(fileReader);
			String line = bufferReader.readLine();
			while (line != null) {
				builder.append(line);
				line = bufferReader.readLine();
			}
			bufferReader.close();
			fileReader.close();
		} catch (Exception exception) {
			exception.printStackTrace();
			OurLogger.error(getClass(), exception.getMessage());
		}
		Gson gson = new Gson();
		players = (List<Player>) gson.fromJson(builder.toString(), new TypeToken<List<Player>>() {
		}.getType());
		if (players == null) {
			players = new ArrayList<Player>();
		}
	}

	public void writePlayers() {
		OurLogger.info(this.getClass(), "loading leader board");
		Gson gson = new Gson();
		String json = gson.toJson(players);
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);

		try {
			File folder = new File("LeaderBoard");
			folder.mkdirs();
			File savedFile = new File("LeaderBoard\\board.json");
			savedFile.createNewFile();
			PrintWriter writer = new PrintWriter(savedFile);
			writer.println(gson.toJson(je));
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			OurLogger.error(getClass(), e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fillTable(TableView fillTable) {
		ObservableList<Object> data = FXCollections.observableArrayList();
		String[] cols = new String[] { "Name", "Score" };
		for (int i = 0; i < cols.length; i++) {
			// We are using non property style for making dynamic table
			final int j = i;
			TableColumn col = new TableColumn(cols[i]);
			col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
					return new SimpleStringProperty(param.getValue().get(j).toString());
				}
			});
			col.setPrefWidth(fillTable.getPrefWidth() / 2);
			col.setResizable(false);
			fillTable.getColumns().addAll(col);
		}

		for (int i = 0; i < players.size(); i++) {
			ObservableList<Object> row = FXCollections.observableArrayList();
			row.add(players.get(i).getName());
			row.add(Integer.toString(players.get(i).getScore()));
			data.add(row);
		}
		fillTable.setItems(data);

	}
}
