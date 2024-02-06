package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final AlternateMvcController controller;

  // constructor
  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER);

    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    MessageView messageView = new MessageView(controller);
    MessageView2 messageView2 = new MessageView2(controller);
    WinnerView winnerView = new WinnerView(controller);

    layout.getChildren().add(messageView.render());
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(controlView.render());
    layout.getChildren().add(messageView2.render());
    // is the board is correct, show the winner message
    if (controller.isSolved()) {
      layout.getChildren().add(winnerView.render());
    }
    return layout;
  }
}
