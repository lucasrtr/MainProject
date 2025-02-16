package cardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HazardPile extends Card {

	private List<HazardCard> hazardCards;

	public HazardPile(String title) {
		super(title, "Hazard");
		hazardCards = new ArrayList<>();
		initializeCards();

	}
	
	public HazardPile() {
		super("Hazard Pile", "Hazard");
		hazardCards = new ArrayList<>(); // Inicialize a lista de cartas
        initializeCards(); // Preencha as cartas
	}
	
	
	public boolean isEmpty() {
	    return hazardCards.isEmpty();
	}

	public HazardCard remove(int index) {
	    return hazardCards.remove(index);
	}

	public void initializeCards() {

		//Creatures
		addCard(new HazardCard("Vagabond", "Effect: Tetanus", 2, 3, true));
		addCard(new HazardCard("Goblin", "", 2, 5, true));
		addCard(new HazardCard("Marauder", "Effect: Looting", 2, 5, true));
		addCard(new HazardCard("Armed Carcass", "", 2, 6, true));
		addCard(new HazardCard("Rabid Dog", "Effect: Rabies", 2, 5, true));
		addCard(new HazardCard("Fanatic", "Effect: Lack of Faith", 4, 4, true));
		addCard(new HazardCard("Corrupted Knight", "Effect: Bleeding", 6, 4, true));
		addCard(new HazardCard("The One Who Judges", "", 4, 5, true));
		addCard(new HazardCard("Wandering Spirit", "", 6, 3, true));
		addCard(new HazardCard("The Abhorrent", "Effect: Dysentery", 8, 3, true));
		addCard(new HazardCard("...", "", 10, 10, true));
		//Missfortunes
		addCard(new HazardCard("Trap", "Effect: - 5 of health", 0, 0, false));
		addCard(new HazardCard("Swamp Gases", "Effect: Blindness", 0, 0, false));
		addCard(new HazardCard("Impotence", "Effect: Lose equipped Ability", 0, 0, false));
		addCard(new HazardCard("A Hole in the Bag", "Effect: Lose item from slot 1.", 0, 0, false));
		addCard(new HazardCard("Unwanted Beggar", "Effect: Player looses 15 of gold.\"\r\n"
				+ "	+ \"If doesn't have or want to give, the Beggar turns into a Vagabond Token", 0, 0, false));
		//Random
		addCard(new HazardCard("Clear Path", "Effect: Keep walking.", 0, 0, false));

	}
	
	public HazardCard drawCard() {
	    if (!hazardCards.isEmpty()) {
	        Random rand = new Random();
	        int index = rand.nextInt(hazardCards.size()); // Seleciona um índice aleatório
	        return hazardCards.remove(index); // Remove e retorna a carta sorteada
	    }
	    return null; // Retorna null se não houver mais cartas
	}

		public void addCard(HazardCard card) {
			hazardCards.add(card);
		}


		public void applyEffect(Player player) {
			System.out.println("The cards effect " + getTitle() + " is been applied.");
			//lógica para efeitos de azar, doenças ou maldições.
		}

	}
