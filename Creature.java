package cardGame;

public class Creature extends HazardCard {

	private int health;
	private int power;

	public Creature(String title, int health, int power) {
		super(title, title, power, power, true);
		this.health = health;
		this.power = power;
	}

	public int getHealth() {
		return health;
	}

	public int getPower() {
		return this.power;
	}

	public void takeDamage(int damage) {
		this.health -= damage;
		if (this.health < 0) {
			this.health = 0;
		}
	}
}
