package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    // Your constructor code here
    this.board = board;
  }

  @Override
  public int getWidth() {
    return this.board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    // exceptions for out of bounds
    if (r >= getHeight()) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= getWidth()) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    // switch function for each case 1-6
    int x = board[r][c];
    switch (x) {
      case 0:
        return CellType.CLUE;
      case 1:
        return CellType.CLUE;
      case 2:
        return CellType.CLUE;
      case 3:
        return CellType.CLUE;
      case 4:
        return CellType.CLUE;
      case 5:
        return CellType.WALL;
      case 6:
        return CellType.CORRIDOR;
    }
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    // exceptions for out of bounds
    if (r >= getHeight()) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= getWidth()) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("cell is not type clue");
    }
    return this.board[r][c];
  }
}
