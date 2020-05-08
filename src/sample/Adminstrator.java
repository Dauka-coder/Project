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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static sample.Controller.*;

public class Adminstrator {

    private static String nameOfAnime;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Close;

    @FXML
    private Button RollUp;

    @FXML
    private TextField need_day;

    @FXML
    private TextField need_month;

    @FXML
    private TextField need_years;

    @FXML
    private TextField need_hours;

    @FXML
    private TextField need_minutes;

    @FXML
    private TextField need_code;

    @FXML
    private Button Add;

    @FXML
    private Button add_photo;

    @FXML
    private TextField anime_name;

    @FXML
    private TextField anime_descript;

    @FXML
    private Button Back;
    protected static Date needtime1;
    protected static String needCode;
    private String file1;

    @FXML
    void initialize() {
        Close.setOnAction(event -> {
            Stage stage = (Stage) Close.getScene().getWindow();
            stage.close();
        });

        RollUp.setOnAction(event -> {
            Stage stage = null;

            stage = (Stage) RollUp.getScene().getWindow();
            stage.setIconified(true);
        });

        Back.setOnAction(event -> {
            Stage stage1 = (Stage) Back.getScene().getWindow();
            stage1.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));

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
            stage.setResizable(false);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.show();

        });

        Add.setOnAction(event -> {
                String needday = need_day.getText().trim();
                String needmonth = need_month.getText().trim();
                String needyears = need_years.getText().trim();
                String needhours = need_hours.getText().trim();
                String needminutes = need_minutes.getText().trim();
                needCode = need_code.getText().trim();
                nameOfAnime = anime_name.getText();
                String descriptOfAnime = anime_descript.getText();

                anime_Name.setText(nameOfAnime);
                anime_Descript.setText(descriptOfAnime);

                int needDay = Integer.parseInt(needday.replaceAll("[^0-9]", ""));
                int needMonth = Integer.parseInt(needmonth.replaceAll("[^0-9]", ""));
                int needYears = Integer.parseInt(needyears.replaceAll("[^0-9]", ""));
                int needHours = Integer.parseInt(needhours.replaceAll("[^0-9]", ""));
                int needMinutes = Integer.parseInt(needminutes.replaceAll("[^0-9]", ""));
                Image image = new Image(file1);
                anime_Photo = new ImageView(image);

                Calendar needtime = new GregorianCalendar();
                needtime.set(Calendar.YEAR, needYears);
                needtime.set(Calendar.MONTH, needMonth - 1);
                needtime.set(Calendar.DATE, needDay);
                needtime.set(Calendar.HOUR, needHours - 12);
                needtime.set(Calendar.MINUTE, needMinutes);
                needtime.set(Calendar.SECOND, 0);
                needtime1 = needtime.getTime();
                System.out.println(needtime1);
        });

        add_photo.setOnAction(event -> {
            try {
                FileChooser chooser = new FileChooser();
                File file = chooser.showOpenDialog(null);
                file1 = file.toURI().toURL().toString();

            } catch (Exception e){
                e.printStackTrace(); }
        });
    }
}
