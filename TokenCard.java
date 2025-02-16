package cardGame;

public class TokenCard extends Card {

	private String effect;
//	private int durability; ??

	public TokenCard(String title, String effect) {
		super(title, "Token");
		this.effect = effect;
	}	

	public String getEffect() {
		return effect;
	}
}
