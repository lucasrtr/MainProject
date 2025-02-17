package cardGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
	private JPanel panel;
	private JButton startButton;
	private JPanel cardPanel;
	private JPanel infoPanel; // Novo painel para exibir informações do jogador
	private UtilityPile utilityPile;
	private Game game;


	// Stats do jogador
	private JLabel lifeLabel;
	private JLabel weaponLabel;
	private JLabel shieldLabel;
	private JLabel abilityLabel;
	private JLabel divinityLabel;
	private JLabel diseaseLabel;
	private JLabel curseLabel;
	private JLabel backpackLabel;
	private JTextArea backpackTextArea;

	public GameUI() {
		game = new Game();

		frame = new JFrame("HAZARD'S QUEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLayout(new BorderLayout());

		// Painel onde as cartas serão exibidas
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Painel onde os stats do jogador serão exibidos
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		// Inicializando a pilha de utilidades
		utilityPile = new UtilityPile();
		utilityPile.initializeCards();

		// Labels para stats do jogador
		lifeLabel = new JLabel("Health: " + game.getPlayerLife());
		weaponLabel = new JLabel("Equipped Weapon: None");
		shieldLabel = new JLabel("Equipped Shield: None");

		abilityLabel = new JLabel("Active Ability: None");
		divinityLabel = new JLabel("Active Divinity: None");
		diseaseLabel = new JLabel("Active Disease: None");
		curseLabel = new JLabel("Active Curse: None");

		backpackLabel = new JLabel("Backpack:");
		backpackTextArea = new JTextArea(5, 20);
		backpackTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(backpackTextArea);

		infoPanel.add(lifeLabel);
		infoPanel.add(weaponLabel);
		infoPanel.add(shieldLabel);
		infoPanel.add(abilityLabel);
		infoPanel.add(divinityLabel);
		infoPanel.add(diseaseLabel);
		infoPanel.add(curseLabel);
		infoPanel.add(backpackLabel);
		infoPanel.add(scrollPane);


		JLabel titleLabel = new JLabel("Welcome to Hazard's Quest!");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		startButton = new JButton("Start Game");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Painel onde as cartas serão exibidas
		cardPanel = new JPanel();
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		panel.add(startButton);
		panel.add(cardPanel);
		frame.add(panel,BorderLayout.CENTER);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Game has started!");
				startGame();
			}
		});

		frame.setVisible(true);



		// Adicionando os componentes ao painel
		panel.add(titleLabel);
		panel.add(startButton);
		panel.add(cardPanel);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);

	}

	private void startGame() {
		cardPanel.removeAll(); // Limpa o painel antes de adicionar novas cartas

		List<String> drawnCards = game.drawUtilityCards(3);
		// Exibe as cartas de forma organizada
		for (String cardTitle : drawnCards) {
			JLabel cardLabel = new JLabel("- " + cardTitle);
			cardPanel.add(cardLabel);
		}
		JOptionPane.showMessageDialog(frame, "First Round...");
		JOptionPane.showMessageDialog(frame, "Three Utility cards will be drawn." + "\n You have to choose two of them.");
		frame.add(infoPanel, BorderLayout.SOUTH);
		cardPanel.revalidate();
		cardPanel.repaint();

		// Atualiza os stats do jogador
		updatePlayerInfo();
	}

	private void updatePlayerInfo() {
		// Atualiza os stats do jogador (com valores reais após o inicio do jogo)
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

		// Converte a lista para uma string legível
		StringBuilder backpackContents = new StringBuilder("Backpack:\n");
		for (UtilityCard card : backpack) {
			backpackContents.append("- ").append(card.getTitle()).append("\n");
		}

		// Define o texto formatado na JTextArea
		backpackTextArea.setText(backpackContents.toString());
	}


	private void drawUtilityCards(int numberOfCards) {
		cardPanel.removeAll(); // Limpa cartas antigas

		List<UtilityCard> drawnCards = new ArrayList<>();
		for (int i = 0; i < numberOfCards; i++) {
			UtilityCard card = utilityPile.drawCard();
			if (card != null) {
				drawnCards.add(card);
				JLabel cardLabel = new JLabel(card.getTitle());
				cardPanel.add(cardLabel);
			}
		}

		for (UtilityCard card : drawnCards) {
			JLabel cardLabel = new JLabel("Card: " + card.getTitle());
			cardPanel.add(cardLabel);
		}

		cardPanel.revalidate();
		cardPanel.repaint();

		// Adicionando o painel ao frame
		frame.add(panel, BorderLayout.CENTER);

		// Tornando a janela visível
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameUI());
	}
}