package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static sample.Adminstrator.needtime1;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text eventsofdays;

    @FXML
    private Button Events;

    @FXML
    private Button Join;

    @FXML
    private Button Close;

    @FXML
    private Button RollUp;

    @FXML
    private ImageView Setting;

    @FXML
    protected static ImageView anime_Photo;

    @FXML
    protected static Text anime_Descript;

    @FXML
    protected static Text anime_Name;

    @FXML
    void initialize(){
        Close.setOnAction(event -> {
            Stage stage =(Stage) Close.getScene().getWindow();
            stage.close();
        });

        RollUp.setOnAction(event -> {
            Stage stage = null;

            stage = (Stage) RollUp.getScene().getWindow();
            stage.setIconified(true);
        });

        Join.setOnAction(event -> {
                Stage stage1 =(Stage) Join.getScene().getWindow();
                stage1.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Join.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Anime Online");
                stage.getIcons().add(new Image("/sample/Assets/iCON.jpg"));
                stage.show();
        });

            Calendar time = new GregorianCalendar();
            if(time.get(Calendar.DAY_OF_MONTH)==8)
                eventsofdays.setText("8 мая ООН  празднует первый из двух дней памяти и примирения, посвящённых памяти жертв Второй мировой войны.");
            if(time.get(Calendar.DAY_OF_MONTH)==9)
                eventsofdays.setText("9 мая День Побе́ды — праздник победы Красной Армии и советского народа над нацистской Германией в Великой Отечественной войне 1941—1945 годов.");

    }


}
