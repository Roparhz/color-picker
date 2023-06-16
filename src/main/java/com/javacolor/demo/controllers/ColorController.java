package com.javacolor.demo.controllers;

import com.javacolor.demo.models.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ColorController implements Initializable {

    private Color color;
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;
    @FXML
    private TextField redField;
    @FXML
    private TextField greenField;
    @FXML
    private TextField blueField;
    @FXML
    private TextField hexaField;
    @FXML
    private Pane colorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialise la couleur
        setupSliderListener(redSlider, redField);
        setupSliderListener(greenSlider, greenField);
        setupSliderListener(blueSlider, blueField);

        //change les champs en fonction du slider
        setupFieldListener(redField, redSlider);
        setupFieldListener(greenField, greenSlider);
        setupFieldListener(blueField, blueSlider);

        //change les champs en fonction du hexa
        hexaField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                color = new Color(newValue);
                redSlider.setValue(color.getRed());
                greenSlider.setValue(color.getGreen());
                blueSlider.setValue(color.getBlue());
                colorPane.setStyle("-fx-background-color: " + color.getHexValue());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void setupSliderListener(Slider slider, TextField field) {
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            field.setText(String.valueOf(newValue.intValue()));
            updateColor();
        });
    }

    private void setupFieldListener(TextField field, Slider slider) {
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                slider.setValue(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void updateColor() {
        try {
            color = new Color(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText()));
            hexaField.setText(color.getHexValue());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
