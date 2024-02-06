package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {

    // add puzzles to the library
    PuzzleLibrary puzzlib = new PuzzleLibraryImpl();
    puzzlib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzlib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzlib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzlib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzlib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    // model view controller :0
    Model model = new ModelImpl(puzzlib);
    ControllerImpl controller = new ControllerImpl(model);
    View view = new View(controller);

    // scene
    Scene scene = new Scene(view.render(), 1100, 1000);
    scene.getStylesheets().add("main.css");

    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    stage.setScene(scene);
    stage.setTitle("Akari -by wei c:");
    stage.show();
  }
}
