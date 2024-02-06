package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {
  private final AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  public Parent render() {
    // adds Akari title to top of application
    VBox pane = new VBox();
    pane.getChildren().add(new Label("Akari"));
    pane.getStyleClass().add("intro");
    pane.setAlignment(Pos.TOP_CENTER);
    return pane;
  }
}
