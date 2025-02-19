package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilityPile {

	private List<UtilityCard> utilityCards;

	public UtilityPile() {
		utilityCards = new ArrayList<>();
		initializeCards();

	}

	public void initializeCards() {
		// Blessings
		utilityCards.add(new UtilityCard("Taxing the Rich", "Player receives +5 gold per killing.", 0, 0));
		utilityCards.add(new UtilityCard("Unshakable Faith", "Player is immune to all Curses.", 0, 0));
		utilityCards.add(new UtilityCard("Supreme Outcry", "Player is revived with 6 life and draws 3 cards.", 0, 0));

		//Abilities
		utilityCards.add(new UtilityCard("Vigorousness", "Player is immune to all Diseases.", 0, 0));
		utilityCards.add(new UtilityCard("Friendly Mule", "Player can escape all enemies and misfortunes without losing the backpack.", 0, 0));

		//Random
		utilityCards.add(new UtilityCard("Gold Coins", "+ 5 gold coins!", 0, 0));

		//Special Items
		utilityCards.add(new UtilityCard("Healing Schnapps", "Effect: Heals 5 of health and cure active diseases.", 0, 0));
		utilityCards.add(new UtilityCard("Lord's Gloves", "Effect: Increases + 2/3 of equipped weapon.", 0, 0));
		utilityCards.add(new UtilityCard("Shield Ointment", "Effect: +3 resistance to equipped shield.", 0, 0));
		//Weapons
		utilityCards.add(new UtilityCard("Barely Usable Stick", "", 4, 1));
		utilityCards.add(new UtilityCard("Dagger", "", 4, 2));
		utilityCards.add(new UtilityCard("Crossbow", "", 4, 3));
		utilityCards.add(new UtilityCard("Crossbow", "", 4, 3));
		utilityCards.add(new UtilityCard("Sword", "", 6, 2));
		utilityCards.add(new UtilityCard("Magic Sword", "", 8, 2));
		utilityCards.add(new UtilityCard("QuestEnder", "", 10, 1));
		//Shields
		utilityCards.add(new UtilityCard("Sewer Cover", "", 0, 2));
		utilityCards.add(new UtilityCard("Moribund's Corpse", "", 0, 4));
		utilityCards.add(new UtilityCard("Wooden Shield", "", 0, 6));
		utilityCards.add(new UtilityCard("Metal Shield", "", 0, 8));
		utilityCards.add(new UtilityCard("Magical Shield", "", 0, 10));
		utilityCards.add(new UtilityCard("RaddanShield", "", 0, 12));
	}

	public void add(UtilityCard newCard) {
		for (UtilityCard card : utilityCards) {
			if (card.getTitle().equals(newCard.getTitle())) {
				card.increaseQuantity();
				return;
			}
		}
		utilityCards.add(newCard);
	}

	public List<UtilityCard> getUtilityCards(){
		return utilityCards;
	}

	public void showCards() {
		for (Card card : utilityCards) {
			System.out.println(card);
		}
	}

	public UtilityCard drawCard() {
		if (!utilityCards.isEmpty()) {
			return (UtilityCard) utilityCards.remove(0);
		}
		return null;
	}

	//	public int getSize() {
	//		return utilityCards.size();
	//}

}
