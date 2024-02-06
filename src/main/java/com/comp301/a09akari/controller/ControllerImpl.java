package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import java.util.concurrent.ThreadLocalRandom;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    // Constructor code goes here
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    // checks if there is a next puzzle, if not set the puzzle index back to 0.
    if (model.getActivePuzzleIndex() + 1 < model.getPuzzleLibrarySize()) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    } else {
      model.setActivePuzzleIndex(0);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    // checks if there is a prev puzzle, if not set the puzzle to librarysize - 1 for last index.
    if (model.getActivePuzzleIndex() - 1 >= 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } else {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    // gets a random number between 0 and librarysize, if the number is the active pizzle, then go
    // again until the number is different
    int random_int = ThreadLocalRandom.current().nextInt(0, model.getPuzzleLibrarySize());
    while (model.getActivePuzzleIndex() == random_int) {
      random_int = ThreadLocalRandom.current().nextInt(0, model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(random_int);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    // if there is lamp then remove, if not add lamp
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  @Override
  public int getIndex() {
    return model.getActivePuzzleIndex() + 1;
  }

  @Override
  public int getSize() {
    return model.getPuzzleLibrarySize();
  }
}
