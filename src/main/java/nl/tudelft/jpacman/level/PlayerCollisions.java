package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;

/**
 * A simple implementation of a collision map for the JPacman player.
 * <p>
 * It uses a number of instanceof checks to implement the multiple dispatch for the 
 * collisionmap. For more realistic collision maps, this approach will not scale,
 * and the recommended approach is to use a {@link CollisionInteractionMap}.
 *
 * @author Arie van Deursen, 2014
 *
 */

public class PlayerCollisions implements CollisionMap {

    @Override
    public void collide(Unit mover, Unit collidedOn) {
        mover.collide(collidedOn, this);
    }

   
    public void playerColliding(Player player, Unit collidedOn) {
        if (collidedOn instanceof Ghost) {
            playerVersusGhost(player, (Ghost) collidedOn);
        }
        if (collidedOn instanceof Pellet) {
            ((Pellet) collidedOn).playerVersusPellet(player);
        }
    }

    public void ghostColliding(Ghost ghost, Unit collidedOn) {
        if (collidedOn instanceof Player) {
            playerVersusGhost((Player) collidedOn, ghost);
        }
    }

    public void pelletColliding(Pellet pellet, Unit collidedOn) {
        if (collidedOn instanceof Player) {
            pellet.playerVersusPellet((Player) collidedOn);
        }
    }


    /**
     * Actual case of player bumping into ghost or vice versa.
     *
     * @param player The player involved in the collision.
     * @param ghost The ghost involved in the collision.
     */
    public void playerVersusGhost(Player player, Ghost ghost) {
        player.setAlive(false);
    }

   

}
