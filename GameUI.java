package cardGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GameUI {
	private JFrame frame;
	private JTextArea gameTextArea;
	private JTextArea statusTextArea;
	private JTextArea logTextArea;
	private JTextField commandField; // Nova área de entrada de comando
	private JButton nextRoundButton;
	private UtilityPile utilityPile;
	private Game game; //Instância da classe Game
	private int selectedIndex = 0;

	private int selectedCardIndex = 0; // Índice de carta selecionada
	private List<UtilityCard> allCards = new ArrayList<>(); // Lista de todas as cartas de utilidade
	private List<UtilityCard> drawnCardsList = new ArrayList<>(); // Lista das cartas sorteadas da rodada


	public GameUI() {
		frame = new JFrame("HAZARD'S QUEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.setLayout(new BorderLayout());
		gameTextArea = new JTextArea();
		gameTextArea.setEditable(false);
		frame.add(new JScrollPane(gameTextArea), BorderLayout.CENTER);
		setupKeyListener();

		// Inicializando o jogo
		game = new Game(); // Cria a instância do jogo

		// Inicializando a pilha de cartas de utilidade
		utilityPile = new UtilityPile();
		showUtilityCards();

		// Painel principal
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.BLACK);

		// Área de status do jogador
		statusTextArea = new JTextArea(6, 30);
		statusTextArea.setEditable(false);
		statusTextArea.setBackground(Color.BLACK);
		statusTextArea.setForeground(Color.YELLOW);
		statusTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		JScrollPane statusScroll = new JScrollPane(statusTextArea);
		statusScroll.setBorder(BorderFactory.createTitledBorder("- Player Status -"));

		// Área principal do jogo (onde as cartas vão aparecer)
		gameTextArea = new JTextArea(20, 50);
		gameTextArea.setEditable(false);
		gameTextArea.setBackground(Color.BLACK);
		gameTextArea.setForeground(Color.WHITE);
		gameTextArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		JScrollPane gameScroll = new JScrollPane(gameTextArea);
		gameScroll.setBorder(BorderFactory.createTitledBorder("- Game -"));

		// Área de log
		logTextArea = new JTextArea(8, 50);
		logTextArea.setEditable(false);
		logTextArea.setBackground(Color.BLACK);
		logTextArea.setForeground(Color.WHITE);
		logTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		JScrollPane logScroll = new JScrollPane(logTextArea);
		logScroll.setBorder(BorderFactory.createTitledBorder("- Log -"));

		// Área de entrada de comando
		commandField = new JTextField();
		commandField.setBackground(Color.BLACK);
		commandField.setForeground(Color.WHITE);
		commandField.setFont(new Font("Courier New", Font.PLAIN, 14));
		commandField.setBorder(BorderFactory.createTitledBorder("- Command -"));

		// Action listener para o campo de comando
		commandField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = commandField.getText();
				handleCommand(command);  // Processa o comando do usuário
				commandField.setText("");  // Limpa o campo de comando após execução
			}
		});

		// Botão de próxima rodada
		nextRoundButton = new JButton("Next Round");
		nextRoundButton.setBackground(Color.RED);
		nextRoundButton.setForeground(Color.BLACK);
		nextRoundButton.setFont(new Font("Courier New", Font.BOLD, 14));

		// Action listener para o botão de próxima rodada
		nextRoundButton.addActionListener(e -> {
			int numberOfCards = 3; 
			game.initializePiles(); // Garante que as pilhas são inicializadas antes do sorteio
			List<String> drawnCards = game.drawUtilityCards(numberOfCards);

			if (drawnCards.isEmpty()) {
				updateGameArea(List.of("No cards drawn."));
			} else {
				updateGameArea(drawnCards);
				updateLogArea("Drawn 3 utility cards:\n" + "\n" + String.join("\n", drawnCards));
			}
		});

		// Layout
		mainPanel.add(statusScroll, BorderLayout.WEST);
		mainPanel.add(gameScroll, BorderLayout.CENTER);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(logScroll, BorderLayout.SOUTH);

		// Painel para agrupar o campo de comando e o botão
		JPanel commandPanel = new JPanel(new BorderLayout());
		commandPanel.add(commandField, BorderLayout.CENTER);
		commandPanel.add(nextRoundButton, BorderLayout.EAST);

		frame.add(commandPanel, BorderLayout.NORTH);

		// Iniciando tela
		frame.setVisible(true);

		// Exemplo de inicialização
		statusTextArea.setText("Health: 15\n" +
				"\nWeapon: \n" +
				"\nShield: \n" +
				"\nAbility: \n" +
				"\nDivinity: \n" +
				"\nDisease: \n" +
				"\nCurse: \n" + 
				"\nBackpack:\n Slot 1: Empty\n \n Slot 2: Empty");

		gameTextArea.setText("Welcome to Hazard's Quest!\nPress 'Next Round' to start.");


		// Adicionando o KeyListener para a navegação
		gameTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				// Setas para cima e para baixo para navegar entre as cartas
				if (code == KeyEvent.VK_UP) {
					if (selectedCardIndex > 0) {
						selectedCardIndex--;
					}
				} else if (code == KeyEvent.VK_DOWN) {
					if (selectedCardIndex < allCards.size() -1) {
						selectedCardIndex++;
					}
				}

				// Atualiza a visualização das cartas com a carta selecionada
				updateGameAreaWithSelection();
			}
		});

		// Foco no gameTextArea para capturar as teclas
		gameTextArea.setFocusable(true);

	}

	private void showUtilityCards() {
		gameTextArea.setText("Utility Cards:\n");
		allCards.clear(); // Limpa a lista antes de adicionar novas cartas

		List<UtilityCard> cards = new ArrayList<>(utilityPile.getUtilityCards());

		for (UtilityCard card : cards) {
			for (int i = 0; i < card.getQuantity(); i++) {
				allCards.add(card);
			}
		}

		Collections.shuffle(allCards);

		updateGameAreaWithSelection(); // Exibe as cartas com destaque na selecionada
	}


	private void updateGameAreaWithSelection() {
		StringBuilder gameText = new StringBuilder("Drawn Utility Cards:\n\n");

		for (int i = 0; i < drawnCardsList.size(); i++) {
			UtilityCard card = drawnCardsList.get(i);
			String cardInfo = card.getTitle();

			if (card.getEffect() != null && !card.getEffect().isEmpty()) {
				cardInfo += " - Effect: " + card.getEffect();
			} else {
				cardInfo += " (ATK: " + card.getAttackPower() + ", DUR: " + card.getDurability() + ")";
			}

			if (i == selectedCardIndex) {
				gameText.append("> "); // Destaca a carta selecionada
			} else {
				gameText.append("  ");
			}
			gameText.append(cardInfo).append("\n");
		}

		gameTextArea.setText(gameText.toString().trim());
	}


	// Atualiza a área de jogo com os títulos das cartas sorteadas
	private void updateGameArea(List<String> drawnCardTitles) {
		drawnCardsList.clear(); // Limpa as cartas da rodada anterior
		StringBuilder gameText = new StringBuilder("Drawn Utility Cards:\n\n");

		for (String title : drawnCardTitles) {
			for (UtilityCard card : utilityPile.getUtilityCards()) {
				if (card.getTitle().equals(title)) {
					drawnCardsList.add(card); // Armazena apenas as cartas sorteadas

					String subType = card.getSubType() != null ? card.getSubType().toLowerCase() : "utility";
					gameText.append(card.getTitle()).append(" (").append(subType).append(")\n");

					if (card.getEffect() != null && !card.getEffect().isEmpty()) {
						gameText.append("-> ").append(card.getEffect()).append("\n\n");
					} else {
						gameText.append("ATK: ").append(card.getAttackPower()).append("\n")
						.append("DUR: ").append(card.getDurability()).append("\n\n");
					}
				}
			}
		}

		selectedCardIndex = 0; // Reseta a seleção para a primeira carta da rodada
		gameTextArea.setText(gameText.toString().trim());
	}

	// Atualiza o histórico (log) com a ação realizada
	private void updateLogArea(String logMessage) {
		String currentLog = logTextArea.getText();
		String updatedLog = currentLog + "\n" + logMessage;
		logTextArea.setText(updatedLog);  // Atualiza o histórico de eventos
	}

	// Processa o comando inserido pelo usuário
	private void handleCommand(String command) {
		if (command.equalsIgnoreCase("up")) {
			if (selectedCardIndex > 0) {
				selectedCardIndex--;
			}
			updateGameAreaWithSelection();
		} else if (command.equalsIgnoreCase("down")) {
			if (selectedCardIndex < allCards.size() - 1) {
				selectedCardIndex++;
			}
			updateGameAreaWithSelection();
		} else {
			updateLogArea("Unknown command: " + command);
		}
	}

	private void setupKeyListener() {
		// Listener para capturar o Enter
		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER) {
				equipSelectedCard();
				return true;
			}
			return false;
		});
	}

	private void equipSelectedCard() {
		Player player = game.getPlayer();
		UtilityCard selectedCard = game.getUtilityCards().get(selectedIndex);

		if (selectedCard != null) {
			player.equipItem(selectedCard);
			logTextArea.append("\nEquipped: " + selectedCard.getTitle() + "\n");
		} else {
			logTextArea.append("\nEquipped: " + selectedCard.getTitle() + "\n");
		}
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(GameUI::new);
	}
}