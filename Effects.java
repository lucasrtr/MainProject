package cardGame;

public class Effects {

	private String name;
	private String type; // "Ability", "Divinity", "Disease", "Curse"
	private String description;
	private int duration; // -1 para permanente, ou número de rodadas

	public Effects(String name, String type, String description, int duration) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type; 
	}
	public String getDescription() {
		return description;
	}
	public int getDuration() {
		return duration;
	}
	public void decreaseDuration() {
		if (duration > 0) duration--;
	}

	@Override
	public String toString() {
		return name + " (" + type + "): " + description;
	}

}
