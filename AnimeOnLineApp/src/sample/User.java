package sample;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import javafx.scene.control.TextArea;

import static sample.Adminstrator.file;
import static sample.Adminstrator.needtime;
import static sample.Events.EVENTS;
import static sample.Join.nicknameText;

public class User{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_play;

    @FXML
    private TextArea messageComon;

    @FXML
    private TextArea message;

    @FXML
    private Button button_add;

    @FXML
    private Text eventsofday;

    @FXML
    private Button Close;

    @FXML
    private Button RollUp;

    MediaPlayer player;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider timeSlider;

    @FXML
    void initialize(){
        try {
            Media media = new Media(file.toURI().toURL().toString());

            player = new MediaPlayer(media);

            mediaView.setMediaPlayer(player);

            Image imageOk = new Image(getClass().getResourceAsStream("Assets\\3.png"));
            button_play.graphicProperty().setValue(new ImageView(imageOk));

            player.setOnReady(()->{
                timeSlider.setMin(0);
                timeSlider.setMax(player.getMedia().getDuration().toSeconds());
                timeSlider.setValue(0);
            });

            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    Duration time = player.getCurrentTime();

                    timeSlider.setValue(time.toSeconds());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        Close.setOnAction(event -> {
            Stage stage =(Stage) Close.getScene().getWindow();
            stage.close();
        });

        RollUp.setOnAction(event -> {
            Stage stage = null;

            stage = (Stage) RollUp.getScene().getWindow();
            stage.setIconified(true);
        });

        button_play.setOnAction(event -> {
            double val;
            Calendar now = new GregorianCalendar();
            val = ((now.get(Calendar.HOUR) - needtime.get(Calendar.HOUR)) * 3600 + (now.get(Calendar.MINUTE) - needtime.get(Calendar.MINUTE)) * 60 + now.get(Calendar.SECOND)-needtime.get(Calendar.SECOND)) * 1000;
            new Thread(() ->player.seek(new Duration(val))).start();

            player.play();
            button_play.setVisible(false);
        });

        button_add.setOnAction(event -> {
            String ourMessage = message.getText();
            messageComon.setWrapText(true);
            message.setText("");
            messageComon.appendText(nicknameText+":"+ourMessage+"\n");
        });

        Calendar time = new GregorianCalendar();
        eventsofday.setText(EVENTS(time));
    }

}

