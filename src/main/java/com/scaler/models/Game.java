package com.scaler.models;

import java.util.ArrayList;
import java.util.List;

import com.scaler.models.enums.CellState;
import com.scaler.models.enums.GameState;
import com.scaler.strategy.ColumnWinningStrategy;
import com.scaler.strategy.DiagonalWinningStrategy;
import com.scaler.strategy.RowWinningStrategy;
import com.scaler.strategy.WinningStrategy;

public class Game {
	private final Board board;
	private final List<Player> players;
	private final List<WinningStrategy> winningStrategies;
	private int currentPlayerIndex;
	private int movesPlayed;
	private GameState gameState;
	private Player winner;

	public Game(int boardSize, List<Player> players) {
		this(new Board(boardSize), players, defaultWinningStrategies(boardSize));
	}

	public Game(Board board, List<Player> players, List<WinningStrategy> winningStrategies) {
		if (board == null || board.getSize() < 1) {
			throw new IllegalArgumentException("Board size must be positive");
		}
		if (players == null || players.size() < 2) {
			throw new IllegalArgumentException("At least two players are required");
		}
		this.board = board;
		this.players = new ArrayList<>(players);
		this.winningStrategies = new ArrayList<>(winningStrategies);
		this.gameState = GameState.IN_PROGRESS;
	}

	private static List<WinningStrategy> defaultWinningStrategies(int boardSize) {
		return List.of(new RowWinningStrategy(boardSize),
				new ColumnWinningStrategy(boardSize),
				new DiagonalWinningStrategy(boardSize));
	}

	public void play() {
		while (gameState == GameState.IN_PROGRESS) {
			printBoard();
			makeMove(currentPlayer().makeMove(board));
		}
		printBoard();
		System.out.println(winner == null ? "Game ended in a draw." : winner.getName() + " wins!");
	}

	public void makeMove(Move move) {
		if (gameState != GameState.IN_PROGRESS) {
			throw new IllegalStateException("The game has already ended");
		}
		if (move == null || move.getPlayer() != currentPlayer()) {
			throw new IllegalArgumentException("It is not this player's turn");
		}

		Cell requestedCell = move.getCell();
		if (requestedCell == null || requestedCell.getRow() < 0 || requestedCell.getRow() >= board.getSize()
				|| requestedCell.getCol() < 0 || requestedCell.getCol() >= board.getSize()) {
			throw new IllegalArgumentException("Move is outside the board");
		}

		Cell cell = board.getCells().get(requestedCell.getRow()).get(requestedCell.getCol());
		if (!cell.isEmpty()) {
			throw new IllegalArgumentException("Cell is already occupied");
		}

		cell.setCellState(CellState.FILLED);
		cell.setPlayer(move.getPlayer());
		movesPlayed++;

		Move appliedMove = new Move(move.getPlayer(), cell);
		for (WinningStrategy winningStrategy : winningStrategies) {
			if (winningStrategy.checkWinner(appliedMove)) {
				winner = move.getPlayer();
				gameState = GameState.ENDED;
				return;
			}
		}
		if (movesPlayed == board.getSize() * board.getSize()) {
			gameState = GameState.DRAW;
			return;
		}
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}

	private Player currentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public void printBoard() {
		for (List<Cell> row : board.getCells()) {
			for (Cell cell : row) {
				String value = cell.isEmpty() ? " " : cell.getPlayer().getSymbol().getCharacter().toString();
				System.out.print(" " + value + " ");
				if (cell.getCol() < board.getSize() - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row.get(0).getRow() < board.getSize() - 1) {
				System.out.println("---".repeat(board.getSize() * 2 - 1));
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public GameState getGameState() {
		return gameState;
	}

	public Player getCurrentPlayer() {
		return currentPlayer();
	}

	public Player getWinner() {
		return winner;
	}
}
