package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private int active_puzzle;
  private final List<ModelObserver> observerList;
  private boolean[][] lamps;

  public ModelImpl(PuzzleLibrary library) {
    // Your constructor code here
    this.library = library;
    this.active_puzzle = 0;
    this.observerList = new ArrayList<>();
    this.lamps =
        new boolean[library.getPuzzle(active_puzzle).getHeight()]
            [library.getPuzzle(active_puzzle).getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    // exceptions for != corridor and out of bounds!
    if (library.getPuzzle(active_puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (r >= library.getPuzzle(active_puzzle).getHeight()) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= library.getPuzzle(active_puzzle).getWidth()) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (!this.lamps[r][c]) {
      this.lamps[r][c] = true;
    }
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    // exceptions for != corridor and out of bounds!
    if (library.getPuzzle(active_puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (r >= library.getPuzzle(active_puzzle).getHeight()) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= library.getPuzzle(active_puzzle).getWidth()) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (this.lamps[r][c]) {
      this.lamps[r][c] = false;
    }
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    // exceptions for != corridor and out of bounds!
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int puzzheight = this.library.getPuzzle(this.active_puzzle).getHeight();
    int puzzwidth = this.library.getPuzzle(this.active_puzzle).getWidth();
    if (r >= puzzheight) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= puzzwidth) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (this.library.getPuzzle(active_puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("!= corridor!");
    }
    if (lamps[r][c]) {
      return true;
    }
    boolean result;
    result =
        (checkDirection(r, c, 0, -1)
            || checkDirection(r, c, 0, 1)
            || checkDirection(r, c, 1, 0)
            || checkDirection(r, c, -1, 0));
    return result; // checks left,right, up, and down direction, returns true if 1 or more direction
    // is true
  }

  @Override
  public boolean isLamp(int r, int c) {
    // exceptions for != corridor and out of bounds!
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (r >= library.getPuzzle(active_puzzle).getHeight()) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= library.getPuzzle(active_puzzle).getWidth()) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (library.getPuzzle(active_puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return this.lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    // exceptions for != corridor and out of bounds!
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int puzzheight = this.library.getPuzzle(this.active_puzzle).getHeight();
    int puzzwidth = this.library.getPuzzle(this.active_puzzle).getWidth();
    if (r >= puzzheight) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= puzzwidth) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (this.library.getPuzzle(active_puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("!= corridor!");
    }
    // if bloonean returns true then false, if false then true
    if (!lamps[r][c]) {
      return true;
    }
    boolean result;
    result =
        (checkDirection(r, c, 0, -1)
            || checkDirection(r, c, 0, 1)
            || checkDirection(r, c, 1, 0)
            || checkDirection(r, c, -1, 0));
    return result; // checks left,right, up, and down direction, returns true if 1 or more direction
    // is true
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.library.getPuzzle(this.active_puzzle);
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.active_puzzle;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    // exceptions for != corridor and out of bounds!
    if (index < 0) {
      throw new IndexOutOfBoundsException("set index is greater than library size c:");
    }
    if (index >= this.library.size()) {
      throw new IndexOutOfBoundsException("set index is greater than library size c:");
    }
    this.active_puzzle = index;
    this.lamps =
        new boolean[library.getPuzzle(active_puzzle).getHeight()]
            [library.getPuzzle(active_puzzle).getWidth()];
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return this.library.size();
  }

  @Override
  public void resetPuzzle() {
    // ressets puzzle by reseting the lamps to when they were constructed
    this.lamps =
        new boolean[library.getPuzzle(active_puzzle).getHeight()]
            [library.getPuzzle(active_puzzle).getWidth()];
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    // returns false if there is any error with cluesatisfied, illegal lamp, or isLIT. returns true
    // if not!!
    for (int r = 0; r < library.getPuzzle(active_puzzle).getHeight(); r++) {
      for (int c = 0; c < library.getPuzzle(active_puzzle).getWidth(); c++) {
        CellType cellType = library.getPuzzle(active_puzzle).getCellType(r, c);
        if ((cellType == CellType.CORRIDOR
                && (!isLit(r, c) || (isLamp(r, c) && isLampIllegal(r, c))))
            || (cellType == CellType.CLUE && !isClueSatisfied(r, c))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    // exceptions for != corridor and out of bounds!
    int puzzheight = this.library.getPuzzle(this.active_puzzle).getHeight();
    int puzzwidth = this.library.getPuzzle(this.active_puzzle).getWidth();
    if (r >= puzzheight) {
      throw new IndexOutOfBoundsException("r out of bounds");
    }
    if (c >= puzzwidth) {
      throw new IndexOutOfBoundsException("c out of bounds");
    }
    if (r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.library.getPuzzle(this.active_puzzle).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("cell is not type clue :(");
    }
    int x = 0; // uses "X" variable and add one for each direction
    if (r > 0 && lamps[r - 1][c]) {
      x++;
    }
    if (r < puzzheight - 1 && lamps[r + 1][c]) {
      x++;
    }
    if (c > 0 && lamps[r][c - 1]) {
      x++;
    }
    if (c < puzzwidth - 1 && lamps[r][c + 1]) {
      x++;
    }
    return x == this.library.getPuzzle(this.active_puzzle).getClue(r, c);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    this.observerList.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.observerList.remove(observer);
  }

  private boolean checkDirection(int r, int c, int x, int y) {
    // helper function that loops through each column and row *_*
    int puzzheight = this.library.getPuzzle(this.active_puzzle).getHeight();
    int puzzwidth = this.library.getPuzzle(this.active_puzzle).getWidth();
    for (int i = r + x, j = c + y;
        i >= 0 && i < puzzheight && j >= 0 && j < puzzwidth;
        i += x, j += y) {
      if (this.library.getPuzzle(this.active_puzzle).getCellType(i, j) != CellType.CORRIDOR) {
        break;
      }
      if (this.lamps[i][j]) {
        return true;
      }
    }
    return false;
  }
}
