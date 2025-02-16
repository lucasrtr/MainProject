package cardGame;

public abstract class Card {

	private String title;
	private String type;
	private int quantity = 1; // Contador de quantidades da carta


	public Card(String title, String type) {
		super();
		this.title = title;
		this.type = type;
		this.quantity = 1; // Todas começam com 1 unidade

	}
	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void increaseQuantity() {
		this.quantity++;
	}


	@Override
	public String toString() {
		return title + " (" + type + ") x" + quantity;
	}

}
