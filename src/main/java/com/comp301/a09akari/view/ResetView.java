package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ResetView implements FXComponent {
  private final AlternateMvcController controller;

  public ResetView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox panel = new HBox();
    panel.setSpacing(20);
    panel.setAlignment(Pos.CENTER);
    // adds reset button
    Button reset = new Button("Reset");
    reset.getStyleClass().add("buttons");
    reset.setOnAction((ActionEvent actionEvent) -> controller.clickResetPuzzle());
    panel.getChildren().add(reset);
    // adds random button
    Button random = new Button("Random");
    random.setOnAction((ActionEvent event) -> controller.clickRandPuzzle());
    random.getStyleClass().add("buttons");
    panel.getChildren().add(random);
    return panel;
  }
}
