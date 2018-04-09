package bomberman.entity.killableentity;

import bomberman.Sandbox;
import bomberman.animations.KillableEntityAnimations;
import bomberman.animations.sprites.SpriteSpecification;
import bomberman.constants.EntityDimensions;
import bomberman.entity.Entity;
import bomberman.entity.boundedbox.CollidableType;
import bomberman.utils.Tiling;

public class BrickWall extends KillableEntity {

	public BrickWall(int x, int y) {
		super(Tiling.snapTile(x), Tiling.snapTile(y));
	}

	public int setHealth() {
		return 0;
	}

	public void update(Sandbox sb) {}
	
	public boolean isPersistant() {
		return !dead;
	}

	protected void setAnimations(Entity e) {
		animations = new KillableEntityAnimations(e, SpriteSpecification.BRICKWALL, SpriteSpecification.BRICKWALL);
	}

	protected String setName() {
		return "Brick Wall " + ID;
	}

	protected EntityDimensions setED() {
		return EntityDimensions.WALLD;
	}

	protected void setCollidableType() {
		collidableType = CollidableType.EXPLODABLE;
	}

}