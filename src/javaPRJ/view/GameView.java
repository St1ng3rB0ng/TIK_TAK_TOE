package javaPRJ.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JPanel topPanel;
    private JPanel boardPanel;
    private JPanel bottomPanel;

    private JLabel statusLabel;
    private JLabel scoreLabel;

    private JButton[][] buttons;
    private JButton resetButton;

    public JButton getButton(int row, int col){return buttons[row][col];}
    public JButton getResetButton() {return resetButton;}

    private static final Color BACKGROUND_COLOR = new Color(44, 62, 80);
    private static final Color BUTTON_COLOR = new Color(52, 73, 94);
    private static final Color BUTTON_HOVER = new Color(41, 128, 185);
    private static final Color TEXT_COLOR = new Color(236, 240, 241);
    private static final Color X_COLOR = new Color(231, 76, 60);
    private static final Color O_COLOR = new Color(46, 204, 113);

    public GameView(){
        setTitle("TIK-TAK-TOE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(10, 10));

        createTopPanel();
        createBoardPanel();
        createBottomPanel();
    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 5, 5));
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        // Статус [гри]
        statusLabel = new JLabel("Хід гравця: X", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(TEXT_COLOR);
        // Рахунок
        scoreLabel = new JLabel("X: 0  |  O: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setForeground(TEXT_COLOR);

        topPanel.add(statusLabel);
        topPanel.add(scoreLabel);

        add(topPanel, BorderLayout.NORTH);
    }

    private void createBoardPanel() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(BACKGROUND_COLOR);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 48));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(BUTTON_COLOR);
                buttons[i][j].setForeground(TEXT_COLOR);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 3));

                // Ефект при наведенні
                JButton btn = buttons[i][j];
                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (btn.getText().isEmpty()) {
                            btn.setBackground(BUTTON_HOVER);
                        }
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btn.setBackground(BUTTON_COLOR);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        resetButton = new JButton("Рестарт");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setFocusPainted(false);
        resetButton.setBackground(new Color(41, 128, 185));
        resetButton.setForeground(TEXT_COLOR);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(resetButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateButton(int row, int col, String text) {
        buttons[row][col].setText(text);
        if (text.equals("X")) {
            buttons[row][col].setForeground(X_COLOR);
        } else if (text.equals("O")) {
            buttons[row][col].setForeground(O_COLOR);
        }
    }

    public void updateStatus(String status) {statusLabel.setText(status);}
    public void updateScore(int xWins, int oWins) {scoreLabel.setText("X: " + xWins + "  |  O: " + oWins);}

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(TEXT_COLOR);
            }
        }
    }
}
