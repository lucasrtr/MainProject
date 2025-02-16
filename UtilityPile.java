package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilityPile extends Card {

	private List<Card> cards;
	
	

	public UtilityPile(String title) {
		super(title, "Utility");
		cards = new ArrayList<>();
		initializeCards();

	}
	
	public UtilityPile() {
	    super("Utility Pile", "Utility");
	    cards = new ArrayList<>();
	}

	public void initializeCards() {
		// Blessings
		addCard(new UtilityCard("Taxing the Rich", "Player receives +5 gold per killing.", 0, 0));
		addCard(new UtilityCard("Unshakable Faith", "Player is immune to all Curses.", 0, 0));
		addCard(new UtilityCard("Supreme Outcry", "Player is revived with 6 life and draws 3 cards.", 0, 0));

		//Abilities
		addCard(new UtilityCard("Vigorousness", "Player is immune to all Diseases.", 0, 0));
		addCard(new UtilityCard("Friendly Mule", "Player can escape all enemies and misfortunes without losing the backpack.", 0, 0));

		//Random
		addCard(new UtilityCard("Gold Coins", "+ 5 gold coins!", 0, 0));

		//Special Items
		addCard(new UtilityCard("Healing Schnapps", "Effect: Heals 5 of health and cure active diseases.", 0, 0));
		addCard(new UtilityCard("Lord's Gloves", "Effect: Increases + 2/3 of equipped weapon.", 0, 0));
		addCard(new UtilityCard("Shield Ointment", "Effect: +3 resistance to equipped shield.", 0, 0));
		//Weapons
		addCard(new UtilityCard("Barely Usable Stick", "", 4, 1));
		addCard(new UtilityCard("Dagger", "", 4, 2));
		addCard(new UtilityCard("Crossbow", "", 4, 3));
		addCard(new UtilityCard("Crossbow", "", 4, 3));
		addCard(new UtilityCard("Sword", "", 6, 2));
		addCard(new UtilityCard("Magic Sword", "", 8, 2));
		addCard(new UtilityCard("QuestEnder", "", 10, 1));
		//Shields
		addCard(new UtilityCard("Sewer Cover", "", 0, 2));
		addCard(new UtilityCard("Moribund's Corpse", "", 0, 4));
		addCard(new UtilityCard("Wooden Shield", "", 0, 6));
		addCard(new UtilityCard("Metal Shield", "", 0, 8));
		addCard(new UtilityCard("Magical Shield", "", 0, 10));
		addCard(new UtilityCard("RaddanShield", "", 0, 12));
	}

	public void addCard(Card newCard) {
		for (Card card : cards) {
			if (card.getTitle().equals(newCard.getTitle())) {
				card.increaseQuantity();
				return;
			}
		}
		cards.add(newCard);
	}

	public void showCards() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}

	 public UtilityCard drawCard() {
	        if (!cards.isEmpty()) {
	            return (UtilityCard) cards.remove(0);
	        }
	        return null;
	    }

//	public int getSize() {
//		return utilityCards.size();
	//}

}
