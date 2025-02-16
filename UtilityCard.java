package cardGame;

public class UtilityCard extends Card {

	private String effect;
	private int attack;
	private int durability;

	public UtilityCard(String title, String effect, int attack, int durability) {
		super(title, "Utility");
		this.effect = effect;
		this.attack = attack;
		this.durability = durability;

	}
	public String getEffect() {
		return effect;

	}
	public int getAttack() {
		return attack;

	}
	public int getDurability() {
		return durability;

	}

}
