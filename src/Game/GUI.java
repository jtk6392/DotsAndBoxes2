
package Game;

//Import statements
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.shape.*;
import java.io.FileInputStream;
import java.util.List;

public class GUI extends Application {

    /**
     * Fields
     */
    Button button;
    DropShadow shadow;
    int gap;
    int columns;
    private Object Color;

    public GUI(int gap, int columns) {
        this.gap = gap;
        this.columns = columns;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title of the Window");

        shadow = new DropShadow();

        /**
         * Inputs files and sets them to an image
         */
        FileInputStream istream = new FileInputStream("C:\\Users\\Isodore\\Desktop\\resources\\button.png");
        FileInputStream bstream = new FileInputStream("C:\\Users\\Isodore\\Desktop\\resources\\box.png");
        FileInputStream lstream = new FileInputStream("C:\\Users\\Isodore\\Desktop\\resources\\line.png");
        Image image = new Image(istream);
        Image box = new Image(bstream);
        Image line = new Image(lstream);

        /**
         * Sets the box and line to imageview objects
         */
       //ImageView box2 = new ImageView(box);
        //ImageView line2 = new ImageView(line);

        /**
         * Sets color for grid background
         *
         */
        Color redColor = Color.
        BackgroundFill backgroundFill = new BackgroundFill(redColor, null, null);
        Background background = new Background(backgroundFill);

        Label label = new Label();
        /**
         * Makes Tilepane:Grid and sets its values.
         * Make sure that the Pref columns, # of dots, and distance between them are related.
         */
        TilePane grid = new TilePane();
        grid.setPrefColumns(4);
        grid.setBackground(background);
        grid.resize(490, 540);

        /**
         * Adds hover effect to the dots.
         */
        for (int x = 0; x < 16; x++) {
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> imageView.setEffect(shadow));

            imageView.addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> imageView.setEffect(null));
            grid.getChildren().add(imageView);
        }

        /**
         * Sets the gap between each of
         * the dots both vertically
         * and horizontally.
         */
        grid.setHgap(8);
        grid.setVgap(8);
        Pane pane = new Pane();
        //pane.setBackground(background);
        pane.resize(490, 540);
        //grid.setAlignment(grid, pane.getMaxWidth()/2 );
        /**
         * Adds everything to the pane and relocates as necessary.
         */
        pane.getChildren().add(grid);
        Group root = new Group(box2);
        Group root2 = new Group(line2);
        pane.getChildren().add(root);
        pane.getChildren().add(root2);
        root2.relocate(50, 50);
        //root.relocate(23, 22);
        //x diff 64
        //y diff 64
        root.relocate(23, 86);

        //StackPane layout = new StackPane();
        //layout.getChildren().add(box2);
        //root.getChildren().add(grid);
        //grid.getChildren().add(box2);
        //Scene scene = new Scene(grid, 500, 610);
        //primaryStage.setScene(scene);

        /**
         * Sets up the scene and loads it into the stage(screen)
         * and
         */
        Scene scene2 = new Scene(pane, 490, 540);
        Stage k = new Stage();
        //k.setResizable(false);
        k.setScene((scene2));

        //Shows screen (DON'T CHANGE)
        k.show();
        //primaryStage.show();


    }
}


