package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class PuzzleView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  public Parent render() {
    TilePane board = createBoard();
    int x = controller.getActivePuzzle().getWidth();
    int y = controller.getActivePuzzle().getHeight();
    // configures and populates the board base off size :0
    configureBoard(board, x, y, 3, 63);
    populateBoard(board, x, y, 60);
    return board;
  }

  private TilePane createBoard() {
    return new TilePane();
  }

  private void configureBoard(TilePane board, int x, int y, int i, int j) {
    board.setPrefColumns(x);
    board.setPrefRows(y);
    board.setHgap(i);
    board.setVgap(i);
    int up = y * j;
    int down = x * j;
    board.setMinWidth(down);
    board.setMinHeight(up);
    board.setMaxWidth(down);
    board.setMaxHeight(up);
  }

  private void populateBoard(TilePane board, int x1, int y1, int size) {
    for (int x2 = 0; x2 < y1; x2++) {
      for (int y2 = 0; y2 < x1; y2++) {
        CellType cellType = controller.getActivePuzzle().getCellType(x2, y2);
        // switch case to match the cell type!
        switch (cellType) {
          case CORRIDOR:
            configureCorridorCell(board, x2, y2, size);
            break;
          case CLUE:
            configureClueCell(board, x2, y2, size);
            break;
          case WALL:
            configureWallCell(board, size);
            break;
        }
      }
    }
  }

  private void configureCorridorCell(TilePane board, int x, int y, int size) {
    Button button = new Button();
    button.setOnAction((ActionEvent event) -> controller.clickCell(x, y));
    if (controller.isLamp(x, y)) {
      button.getStyleClass().add(controller.isLampIllegal(x, y) ? "redlamp" : "yellowlamp");
    } else if (controller.isLit(x, y)) {
      button.getStyleClass().add("yellowsquare");
    } else {
      button.getStyleClass().add("nofill");
    }
    button.setPrefSize(size, size);
    board.getChildren().add(button);
  }

  private void configureClueCell(TilePane board, int x, int y, int size) {
    String clue = String.valueOf(controller.getActivePuzzle().getClue(x, y));
    Label label = new Label(clue);
    // is the clue is statisfied, change the square to green or, red if not
    if (controller.isClueSatisfied(x, y)) {
      label.getStyleClass().add("satisfied");
    } else {
      label.getStyleClass().add("not_satisfied");
    }
    label.setPrefSize(size, size);
    board.getChildren().add(label);
  }

  private void configureWallCell(TilePane board, int s) {
    // creates all walls a black squares
    Label label = new Label();
    label.getStyleClass().add("black_square");
    label.setPrefSize(s, s);
    board.getChildren().add(label);
  }
}
