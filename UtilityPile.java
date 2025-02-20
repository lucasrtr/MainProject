package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilityPile {

	private List<UtilityCard> utilityCards;
	private Card card;
	
	public UtilityPile() {
		utilityCards = new ArrayList<>();
		initializeCards();

	}

	public void initializeCards() {
		// Blessings
		utilityCards.add(new UtilityCard("Taxing the Rich", "Blessing", "Player receives +5 gold per killing.", 0, 0, 2));
		
		utilityCards.add(new UtilityCard("Unshakable Faith", "Blessing", "Player is immune to all Curses.", 0, 0, 2));
		
		utilityCards.add(new UtilityCard("Supreme Outcry", "Blessing", "Player is revived with 6 life and draws 3 cards.", 0, 0, 2));
		

		//Abilities
		utilityCards.add(new UtilityCard("Vigorousness", "Ability", "Player is immune to all Diseases.", 0, 0, 2));
		utilityCards.add(new UtilityCard("Friendly Mule", "Ability", "Player can escape all enemies and misfortunes without losing the backpack.", 0, 0, 2));

		//Random
		utilityCards.add(new UtilityCard("Gold Coins", "Random", "+ 5 gold coins!", 0, 0, 4));

		//Special Items
		utilityCards.add(new UtilityCard("Healing Schnapps", "Special Item", "Effect: Heals 5 of health and cure active diseases.", 0, 0, 3));
		utilityCards.add(new UtilityCard("Lord's Gloves", "Special Item", "Effect: Increases + 2/3 of equipped weapon.", 0, 0, 2));
		utilityCards.add(new UtilityCard("Shield Ointment", "Special Item", "Effect: +3 resistance to equipped shield.", 0, 0, 2));
		//Weapons
		utilityCards.add(new UtilityCard("Barely Usable Stick", "Weapon", "", 4, 1, 3));
		utilityCards.add(new UtilityCard("Dagger", "Weapon", "", 4, 2, 3));
		utilityCards.add(new UtilityCard("Crossbow", "Weapon", "", 4, 3, 3));
		utilityCards.add(new UtilityCard("Sword", "Weapon", "", 6, 2, 3));
		utilityCards.add(new UtilityCard("Magic Sword", "Weapon","", 8, 2, 1));
		utilityCards.add(new UtilityCard("QuestEnder", "Weapon","", 10, 1, 1));
		//Shields
		utilityCards.add(new UtilityCard("Sewer Cover", "Shield", "", 0, 2, 2));
		utilityCards.add(new UtilityCard("Moribund's Corpse", "Shield", "", 0, 4, 2));
		utilityCards.add(new UtilityCard("Wooden Shield", "Shield", "", 0, 6, 2));
		utilityCards.add(new UtilityCard("Metal Shield", "Shield", "", 0, 8, 2));
		utilityCards.add(new UtilityCard("Magical Shield", "Shield", "", 0, 10, 1));
		utilityCards.add(new UtilityCard("RaddanShield", "Shield", "", 0, 12, 1));
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
		return new ArrayList<>(utilityCards);
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
