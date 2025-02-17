package cardGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Player player;
	private int playerHealth;
	private UtilityPile utilityPile;
	private HazardPile hazardPile;
	private TokenPile tokenPile;
	private int round;
	private int creaturesDefeatedLastRound; // Contador de criaturas derrotadas por rodada.
	private List<HazardCard> drawnHazardCards = new ArrayList<>();

	public Game() {
		playerHealth = 15;
		utilityPile = new UtilityPile();
		hazardPile = new HazardPile();
		tokenPile = new TokenPile();
		player = new Player();
		round = 1;
		creaturesDefeatedLastRound = 0;

		initializePiles();

	}
	
	public Player getPlayer() {
	    return player;
	}

	public int getPlayerLife() {
		return player.getHealth();
	}

	public Weapon getEquippedWeapon() {
		return player.getEquippedWeapon();
	}

	public Weapon getEquippedShield() {
		return player.getEquippedShield();
	}

	public Effects getActiveAbility() {
		return player.getActiveAbility();
	}

	public Effects getActiveDivinity() {
		return player.getActiveDivinity();
	}

	public Effects getActiveDisease() {
		return player.getActiveDisease();
	}

	public Effects getActiveCurse() {
		return player.getActiveCurse();
	}

	private void initializePiles() {
		utilityPile.initializeCards();
		hazardPile.initializeCards();
		tokenPile.initializeCards();

	}

	public void startGame() {
		System.out.println("Starting the game...");
		while (round <= 13 && player.isAlive()) {
			System.out.println("Round " + round);
			GameRound gameRound = new GameRound(player, List.of(utilityPile), List.of(hazardPile));
			gameRound.playRound();
			creaturesDefeatedLastRound = gameRound.getCreaturesDefeated();
			round++;
		}

		if (player.isAlive()) {
			System.out.println("The Player survived the 13 rounds! Now it will face the final boss.");
			//implementar logica do boss 

		} else {
			System.out.println("The Player has been defeated.");
		}

	}
	
	public void startCombat() {
	    if (drawnHazardCards.isEmpty()) {
	        System.out.println("No hazard cards to fight!");
	        return;
	    }

	    for (HazardCard hazard : drawnHazardCards) {
	        System.out.println("Fighting: " + hazard.getTitle());
	        // Aqui viria a lógica de combate contra a carta
	    }
	}
	
	public int getCreaturesDefeatedLastRound() {
	    return creaturesDefeatedLastRound;
	}
	
	public int calculateUtilityCards() {
		int defeated = getCreaturesDefeatedLastRound();
	    int defeatedCreaturesLastRound = getCreaturesDefeatedLastRound();
	    if (defeated == 0) return 1;
	    if (defeated == 1) return 2;
	    return 3;
	}

	//sortear cartas de utilidade:
	public List<String> drawUtilityCards(int numberOfCards) {
		List<String> drawnCardTitles = new ArrayList<>();
		for (int i = 0; i < numberOfCards; i++) {
			UtilityCard card = utilityPile.drawCard();
			if (card != null) {
				drawnCardTitles.add(card.getTitle()); // Adiciona o título da carta à lista
			}
		}
		return drawnCardTitles; // Retorna a lista de títulos das cartas
	}

	public void showUtilityCards() {
		System.out.println("Avaiable utilities:");
		utilityPile.showCards();
	}
	
	public int calculateHazardCards() {
	    return (int) (Math.random() * 3) + 1; // Sorteia entre 1 e 3 cartas
	}

	public void drawFromHazardPile(int numberOfCards) {
	    for (int i = 0; i < numberOfCards; i++) {
	        HazardCard card = hazardPile.drawCard();
	        if (card != null) {
	            drawnHazardCards.add(card);
	        }
	    }
	}
	
	public void showHazardCards() {
	    System.out.println("Hazard Cards Drawn:");
	    for (HazardCard card : drawnHazardCards) {
	        System.out.println("- " + card.getTitle());
	    }
	}

	private void playRounds() {
		for (int round = 1; round <= 13; round++) {

			// Se for a primeira rodada, sempre abre 3 cartas de utilidade
			if (round == 1) {
				drawUtilityCards(3);
			} else {
				// Depois da primeira rodada, abre 1 ou 2 cartas de utilidade
				drawUtilityCards(1 + Math.min(creaturesDefeatedLastRound, 2));
			}

			// O jogador enfrenta as 3 cartas de periculosidade
			showHazardCards();

			// Aqui podemos incluir a lógica para o jogador derrotar criaturas
			creaturesDefeatedLastRound = 0; // Resetamos o contador para a próxima rodada

			// Se o jogador perder toda a saúde, o jogo termina
			if (playerHealth <= 0) {
				System.out.println("Você perdeu! Fim de jogo.");
				break;
			}

			// Exemplo de dano por rodada
			playerHealth -= 1;
			System.out.println("Sua saúde: " + playerHealth);
		}

		endGame();
	}

	private void endGame() {
		if (playerHealth > 0) {
			System.out.println("Você venceu o jogo!");
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}
}