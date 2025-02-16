package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenPile {

	private List<TokenCard> tokenCards;

	public TokenPile() {
		tokenCards = new ArrayList<>();// Adicionar cartas de token
		initializeCards();

	}

	public void initializeCards() {

		//TOKENS
		addCard(new TokenCard("Tetanus", "Effect: -2 of health per interaction"));
		addCard(new TokenCard("Alcoholism", "Effect: Player cannot activate any Abilities."));
		addCard(new TokenCard("Bleeding", "Effect: -1 of life per interaction."));
		addCard(new TokenCard("Rabies", "Effect: Escape becomes unavaible."));
		addCard(new TokenCard("Dysentery", "Effect: -5 of health."));	

	}


	public void addCard(TokenCard card) {
		tokenCards.add(card);
	}

	public TokenCard drawCard() {
		if (!tokenCards.isEmpty()) {
			Random rand = new Random();
			int index = rand.nextInt(tokenCards.size());
			return tokenCards.remove(0);
		}
		return null;
	}

	public int getSize() {
		return tokenCards.size();
	}

}
