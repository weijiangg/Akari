package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView2 implements FXComponent {
  private final AlternateMvcController controller;

  public MessageView2(AlternateMvcController controller) {
    this.controller = controller;
  }

  public Parent render() {
    VBox pane = new VBox();
    pane.getChildren()
        .add(new Label("Puzzle " + controller.getIndex() + "/ " + controller.getSize()));
    pane.getStyleClass().add("intro");
    pane.setAlignment(Pos.BOTTOM_CENTER);
    return pane;
  }
}
