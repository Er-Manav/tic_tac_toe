package com.scaler.models;

import com.scaler.models.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> cells;

    public Board(int size) {
        this.size = size;

        this.cells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.cells.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                this.cells.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = cells.get(row).get(col);
                String value = cell.isEmpty() ? " " : cell.getPlayer().getSymbol().getCharacter().toString();
                System.out.print(" " + value + " ");
                if (col < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < size - 1) {
                System.out.println("---".repeat(size * 2 - 1));
            }
        }
    }
}
