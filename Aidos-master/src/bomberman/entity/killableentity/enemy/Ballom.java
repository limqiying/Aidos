package bomberman.entity.killableentity.enemy;

import bomberman.animations.KillableEntityAnimations;
import bomberman.animations.sprites.SpriteSpecification;
import bomberman.constants.EntityDimensions;
import bomberman.entity.Entity;
import bomberman.entity.boundedbox.CollidableType;
import bomberman.entity.movements.RandomMovements;

public class Ballom extends Enemy {

	public Ballom(int x, int y) {
		super(x, y);
	}

	public int setHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void setMovementStrategy() {
		movementStrategy = new RandomMovements(20);
//		movementStrategy = new Stationary();
	}

	protected void setSteps() {
		steps = 2;
	}

	protected void setAnimations(Entity e) {
		animations = new KillableEntityAnimations(e, SpriteSpecification.BALLOOM, SpriteSpecification.BALLOOMDIE);
	}

	protected String setName() {
		return "Balloon";
	}

	protected void setCollidableType() {
		collidableType = CollidableType.PENETRABLE;
	}

	@Override
	protected EntityDimensions setED() {
		return EntityDimensions.BALLOMD;
	}

}
