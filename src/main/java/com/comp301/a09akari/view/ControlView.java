package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox pane = new VBox();
    pane.setAlignment(Pos.CENTER);
    // creates next previous, and reset buttons!
    NextView puzzleMoveControls = new NextView(controller);
    ResetView puzzleSolutionControls = new ResetView(controller);
    // adds + renders them
    pane.getChildren().add(puzzleMoveControls.render());
    pane.getChildren().add(puzzleSolutionControls.render());
    return pane;
  }
}
