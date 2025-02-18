

package cardGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GameUI {
	private JFrame frame;
	private JTextArea playerInfo;
	private JPanel gameArea;
	private JPanel hazardZone;
	private JPanel panel, playerPanel, gamePanel, cardPanel, infoPanel, backpackPanel;
	private JButton startButton, nextRoundButton;
	private JTextArea backpackTextArea;
	private JScrollPane backPackScroll;
	private JLabel lifeLabel, weaponLabel, shieldLabel, abilityLabel, divinityLabel, diseaseLabel, curseLabel;
	private UtilityPile utilityPile;
	private Game game;


	public GameUI() {
		game = new Game();
		utilityPile = new UtilityPile();
		utilityPile.initializeCards();

		frame = new JFrame("HAZARD'S QUEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setLayout(new BorderLayout());

		
		// Header
		JLabel titleLabel = new JLabel("Welcome to Hazard's Quest!", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		frame.add(titleLabel, BorderLayout.NORTH);

		startButton = new JButton("Start Game");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.addActionListener(e -> startGame());

		// Painel do jogador
		JPanel playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
		playerInfoPanel.setBorder(BorderFactory.createTitledBorder("Player Info"));
		playerInfo = new JTextArea("Health: 15\nEquipped Weapon: None\nEquipped Shield: None\nActive Ability: None\nActive Divinity: None\nActive Disease: None\nActive Curse: None");
		playerInfo.setEditable(false);
		playerInfoPanel.add(playerInfo);
		frame.add(playerInfoPanel, BorderLayout.WEST);
		
		// Game Area Panel
		gameArea = new JPanel(new BorderLayout());
		gameArea.setBorder(BorderFactory.createTitledBorder("Game Area"));
		gameArea.setLayout(new GridLayout(1, 2, 10, 10)); // Expandindo Game Area
		frame.add(gameArea, BorderLayout.CENTER);
		
		// Criando o painel de cartas dentro do gamePanel
		gamePanel = new JPanel(new BorderLayout());
		cardPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		gamePanel.add(cardPanel, BorderLayout.NORTH);
		gameArea.add(gamePanel, BorderLayout.CENTER);
		
		// Hazard Zone
		hazardZone = new JPanel();
        hazardZone.setBorder(BorderFactory.createTitledBorder("Hazard Zone"));
        hazardZone.setLayout(new FlowLayout(FlowLayout.LEFT)); // Mostrar múltiplas cartas
        gameArea.add(hazardZone);

	/*	// Labels de stats do jogador
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
		*/

		// Painel da mochila do jogador
		backpackPanel = new JPanel();
		backpackPanel.setBorder(BorderFactory.createTitledBorder("Backpack"));
		backpackPanel.setLayout(new FlowLayout());
		backpackTextArea = new JTextArea(5, 40);
		backpackTextArea.setEditable(false);
		backPackScroll = new JScrollPane(backpackTextArea);
		backpackPanel.add(backPackScroll, BorderLayout.CENTER);
	//	backpackPanel.setPreferredSize(new Dimension(900, 60)); // Reduzindo drasticamente o espaço
		frame.add(backpackPanel, BorderLayout.SOUTH);

		// Painel do jogo (Centro - Cartas)
		cardPanel = new JPanel();
		cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		gameArea.add(cardPanel);

		cardPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		gamePanel.add(cardPanel, BorderLayout.NORTH);
		
		// Painel de Hazards
		hazardZone = new JPanel(new FlowLayout());
		hazardZone.setBorder(BorderFactory.createTitledBorder("Hazard Zone"));
		gamePanel.add(hazardZone, BorderLayout.CENTER);

		// Painel de Botões
		JPanel buttonPanel = new JPanel();
        nextRoundButton = new JButton("Next Round");
        nextRoundButton.setEnabled(false);
        buttonPanel.add(startButton);
        buttonPanel.add(nextRoundButton);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
        
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(playerInfoPanel, BorderLayout.WEST);
        frame.add(gameArea, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
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
	    // Garantindo que 3 cartas sejam exibidas
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
		backpackTextArea = new JTextArea(3, 40);
		backpackTextArea.setEditable(false);
		JScrollPane backpackScroll = new JScrollPane(backpackTextArea);
		backpackPanel.add(backpackScroll);
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