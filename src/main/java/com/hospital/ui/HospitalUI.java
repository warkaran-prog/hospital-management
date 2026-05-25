package com.hospital.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HospitalUI extends Application {

    @Override
    public void start(Stage stage) {

        // Labels
        Label nameL = new Label("Name:");
        Label ageL = new Label("Age:");
        Label genderL = new Label("Gender:");
        Label bpL = new Label("Blood Pressure:");
        Label hrL = new Label("Heart Rate:");
        Label tempL = new Label("Temperature:");
        Label typeL = new Label("Type:");
        Label daysL = new Label("Days:");

        // TextFields
        TextField nameF = new TextField();
        TextField ageF = new TextField();
        TextField genderF = new TextField();
        TextField bpF = new TextField();
        TextField hrF = new TextField();
        TextField tempF = new TextField();
        TextField typeF = new TextField();
        TextField daysF = new TextField();

        // Button
        Button submit = new Button("Admit Patient");

        // Button Action
        submit.setOnAction(e -> {

            try {

                String json = "{"
                        + "\"name\":\"" + nameF.getText() + "\","
                        + "\"age\":" + ageF.getText() + ","
                        + "\"gender\":\"" + genderF.getText() + "\","
                        + "\"bloodPressure\":\"" + bpF.getText() + "\","
                        + "\"heartRate\":\"" + hrF.getText() + "\","
                        + "\"temperature\":\"" + tempF.getText() + "\","
                        + "\"type\":\"" + typeF.getText() + "\","
                        + "\"days\":" + daysF.getText()
                        + "}";

                URL url = new URL("http://localhost:8080/patients");

                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                conn.setRequestProperty(
                        "Content-Type",
                        "application/json"
                );

                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();

                os.write(json.getBytes());

                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == 200 || responseCode == 201) {

                    System.out.println(
                            "Patient Added Successfully!"
                    );

                } else {

                    System.out.println(
                            "Failed to Add Patient"
                    );
                }

                conn.disconnect();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // Layout
        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(nameL, 0, 0);
        grid.add(nameF, 1, 0);

        grid.add(ageL, 0, 1);
        grid.add(ageF, 1, 1);

        grid.add(genderL, 0, 2);
        grid.add(genderF, 1, 2);

        grid.add(bpL, 0, 3);
        grid.add(bpF, 1, 3);

        grid.add(hrL, 0, 4);
        grid.add(hrF, 1, 4);

        grid.add(tempL, 0, 5);
        grid.add(tempF, 1, 5);

        grid.add(typeL, 0, 6);
        grid.add(typeF, 1, 6);

        grid.add(daysL, 0, 7);
        grid.add(daysF, 1, 7);

        grid.add(submit, 1, 8);

        // Scene
        Scene scene = new Scene(grid, 500, 450);

        // Stage
        stage.setTitle("Hospital Management System");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}