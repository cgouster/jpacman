package nl.tudelft.jpacman.npc;


import java.util.Random;

public class GhostProduct {
	private final int moveInterval;
	private final int intervalVariation;

	public GhostProduct(int intervalVariation, int moveInterval) {
		this.intervalVariation = intervalVariation;
		this.moveInterval = moveInterval;
	}

	/**
	* The time that should be taken between moves.
	* @return  The suggested delay between moves in milliseconds.
	*/
	public long getInterval() {
		return this.moveInterval + new Random().nextInt(this.intervalVariation);
	}
}
