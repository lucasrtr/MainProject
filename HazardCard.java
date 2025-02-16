package cardGame;

public class HazardCard extends Card {

	private String effect;
	private int attack;
	private int health;
	private boolean isCreature;

	public HazardCard(String title, String effect, int attack, int health, boolean isCreature) {
		super(title, "Hazard");
		this.effect = effect;
		this.attack = attack;
		this.health = health;
		this.isCreature = isCreature;

	}
	
	public boolean isCreature() {
		return this instanceof Creature;
	}

	public int getAttack() {
		return attack;
	}

	public int getHealth() {
		return health;
	}
	
	public String getEffect() {
		return effect;
	}
	
	public void applyEffect(Player player) {
        // Implementação do efeito da carta, ex: reduzir vida, aplicar maldição, etc.
        System.out.println(player + " foi afetado por " + getTitle());
    }
	
}
