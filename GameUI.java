package cardGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GameUI {
	private JFrame frame;
	private JPanel panel, playerPanel, gamePanel, cardPanel, infoPanel, backpackPanel;
	private JButton startButton, nextRoundButton;
	private JTextArea backpackTextArea;
	private JLabel lifeLabel, weaponLabel, shieldLabel, abilityLabel, divinityLabel, diseaseLabel, curseLabel;
	private UtilityPile utilityPile;
	private Game game;


	public GameUI() {
		game = new Game();
		utilityPile = new UtilityPile();
		utilityPile.initializeCards();

		frame = new JFrame("HAZARD'S QUEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Welcome to Hazard's Quest!");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		startButton = new JButton("Start Game");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Painel do jogador (Topo)
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.setBorder(BorderFactory.createTitledBorder("Player Info"));
		playerPanel.setPreferredSize(new Dimension(250, 600));

		// Labels de stats do jogador
		lifeLabel = new JLabel("Health: " + game.getPlayerLife());
		weaponLabel = new JLabel("Equipped Weapon: None");
		shieldLabel = new JLabel("Equipped Shield: None");
		abilityLabel = new JLabel("Active Ability: None");
		divinityLabel = new JLabel("Active Divinity: None");
		diseaseLabel = new JLabel("Active Disease: None");
		curseLabel = new JLabel("Active Curse: None");

		playerPanel.add(lifeLabel);
		playerPanel.add(weaponLabel);
		playerPanel.add(shieldLabel);
		playerPanel.add(abilityLabel);
		playerPanel.add(divinityLabel);
		playerPanel.add(diseaseLabel);
		playerPanel.add(curseLabel);

		// Painel da mochila do jogador
		backpackPanel = new JPanel();
		backpackPanel.setLayout(new BorderLayout());
		backpackPanel.setBorder(BorderFactory.createTitledBorder("Backpack"));
		backpackTextArea = new JTextArea(5, 20);
		backpackTextArea.setEditable(false);
		backpackPanel.add(new JScrollPane(backpackTextArea), BorderLayout.CENTER);
		playerPanel.add(backpackPanel);

		// Painel do jogo (Centro - Cartas)
		gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBorder(BorderFactory.createTitledBorder("Game Area"));

		cardPanel = new JPanel();
		cardPanel.setLayout(new FlowLayout());
		gamePanel.add(cardPanel, BorderLayout.CENTER);

		// Painel de Botões
		JPanel buttonPanel = new JPanel();
		nextRoundButton = new JButton("Next Round");
		nextRoundButton.setEnabled(false);

		buttonPanel.add(startButton);
		buttonPanel.add(nextRoundButton);		

		// Eventos dos botões
		startButton.addActionListener(e -> startGame());
		nextRoundButton.addActionListener(e -> drawUtilityCards(game.calculateUtilityCards()));

		// Adiciona os painéis ao frame

		panel.add(titleLabel);
		panel.add(startButton);
		panel.add(playerPanel);
		panel.add(gamePanel);
		panel.add(buttonPanel);

		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	private void startGame() {
		cardPanel.removeAll(); // Limpa o painel antes de adicionar novas cartas
		JOptionPane.showMessageDialog(frame, "First Round...");
		JOptionPane.showMessageDialog(frame, "Three Utility cards will be drawn." + "\n You have to choose two of them.");
		startButton.setEnabled(false);
		nextRoundButton.setEnabled(true);
		drawUtilityCards(game.calculateUtilityCards());
		updatePlayerInfo();

	}

	private void drawUtilityCards(int numberOfCards) {
		cardPanel.removeAll();
		List<String> drawnCards = game.drawUtilityCards(numberOfCards);

		for (String cardTitle : drawnCards) {
			JPanel card = new JPanel();
			card.setPreferredSize(new Dimension(100, 150));
			card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			card.add(new JLabel(cardTitle));
			cardPanel.add(card);
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}

	// Atualiza os stats do jogador
	private void updatePlayerInfo() {
		lifeLabel.setText("Health: " + game.getPlayerLife());
		weaponLabel.setText("Equipped Weapon: " + game.getEquippedWeapon());
		shieldLabel.setText("Equipped Shield: " + game.getEquippedShield());
		abilityLabel.setText("Active Ability: " + game.getActiveAbility());
		divinityLabel.setText("Active Divinity: " + game.getActiveDivinity());
		diseaseLabel.setText("Suffering Disease: " + game.getActiveDisease());
		curseLabel.setText("Suffering Curse: " + game.getActiveCurse());
		updateBackpack();
	}

	private void updateBackpack() {
		List<UtilityCard> backpack = game.getPlayer().getBackPack();
		StringBuilder contents = new StringBuilder("Backpack:\n");
		for (UtilityCard card : backpack) {
			contents.append("- ").append(card.getTitle()).append("\n");
		}
		backpackTextArea.setText(contents.toString());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameUI());
	}
}