package bomberman;

import static bomberman.constants.GlobalConstants.GRID_SIZE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Vector;

import bomberman.entity.Entity;
import bomberman.entity.factory.EntityFactory;
import bomberman.utils.Tiling;

/**
 * Configuration class for the Sandbox, used to load the different maps
 * 
 * @author tialim
 *
 */

public class MapLoader {

	private int widthTile, heightTile;
	private Collection<Entity> entities, players;
	String parentPath = "src/resources/scenes/";

	public MapLoader() {
		this(1);
	}

	public MapLoader(int gameLevel) {
		entities = new Vector<Entity>(); // vector used for multi-threading option later
		players = new Vector<Entity>();
		loadLevel(gameLevel);
	}

	Collection<Entity> getEntities() {
		return entities;
	}

	int getSceneWidth() {
		return widthTile * GRID_SIZE;
	}

	int getSceneHeight() {
		return heightTile * GRID_SIZE;
	}
	
	Collection<Entity> getPlayers() {
		return players;
	}

	void loadLevel(int level) {
		entities.clear();
		players.clear();
		loadMap("Level" + level + ".txt");
	}

	private void addEntity(char entityType, int x, int y) {
		// adds new entity to the entity list
		Entity e = EntityFactory.create(entityType, x, y);
		entities.add(e);
		if (entityType == 'P') {
			players.add(e);
		}
	}

	private void loadMap(String filePath) {
		String[] map = readFile(filePath);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length(); j++) {
				char c = map[i].charAt(j);
				if (c != ' ') {
					addEntity(map[i].charAt(j), Tiling.tileToPixel(j), Tiling.tileToPixel(i));
				}
			}
		}
	}

	private String[] readFile(String filePath) {
		String[] mapFile = null;
		try {
			InputStream is = new FileInputStream(loadFile(filePath));
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String[] meta = buf.readLine()
					.split(" ");
			widthTile = Integer.parseInt(meta[0]);
			heightTile = Integer.parseInt(meta[1]);
			Tiling.tileHeight = heightTile;
			Tiling.tileWidth = widthTile;

			mapFile = new String[heightTile];
			String line = buf.readLine();
			for (int i = 0; i < heightTile; i++) {
				mapFile[i] = line;
				line = buf.readLine();
			}
			buf.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapFile;
	}

	private File loadFile(String filePath) {
		File file = new File(parentPath + filePath);
		String path = file.getAbsolutePath();
		if (File.separatorChar == '\\') {
			// From Windows to Linux/Mac
			path = path.replace('/', File.separatorChar);
			path = path.replace("\\", "\\\\");
		} else {
			// From Linux/Mac to Windows
			path = path.replace('\\', File.separatorChar);
		}
		return new File(path);
	}
}
