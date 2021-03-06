package bomberman.entityconfigurations.boundedbox;

/**
 * Different entities hold different collidable types.
 * If IMPERMEABLE, the entity's position cannot overlap with any other entity's position, including explosions. Used for standard walls.
 * If EXPLODABLE, the entity's position cannot overlap with any entity other than an explosion. Used for brick walls.
 * If PENETRABLE, then entity's position can overlap with any other entity's position.
 * @author tialim
 *
 */

public enum CollidableType {
	
	IMPERMEABLE(false, false), EXPLODABLE(false, true), PENETRABLE(true, true);
	
	private boolean isPlayerCollidable, isExplodable;
	
	CollidableType(boolean isPlayerCollidable, boolean isExplodable) {
		this.isPlayerCollidable = isPlayerCollidable;	// true if players can pass through object
		this.isExplodable = isExplodable;	// true if explosions can pass through objects
	}
	
	public boolean isPlayerCollidable() {
		return isPlayerCollidable;
	}
	
	public boolean isExplodable() {
		return isExplodable;
	}
	
	public boolean isImpermeable() {
		// true if both players and explosions cannot pass through objects
		return !isPlayerCollidable && !isExplodable;
	}

}
