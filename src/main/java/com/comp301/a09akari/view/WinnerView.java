package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WinnerView implements FXComponent {
  private final AlternateMvcController controller;

  public WinnerView(AlternateMvcController controller) {
    this.controller = controller;
  }

  public Parent render() {
    VBox pane = new VBox();
    pane.getChildren().add(new Label("🎉 " + "Congratulations! You Solved the Puzzle!" + " 🎉"));
    pane.getStyleClass().add("winner");
    pane.setAlignment(Pos.BOTTOM_CENTER);
    return pane;
  }
}
