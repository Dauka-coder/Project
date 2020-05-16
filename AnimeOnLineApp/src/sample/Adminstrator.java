package sample;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import static sample.Events.EVENTS;
import static sample.Join.count;
import static sample.Controller.counter;

public class Adminstrator{
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
    private TextArea anime_Descript;

    @FXML
    private Text eventsofthedays;

    @FXML
    private Button add_video;

    @FXML
    private Button Back;
    protected static Date needtime1;
    protected static String needCode;
    protected static File file1;
    protected static File file;
    protected static Calendar needtime;

    protected static String nameOfAnime = "Сегодня нет показов";
    protected static String DescriptOfAnime;
    protected static String timeOfAnime;

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
            try{
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

        add_video.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            file = chooser.showOpenDialog(null);
        });

        add_photo.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            file1 = chooser.showOpenDialog(null);
        });

        Add.setOnAction(event -> {
            String needday = need_day.getText().trim();
            String needmonth = need_month.getText().trim();
            String needyears = need_years.getText().trim();
            String needhours = need_hours.getText().trim();
            String needminutes = need_minutes.getText().trim();
            needCode = need_code.getText().trim();

            //need_code.setText(needCode);
            //need_day.setText(needday);
            //need_month.setText(needmonth);
            //need_years.setText(needyears);
            //need_hours.setText(needhours);
            //need_minutes.setText(needminutes);

            nameOfAnime = anime_name.getText();
            DescriptOfAnime = anime_Descript.getText();
            timeOfAnime = (needday+"."+needmonth+"."+needyears+"/"+needhours+":"+needminutes);

            //anime_name.setText(nameOfAnime);
            //anime_Descript.setText(DescriptOfAnime);

            int needDay = Integer.parseInt(needday.replaceAll("[^0-9]", ""));
            int needMonth = Integer.parseInt(needmonth.replaceAll("[^0-9]", ""));
            int needYears = Integer.parseInt(needyears.replaceAll("[^0-9]", ""));
            int needHours = Integer.parseInt(needhours.replaceAll("[^0-9]", ""));
            int needMinutes = Integer.parseInt(needminutes.replaceAll("[^0-9]", ""));

            needtime = new GregorianCalendar();
            needtime.set(Calendar.YEAR, needYears);
            needtime.set(Calendar.MONTH, needMonth - 1);
            needtime.set(Calendar.DATE, needDay);
            needtime.set(Calendar.HOUR, needHours - 12);
            needtime.set(Calendar.MINUTE, needMinutes);
            needtime.set(Calendar.SECOND, 0);
            needtime1 = needtime.getTime();

            count = 0;
            counter++;
        });

        anime_Descript.setWrapText(true);
        Calendar time = new GregorianCalendar();
        eventsofthedays.setText(EVENTS(time));
    }
}
