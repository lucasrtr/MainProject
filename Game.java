package cardGame;

import java.util.List;

public class Game {

	private int playerHealth;
	private UtilityPile utilityPile;
	private HazardPile hazardPile;
	private TokenPile tokenPile;
	private Player player;
	private int round;
	private int creaturesDefeatedLastRound; // Contador de criaturas derrotadas por rodada.

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

	//sortear cartas de utilidade:
	private void drawUtilityCards(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {UtilityCard card = utilityPile.drawCard();
		if (card != null) {
			System.out.println(card.getTitle());
		}
		}

	}

	private void drawHazardCards() {
		System.out.println("Hazard Cards drawn:");
		for (int i = 0; i < 3; i++) { // Sempre 3 cartas de perigo
			HazardCard card = hazardPile.drawCard();
			if (card != null) {
				System.out.println(card.getTitle());
				playerHealth -= card.getAttack(); // Exemplo de dano causado pela carta
			}
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
			drawHazardCards();

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