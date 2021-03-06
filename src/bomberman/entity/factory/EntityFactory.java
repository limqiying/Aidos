package bomberman.entity.factory;

import bomberman.entity.Entity;
import bomberman.entity.bomb.BlackBomb;
import bomberman.entity.enemy.Ballom;
import bomberman.entity.enemy.Bear;
import bomberman.entity.enemy.Doria;
import bomberman.entity.player.Player;
import bomberman.entity.tile.BrickWall;
import bomberman.entity.tile.Wall;
import bomberman.entity.tile.HiddenDoor;


public class EntityFactory {
	
	public static Entity create(char entityType, int x, int y) {
		Entity entity;
		switch (entityType) {
		case 'B':
			entity = new Ballom(x, y);
			break;
		case 'D':
			entity = new Doria(x, y);
			break;
		case 'E':
			entity = new Bear(x, y);
			break;
		case 'W':
			entity = new Wall(x, y);
			break;
		case 'P': 
			entity = new Player(x, y);
			break;
		case '#':
			entity = new BrickWall(x, y);
			break;
		case 'O':
			entity = new BlackBomb(x, y);
			break;
		case 'X':
			entity = new HiddenDoor(x, y);
			break;
		default:
			entity = new Ballom(x, y);
			break;
		}
		return entity;
	}

}

