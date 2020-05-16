package sample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import static sample.Adminstrator.*;
import static sample.Events.EVENTS;

public class Controller{
    @FXML
    private Text eventsofdays;

    @FXML
    private Button Join;

    @FXML
    private Button Close;

    @FXML
    private Button RollUp;

    @FXML
    private ImageView Setting;

    @FXML
    private TextArea anime_Name;

    @FXML
    private TextArea anime_Descript;

    @FXML
    private TextArea anime_time;

    @FXML
    private ImageView anime_Photo;

    @FXML
    private Button viewImage;
    protected static int counter = 0;

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

        if(counter==0){
            viewImage.setVisible(false);
        }
        viewImage.isPressed();
        viewImage.setOnAction(event -> {
            Image imageObject = null;
            try {
                imageObject = new Image(file1.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            anime_Photo.setImage(imageObject);
            viewImage.setVisible(false);
        });

        //anime_Photo.

        anime_Descript.setWrapText(true);
        anime_Name.setText(nameOfAnime);
        anime_Descript.setText(DescriptOfAnime);
        anime_time.setText(timeOfAnime);

        Calendar time = new GregorianCalendar();
        eventsofdays.setText(EVENTS(time));
    }
}
