package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameRound {

	private Player player;
	private List<UtilityPile> utilityPile = new ArrayList<>();
	private List<HazardCard> drawnHazardCards = new ArrayList<>();
	private List<HazardCard> hazardPile = new ArrayList<>();
	private final int TOTAL_ROUNDS = 13;
	private Random random = new Random();


	public GameRound(Player player, List<UtilityPile> utilityPiles, List<HazardPile> hazardPiles) {
		this.player = player;
		this.drawnHazardCards = new ArrayList<>();
	}

	public void startGame() {
		for (int round = 1; round <= TOTAL_ROUNDS; round++) {
			System.out.println("-- Round " + round + " ---");
			playRound();
			if (!player.isAlive()) {
				System.out.println("Game Over! Player has died on round " + round);
				return;
			}
		}
		finalBossBattle();
	}

	public void playRound() {
		int utilityCardsToDraw = calculateUtilityCards();
		drawUtilityCards();
		drawFromHazardPile();
		combatPhase();
	}

	private int calculateUtilityCards() {
		if (player.getCreaturesDefeatedLastRound() == 1) {
			return 2;
		} else if (player.getCreaturesDefeatedLastRound() >= 2) {
			return 3;
		}
		return 1;
	}


	 public void drawUtilityCards() {
	        int creaturesDefeated = player.getCreaturesDefeatedLastRound();
	        int numberOfCards = 1;
	        if (creaturesDefeated == 1) {
	            numberOfCards += 1;
	        } else if (creaturesDefeated >= 2) {
	            numberOfCards += 3;
	        }

	        System.out.println("Cartas de Utilidade sorteadas:");
	        for (int i = 0; i < numberOfCards && i < utilityPile.size(); i++) {
	            UtilityPile card = utilityPile.get(i);
	            System.out.println("- " + card.getTitle());
	            player.equipItem(card);
	        }
	    }

	 private void drawFromHazardPile() {
		    drawnHazardCards.clear();
		    for (int i = 0; i < 3 && !hazardPile.isEmpty(); i++) {
		        drawnHazardCards.add(hazardPile.remove(0));
		    }
		    System.out.println("Three Hazard cards were displayed on the table.");
		    if (!drawnHazardCards.isEmpty()) {
		        interactWithHazard(drawnHazardCards.get(0));
		    }
		}

	private boolean interactWithHazard(HazardCard hazard) { //!!!!!!conferir que a fuga perde os itens da mochila!!!!
		if (hazard.isCreature()) {
			System.out.println("Você encontrou uma criatura: " + hazard.getTitle());
			boolean fled = player.useEscapeAbility();
			if (fled) {
				System.out.println("O jogador fugiu e perdeu os itens da mochila!");
				return true; // Retorna true para indicar que o jogador fugiu
			}
			if (hazard instanceof Creature creature) {
				return initiateCombat(creature);
			} else {
				System.out.println("Erro: Tentativa de iniciar combate com uma carta que não é uma criatura.");
				return false;
			}	    } else {
				System.out.println("A carta " + hazard.getTitle() + " foi revelada, mas não é uma criatura.");
				applyHazardEffect(hazard);
				return false; // Retorna false para indicar que não houve combate
			}
	}
	
	private void applyHazardEffect(HazardCard hazard) {
	    System.out.println("The card " + hazard.getTitle() + " has caused its effect.");
	    hazard.applyEffect(player);
	}
	
	public void combatPhase() {
	    System.out.println("Iniciando combate...");
	    int creaturesDefeated = 0;
	    
	    for (HazardCard hazard : hazardPile) {
	        if (hazard.isCreature()) {
	            System.out.println("O jogador enfrenta: " + hazard.getTitle());
	            player.takeDamage(hazard.getAttack());
	            System.out.println("Vida do jogador: " + player.getHealth());
	            System.out.println("Defesa do escudo: " + player.getShieldDefense());

	            if (player.getHealth() > 0) {
	                creaturesDefeated++;
	            } else {
	                System.out.println("O jogador foi derrotado!");
	                break;
	            }
	        }
	    }
	    player.setCreaturesDefeatedLastRound(creaturesDefeated);
	}

	private boolean initiateCombat(Creature creature) {
		Random random = new Random();
		boolean playerStarts = random.nextBoolean();
		System.out.println("Moeda lançada: " + (playerStarts ? "O jogador ataca primeiro." : "A criatura ataca primeiro."));

		while (player.isAlive() && creature.getHealth() > 0) {
			if (playerStarts) {
				playerAttack(creature);
				if (creature.getHealth() > 0) {
					creatureAttack(creature);
				}
			} else {
				creatureAttack(creature);
				if (player.isAlive()) {
					playerAttack(creature);
				}
			}
		}

		if (player.isAlive()) {
			System.out.println("Você derrotou " + creature.getTitle() + " e ganhou 5 de ouro!");
			player.addGold(5);
			player.increaseCreaturesDefeatedLastRound();
			drawnHazardCards.remove(creature);
			if (!drawnHazardCards.isEmpty()) {
				interactWithHazard(drawnHazardCards.get(0));
			}
			return true; // O jogador venceu
		} else {
			return false; // O jogador morreu
		}
	}


	private void playerAttack(Creature creature) { 
	    Random random = new Random();
	    int roll = random.nextInt(6) + 1;
	    int attackPower = player.getWeaponPower();
	    int damage;

	    if (roll == 1) {
	        damage = 0;
	    } else if (roll <= 4) {
	        damage = attackPower / 2;
	    } else {
	        damage = attackPower;
	    }

	    creature.takeDamage(damage); // Agora chamamos diretamente o método da criatura
	    System.out.println("Player has attacked " + creature.getTitle() + " causing " + damage + " of damage.");
	}
	
	public int getCreaturesDefeated() {
	    return player.getCreaturesDefeatedLastRound();
	}

	private void creatureAttack(Creature creature) {
	    Random random = new Random();
	    int roll = random.nextInt(6) + 1;
	    int attackPower = creature.getPower();
	    int damage;

	    if (roll == 1) {
	        damage = 0;
	    } else if (roll <= 4) {
	        damage = attackPower / 2;
	    } else {
	        damage = attackPower;
	    }

	    if (player.hasShield()) {
	        int remainingDefense = player.getShieldDefense() - damage;
	        if (remainingDefense < 0) {
	            player.breakShield();
	            player.takeDamage(Math.abs(remainingDefense));
	        } else {
	            player.reduceShieldDefense(damage);
	        }
	    } else { 
	        player.takeDamage(damage);
	    }
	    
	    System.out.println("The creature has attacked and caused " + damage + " of damage.");
	}





	private void finalBossBattle() {
	    System.out.println("--- Final Battle ---");
	    HazardCard finalBoss = hazardPile.remove(hazardPile.size() - 1);
	    boolean survived = interactWithHazard(finalBoss);
	    if (survived) {
	        System.out.println("Congratulations. You have defeated the final creature. You have won.");
	    } else {
	        System.out.println("Player has been slain.");
	    }
	}
}

