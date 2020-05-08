package sample;

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
import javafx.stage.Stage;
import sample.animations.Shake;

import static sample.Adminstrator.needCode;
import static sample.Adminstrator.needtime1;

public class Join {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Join_nickname;

    @FXML
    private TextField Join_code;

    @FXML
    private Button Join_button;

    @FXML
    void initialize() {
        Join_button.setOnAction(event -> {
            int count = 0;

            String nicknameText = Join_nickname.getText().trim();
            String codeText = Join_code.getText().trim();
            String code = "Maks";


            if(nicknameText.equals("ADMIN") && codeText.equals("ANIMEONLINE")) {
                Stage stage1 = (Stage) Join_button.getScene().getWindow();
                stage1.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Adminstrator.fxml"));

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
                count = 1;
            }

            if(count==0){
                Calendar time = new GregorianCalendar();
                Date time1 = time.getTime();
                System.out.println(needtime1);

                if(compare(time1) == 1) {
                    if (nicknameText.equals("") || !codeText.equals(needCode)) {
                        Shake codeAnimation = new Shake(Join_code);
                        Shake nicknameAnimation = new Shake(Join_nickname);
                        codeAnimation.playAnim();
                        nicknameAnimation.playAnim(); }

                    if (!nicknameText.equals("") || codeText.equals(needCode)) {
                        Stage stage1 = (Stage) Join_button.getScene().getWindow();
                        stage1.close();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/User.fxml"));

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
                    }
                }

                if (compare(time1)==0) {
                    Shake codeAnimation = new Shake(Join_code);
                    Shake nicknameAnimation = new Shake(Join_nickname);
                    codeAnimation.playAnim();
                    nicknameAnimation.playAnim();
                }
            }
        });
    }

    public static int compare(Date time){
        int a = 0;
        if (time.after(needtime1)) {
            a = 1;}
        return a;
    }
}
