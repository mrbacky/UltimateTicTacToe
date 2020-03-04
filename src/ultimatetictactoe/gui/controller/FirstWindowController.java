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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ultimatetictactoe.bll.bot.IBot;
import ultimatetictactoe.dal.DynamicBotClassHandler;
import static ultimatetictactoe.dal.DynamicBotClassHandler.loadBotList;

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
    @FXML
    private JFXButton btnPlayerRed;
    @FXML
    private JFXButton btnPlayerBlue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Font.loadFont(getClass().getResourceAsStream("fonts/Ponderosa.ttf"), 14);
        
        ObservableList<IBot> bots = FXCollections.observableArrayList();
        try {
            DynamicBotClassHandler.writeBotsToTextFile();
            bots = loadBotList();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FirstWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboBotsLeft.setButtonCell(new CustomIBotListCell());
        comboBotsLeft.setCellFactory(p -> new CustomIBotListCell());
        comboBotsLeft.setItems(bots);
        comboBotsRight.setButtonCell(new CustomIBotListCell());
        comboBotsRight.setCellFactory(p -> new CustomIBotListCell());
        comboBotsRight.setItems(bots);
        btnStart.setDisableVisualFocus(true);

        radioLeftAI.selectedProperty().addListener((observable, oldValue, newValue) -> comboBotsLeft.setDisable(!newValue));
        radioLeftHuman.selectedProperty().addListener((observable, oldValue, newValue) -> txtHumanNameLeft.setDisable(!newValue));
        radioRightAI.selectedProperty().addListener((observable, oldValue, newValue) -> comboBotsRight.setDisable(!newValue));
        radioRightHuman.selectedProperty().addListener((observable, oldValue, newValue) -> txtHumanNameRight.setDisable(!newValue));
        comboBotsLeft.getSelectionModel().selectFirst();
        comboBotsLeft.setDisable(true);
        comboBotsRight.getSelectionModel().selectFirst();
        comboBotsRight.setDisable(true);

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
