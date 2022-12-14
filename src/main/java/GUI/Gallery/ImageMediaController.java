package GUI.Gallery;

import GUI.Gallery.ImageResizer.ImageDarker;
import GUI.Gallery.SetUp.SettingsLoader;
import GUI.Gallery.Storage.FileViewBase;
import GUI.Gallery.Storage.LinkTransfer;
import GUI.Gallery.Storage.StageConteiner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import static GUI.Gallery.SetUp.SettingsLoader.*;
import static GUI.Gallery.SetupWindowController.rezultbgImageCheck2;
import static GUI.Gallery.Storage.NodeBase.imageViewLinkedHashConteiner;
import static GUI.Gallery.Storage.NodeBase.imageViewTreeConteiner;

public class ImageMediaController implements Initializable {

    public VBox centerVbox;
    public Pane mainPane;
    private ImageView imageView = new ImageView();
    private MediaView mediaView;
    public static ArrayList<MediaPlayer> allPlayers = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    @FXML
    private BorderPane borderPane;
    public static String colorNumber = "";
    public static Image image;
    int num = 0;
    ArrayList<String> listAll = new ArrayList<>();
    public void goToGallery() throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Parent root = FXMLLoader.load(getClass().getResource("Gallery-view.fxml"));
//        StageConteiner.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        StageConteiner.stage.centerOnScreen();
        StageConteiner.stage.getScene().setRoot(root);
//        stage.show();
    }
    public void sentToDB() throws IOException, AWTException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Rectangle2D r = Screen.getPrimary().getBounds();
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle((int) r.getWidth(), (int) r.getHeight());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        image = ImageDarker.darker(screenFullImage, 0.7);

        Parent root = FXMLLoader.load(getClass().getResource("KeyBoard.fxml"));
        StageConteiner.stage.centerOnScreen();
        StageConteiner.stage.getScene().setRoot(root);
    }
    public void LeftPClick() throws FileNotFoundException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
//      listALl ???? ?????????? ?????????????????????? ???? ???????????????? ?? ??????????????????
//      ?????????????? ?????????? ???????????? ????????????, ?????? ?????????????????? ?????????? ???? ????????
//      ???????? ?????????? ??????????, ???? ??????????, ?? ?????? ?????? ??????
        String now = LinkTransfer.link;
        String exLeft = "";
//       num = ?????????? ???????????? ???????????? ???????????????????? ??????????
        for (int i = 0; i < listAll.size(); i++) {
            if (listAll.get(i).equals(now)) {
                num = i;
            }
        }
//        ???????????? ?????????? ?? ?????????? ???????????????? ????, ?????? ?????????? ?????? ?????? ?????????? ?????? ?????? ????????????????!!!
        borderPane.setCenter(null);
        if (num != 0) { // ???? ???? ???????????????? ?? ?????????? ??????????????
            exLeft = listAll.get(num - 1).substring(listAll.get(num - 1).lastIndexOf('.') + 1);
            //?????????? ???????? ????????????????
            if (FileViewBase.imgExtension.contains(exLeft)) {
                Image image = new Image(new FileInputStream(SettingsLoader.getSourseFolder() + "/" + listAll.get(num - 1)));
                imageView.setImage(image);
                imageView.setFitWidth(1200);
                imageView.setFitHeight(800);
                imageView.setId(listAll.get(num - 1));
                borderPane.setCenter(imageView);
                LinkTransfer.link = listAll.get(num - 1);
                borderPane.requestLayout();
            }
            //?????????? ???????? ??????????
            if (FileViewBase.movieExtension.contains(exLeft)) {
                File mediaFile = new File(SettingsLoader.getSourseFolder() + "/" + listAll.get(num - 1));
                Media media = null;
                try {
                    media = new Media(mediaFile.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaView = new MediaView();
                mediaView.setFitWidth(1200);
                mediaView.setFitHeight(800);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaView.setId(listAll.get(num - 1));
                borderPane.setCenter(mediaView);
                LinkTransfer.link = listAll.get(num - 1);
                borderPane.requestLayout();
                mediaPlayer.play();
            }
        } else { // ?? ???? ???????????????? ?? ?????????? ??????????????
            exLeft = listAll.get(listAll.size() - 1).substring(listAll.get(listAll.size() - 1).lastIndexOf('.') + 1);
            //?????????? ???????? ????????????????
            if (FileViewBase.imgExtension.contains(exLeft)) {
                Image image = new Image(new FileInputStream(SettingsLoader.getSourseFolder() + "/" + listAll.get(listAll.size() - 1)));
                imageView.setImage(image);
                imageView.setId(listAll.get(listAll.size() - 1));
                imageView.setFitWidth(1200);
                imageView.setFitHeight(800);
                borderPane.setCenter(imageView);
                LinkTransfer.link = listAll.get(listAll.size() - 1);
                borderPane.requestLayout();
            }
            //?????????? ???????? ??????????
            if (FileViewBase.movieExtension.contains(exLeft)) { //?????????? ???????? ??????????
                File mediaFile = new File(SettingsLoader.getSourseFolder() + "/" + listAll.get(listAll.size() - 1));
                Media media = null;
                try {
                    media = new Media(mediaFile.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaView = new MediaView();
                mediaView.setFitWidth(1200);
                mediaView.setFitHeight(800);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaView.setId(listAll.get(listAll.size() - 1));
                LinkTransfer.link = listAll.get(listAll.size() - 1);
                borderPane.setCenter(mediaView);

                mediaPlayer.play();
            }
        }
    }
    public void RightPClick() throws FileNotFoundException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
//      listALl ???? ?????????? ?????????????????????? ???? ???????????????? ?? ??????????????????
//      ?????????????? ?????????? ???????????? ????????????, ?????? ?????????????????? ?????????? ???? ????????
//      ???????? ?????????? ??????????, ???? ??????????, ?? ?????? ?????? ??????
        String now = LinkTransfer.link;
        String exRight = "";
//      num = ?????????? ???????????? ???????????? ???????????????????? ??????????
        for (int i = 0; i < listAll.size(); i++) {
            if (listAll.get(i).equals(now)) {

                num = i;
            }
        }
//        ???????????? ?????????? ?? ?????????? ???????????????? ????, ?????? ?????????? ?????? ?????? ?????????? ?????? ?????? ????????????????!!!
        borderPane.setCenter(null);

        if (num != listAll.size() - 1) { // ???? ???? ???????????????? ?? ?????????? ??????????????
            exRight = listAll.get(num + 1).substring(listAll.get(num + 1).lastIndexOf('.') + 1);
            //???????????? ???????? ????????????????
            if (FileViewBase.imgExtension.contains(exRight)) {
                Image image = new Image(new FileInputStream(SettingsLoader.getSourseFolder() + "/" + listAll.get(num + 1)));
                imageView.setImage(image);
                imageView.setFitWidth(1200);
                imageView.setFitHeight(800);
                imageView.setId(listAll.get(num + 1));
                borderPane.setCenter(imageView);
                LinkTransfer.link = listAll.get(num + 1);
                borderPane.requestLayout();
            }
            //???????????? ???????? ??????????
            if (FileViewBase.movieExtension.contains(exRight)) {
                File mediaFile = new File(SettingsLoader.getSourseFolder() + "/" + listAll.get(num + 1));
                Media media = null;
                try {
                    media = new Media(mediaFile.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                LinkTransfer.link = listAll.get(num + 1);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaView = new MediaView();
                mediaView.setFitWidth(1200);
                mediaView.setFitHeight(800);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaView.setId(listAll.get(num + 1));
                borderPane.setCenter(mediaView);
                borderPane.requestLayout();
                LinkTransfer.link = listAll.get(num + 1);
                mediaPlayer.play();
            }
        } else { // ?? ???? ???????????????? ?? ?????????? ??????????????
            exRight = listAll.get(0).substring(listAll.get(0).lastIndexOf('.') + 1);
            //???????????? ???????? ????????????????
            if (FileViewBase.imgExtension.contains(exRight)) {
                Image image = new Image(new FileInputStream(SettingsLoader.getSourseFolder() + "/" + listAll.get(0)));
                imageView.setImage(image);
                imageView.setId(listAll.get(0));
                imageView.setFitWidth(1200);
                imageView.setFitHeight(800);
                borderPane.setCenter(imageView);
                LinkTransfer.link = listAll.get(0);
                borderPane.requestLayout();
            }
            //???????????? ???????? ??????????
            if (FileViewBase.movieExtension.contains(exRight)) { //???????????? ???????? ??????????
                File mediaFile = new File(SettingsLoader.getSourseFolder() + "/" + listAll.get(0));
                Media media = null;
                try {
                    media = new Media(mediaFile.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaView = new MediaView();
                mediaView.setFitWidth(1200);
                mediaView.setFitHeight(800);
                mediaView.setId(listAll.get(0));
                mediaView.setMediaPlayer(mediaPlayer);
                borderPane.setCenter(mediaView);
                LinkTransfer.link = listAll.get(0);
                mediaPlayer.play();
            }
        }
    }
    public void NextKeyPress(KeyEvent keyEvent) throws FileNotFoundException {
        if (keyEvent.getCode() == KeyCode.LEFT) {
            LeftPClick();
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            RightPClick();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (rezultbgImageCheck2) {
            mainPane.setBackground(new Background(new BackgroundImage(SetupWindowController.imageForBackGround2,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        if ((colorNumber.length() == 6) | (colorNumber.length() == 7)) {
            mainPane.setStyle("-fx-background: rgb(" + SetupWindowController.RED + "," + SetupWindowController.GREEN + "," + SetupWindowController.BLUE + ");");
        } else {
            mainPane.setStyle("-fx-background: rgb(20,20,30);");
        }

        if ((byAddTime) & (newUp)) {
            ArrayList<ImageView> ivlhcR = new ArrayList<>(imageViewLinkedHashConteiner);
            Collections.reverse(ivlhcR);
            ivlhcR.forEach(i -> {
                listAll.add(i.getId());
            });
        }
        if ((byAddTime) & (newDown)) {
            imageViewLinkedHashConteiner.forEach(i ->
            {
                listAll.add(i.getId());
            });
        }
        if (byName) {
            ArrayList<ImageView> ivlhcR = new ArrayList<>(imageViewTreeConteiner);
            ivlhcR.forEach(i -> {
                listAll.add(i.getId());
            });
        }
        for (int i = 0; i < listAll.size(); i++) {
            if (listAll.get(i).equals(imageView.getId())) {
                num = i;
            }
        }
        if (FileViewBase.imgExtension.contains(LinkTransfer.link.substring(LinkTransfer.link.lastIndexOf('.') + 1))) {
            ImageView imageView = new ImageView();
            borderPane.setCenter(imageView);
            try {
                imageView.setId(LinkTransfer.link);
                imageView.setImage(new Image(new FileInputStream(SettingsLoader.getSourseFolder() + "/" + LinkTransfer.link)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageView.setFitWidth(1200);
            imageView.setFitHeight(800);
        }
        if (FileViewBase.movieExtension.contains(LinkTransfer.link.substring(LinkTransfer.link.lastIndexOf('.') + 1))) {

            File mediaFile = new File(SettingsLoader.getSourseFolder() + "/" + LinkTransfer.link);
            Media media = null;
            try {
                media = new Media(mediaFile.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            allPlayers.add(mediaPlayer);
            mediaView = new MediaView();
            mediaView.setFitWidth(1200);
            mediaView.setFitHeight(800);
            mediaView.setMediaPlayer(mediaPlayer);
            borderPane.setCenter(mediaView);
            mediaPlayer.play();
        }
    }
}
