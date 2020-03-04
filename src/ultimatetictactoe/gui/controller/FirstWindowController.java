/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ultimatetictactoe.bll.bot.IBot;

/**
 * FXML Controller class
 *
 * @author Rocío
 */
public class FirstWindowController implements Initializable {

    @FXML
    private Label lbl_title;
    @FXML
    private JFXButton btnStart;
    @FXML
    private JFXTextField txtHumanNameLeft;
    @FXML
    private JFXTextField txtHumanNameRight;
    @FXML
    private JFXComboBox<IBot> comboBotsLeft;
    @FXML
    private JFXComboBox<IBot> comboBotsRight;
    @FXML
    private JFXRadioButton radioLeftHuman;
    @FXML
    private JFXRadioButton radioRightHuman;
    @FXML
    private JFXRadioButton radioLeftAI;
    @FXML
    private JFXRadioButton radioRightAI;
    @FXML
    private ToggleGroup toggleLeft;
    @FXML
    private ToggleGroup toggleRight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void clickStart(ActionEvent actionEvent) throws IOException {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/gui/view/FirstWindow.fxml"));
        Parent root = fxLoader.load();

        MainController controller = ((MainController) fxLoader.getController());

        if (toggleLeft.getSelectedToggle().equals(radioLeftAI)
                && toggleRight.getSelectedToggle().equals(radioRightAI)) {
            controller.setupGame(comboBotsLeft.getSelectionModel().getSelectedItem(), comboBotsRight.getSelectionModel().getSelectedItem());
            primaryStage.setTitle(
                    comboBotsLeft.getSelectionModel().getSelectedItem().getBotName()
                    + " vs "
                    + comboBotsRight.getSelectionModel().getSelectedItem().getBotName());
        } else if (toggleLeft.getSelectedToggle().equals(radioLeftHuman)
                && toggleRight.getSelectedToggle().equals(radioRightAI)) {
            controller.setupGame(txtHumanNameLeft.getText(), comboBotsRight.getSelectionModel().getSelectedItem());
            primaryStage.setTitle(
                    txtHumanNameLeft.getText()
                    + " vs "
                    + comboBotsRight.getSelectionModel().getSelectedItem().getBotName());
        } else if (toggleLeft.getSelectedToggle().equals(radioLeftAI)
                && toggleRight.getSelectedToggle().equals(radioRightHuman)) {
            controller.setupGame(comboBotsLeft.getSelectionModel().getSelectedItem(), txtHumanNameRight.getText());
            primaryStage.setTitle(
                    comboBotsLeft.getSelectionModel().getSelectedItem().getBotName()
                    + " vs "
                    + txtHumanNameRight.getText());
        } else if (toggleLeft.getSelectedToggle().equals(radioLeftHuman)
                && toggleRight.getSelectedToggle().equals(radioRightHuman)) {
            controller.setupGame(txtHumanNameLeft.getText(), txtHumanNameRight.getText());
            primaryStage.setTitle(
                    txtHumanNameLeft.getText()
                    + " vs "
                    + txtHumanNameRight.getText());
        }
        controller.startGame();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    private class CustomIBotListCell extends ListCell<IBot> {

        @Override
        protected void updateItem(IBot item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && item != null) {
                setText(item.getBotName());
            } else {
                setText(null);
            }
        }
    }

}
