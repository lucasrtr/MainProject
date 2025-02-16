package cardGame;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private boolean canUseEscape;
	private int health;
	private int gold;
	private boolean hasShield;
	private int shieldDefense;
	private int basePower;
	private int creaturesDefeatedLastRound;
	private Weapon equippedWeapon;
	private List<UtilityCard> backpack; 
	private List<UtilityPile> equippedItems = new ArrayList<>();

	public Player() {
		this.canUseEscape = true; // O jogador pode fugir uma vez por rodada
        this.gold = 0; // O jogador começa com 0 de gold
        this.creaturesDefeatedLastRound = 0; // começa com o contador de criaturas eliminadas zerado
        this.health = 15; // Valor inicial de vida
        this.shieldDefense = 0; // Defesa inicial do escudo zerada - mais tarde implementar a questão do shield token
        this.hasShield = false;
        this.backpack = new ArrayList<>(); // Inicializa a mochila
	}

	public int getHealth() {
		return health;
	}

	public int getPassivePower() {
		return basePower;
	}


	public int getCreaturesDefeatedLastRound() {
		return creaturesDefeatedLastRound;
	}

	public void setCreaturesDefeatedLastRound(int count) {
		this.creaturesDefeatedLastRound = count;
	}

	public void increaseCreaturesDefeatedLastRound() {
		creaturesDefeatedLastRound++;
	}
	
	public void equipItem(UtilityPile item) {
		equippedItems.add(item);
		System.out.println("Equipped item: " + item.getTitle());
	}
	
	public void showEquippedItems() {
        System.out.println("Itens equipados:");
        for (UtilityPile item : equippedItems) {
            System.out.println("- " + item.getTitle());
        }
    }

	public void takeDamage(int damage) {
        if (shieldDefense > 0) {
            int remainingDamage = damage - shieldDefense;
            shieldDefense = Math.max(0, shieldDefense - damage);
            if (remainingDamage > 0) {
                health -= remainingDamage;
            }
        } else {
            health -= damage;
        }
    }

	public boolean isAlive() {
		return health > 0;
	}
	
	public void addGold(int amount) {
        this.gold += amount;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public int getShieldDefense() {
        return shieldDefense;
    }
    
    public void reduceShieldDefense(int amount) {
        this.shieldDefense -= amount;
        if (shieldDefense <= 0) {
            breakShield();
        }
    }

    public void breakShield() {
        this.hasShield = false;
        System.out.println("O escudo quebrou!");
    }
	
	public boolean useEscapeAbility() {
		if (canUseEscape) {
			canUseEscape = false; // Só pode usar uma vez por rodada
			discardBackpackItems(); // perde os itens da mochila
			return true;
		}
		return false;
	}
	
	private void discardBackpackItems() {
		backpack.clear(); //implementar backpack
		System.out.println("The backpack items have been lost during the escape.");
	}
	
	private void reserEscapeAbility() {
		this.canUseEscape = true; // Reseta no início de cada rodada -- mais tarde implementar tirar da rodada final
	}
	
	 public int getWeaponPower() {
	        if (equippedWeapon == null) {
	            return 2; // Habilidade passiva "Punhos"
	        }
	        return equippedWeapon.getDamage();
	    }

}