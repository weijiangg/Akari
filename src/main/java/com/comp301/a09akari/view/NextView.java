package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class NextView implements FXComponent {
  private final AlternateMvcController controller;

  public NextView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox panel = createControlPanel();
    // add previous button
    Button prev = createControlButton("<- Previous", controller::clickPrevPuzzle);
    panel.getChildren().add(prev);
    // adds next buytton
    Button next = createControlButton("Next ->", controller::clickNextPuzzle);
    panel.getChildren().add(next);
    return panel;
  }

  private HBox createControlPanel() {
    HBox panel = new HBox();
    panel.setAlignment(Pos.CENTER);
    panel.setSpacing(20);
    return panel;
  }

  private Button createControlButton(String label, Runnable action) {
    // helper funtion to create button :p
    Button button = new Button(label);
    button.setOnAction((ActionEvent event) -> action.run());
    button.getStyleClass().add("buttons");
    return button;
  }
}
