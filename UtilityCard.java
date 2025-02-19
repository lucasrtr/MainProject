package cardGame;

public class UtilityCard extends Card {

	private String subType; // divinity, ability, item, random
	private String effect;
	private int attackPower;
	private int durability;

	public UtilityCard(String title, String subType, String effect, int attackPower, int durability) {
		super(title, "Utility");
		this.effect = effect;
		this.attackPower = attackPower;
		this.durability = durability;
		this.subType = subType;
	}

	public String getEffect() {
		return effect;

	}
	public int getAttackPower() {
		return attackPower;

	}
	public int getDurability() {
		return durability;

	}
	
	public String getSubType() {
		return subType;
	}

}
