package javaPRJ.controller;

import javaPRJ.model.GameModel;
import javaPRJ.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addBoardButtonListener(i, j);
            }
        }
        view.getResetButton().addActionListener(e -> resetGame());
        updateView();
    }

    private void addBoardButtonListener(int row, int col) {
        JButton button = view.getButton(row, col);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMove(row, col);
            }
        });
    }

    /**
     * Обробка ходу гравця
     * -> Якщо гра закінчена або хід неможливий — виходимо
     * -> Виконуємо хід
     * -> Оновлюємо кнопку
     * -> Перевіряємо чи виграв поточний гравець
     * -> Перевіряємо нічию
     * -> Якщо гра продовжується — змінюємо гравця
     */
    private void handleMove(int row, int col) {
        if (model.isGameOver() || !model.getCell(row, col).equals(GameModel.EMPTY)) {
            return;
        }
        if (model.makeMove(row, col)) {
            view.updateButton(row, col, model.getCurrentPlayer());
            if (model.checkWin()) {
                model.endGame(true);
                view.updateStatus("Переміг гравець: " + model.getCurrentPlayer() + "!");
                view.updateScore(model.getXWins(), model.getOWins());
                return;
            }
            if (model.checkDraw()) {
                model.endGame(false);
                view.updateStatus("Нічия!");
                return;
            }
            model.switchPlayer();
            view.updateStatus("Хід гравця: " + model.getCurrentPlayer());
        }
    }

    private void resetGame() {
        model.resetGame();
        view.clearBoard();
        updateView();
    }

    private void updateView() {
        view.updateStatus("Хід гравця: " + model.getCurrentPlayer());
        view.updateScore(model.getXWins(), model.getOWins());
    }
}