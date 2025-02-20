package cardGame;

public class UtilityCard extends Card {

	private String subType; // divinity, ability, item, random
	private String effect;
	private int attackPower;
	private int durability;
	private int quantity;

	public UtilityCard(String title, String subType, String effect, int attackPower, int durability, int quantity) {
		super(title, "Utility");
		this.effect = effect;
		this.attackPower = attackPower;
		this.durability = durability;
		this.subType = subType;
		this.quantity = quantity;
	}
	
	public UtilityCard(String title, String subType, String effect, int attackPower, int durability) {
		this(title, subType, effect, attackPower, durability, 1);
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
	
	public int getQuantity() {
		return quantity;
	}
	
	public void increaseQuantity(int amount) {
		this.quantity += amount;
	}

}
