/**
 * CopyRight © 2018 LOTFI BOUKHEMERRA - All Rights Reserved - developed by LOTFI BOUKHEMERRA.
 * @refrences: 
 */
package run;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 *
 * @author BOUKHEMERRA Lotfi.
 * repository https://github.com/LotfiBoukhemerra
 */
public class Waiting extends JFXPanel {

    private static final int WIDTH_ = 373;
    private static final int HEIGHT_ = 743;
    
    public Waiting() {
        initFX(this);
    }
    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
    private static Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);
        Rectangle r = new Rectangle(WIDTH_, HEIGHT_);
        r.setFill(Color.WHITE);
        r.setOpacity(0.6);
        root.getChildren().addAll(r);
        Text t = new Text("Waiting for player 2 . . .");
        Text t1 = new Text("click on SPACE if you want to back to menu Game");
        ProgressIndicator p = new ProgressIndicator();
        p.setLayoutX((WIDTH_ -50) / 2 );
        p.setLayoutY(HEIGHT_ / 2 +33);
        t.setX(WIDTH_ / 2 - 120);
        t.setY(HEIGHT_ / 2 -50);
        t1.setX(61);
        t1.setY(HEIGHT_ - 50);
        t.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 22));
        t1.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 11));
        t.setFill(new Color(107 / 255.0, 162 / 255.0, 252 / 255.0, 1.0));
        t1.setFill(new Color(001 / 225.0, 162 / 255.0, 252 / 258.0, 1.0));
        root.getChildren().addAll(t,t1, p);
        return (scene);
    }
}
