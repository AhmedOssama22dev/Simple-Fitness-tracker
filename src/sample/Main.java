package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class Main<arr1> extends Application {
    private Label activityChoice = new Label("Click below to\nChoose activities :");
    private Label timeDone = new Label("Enter minutes done:");
    private Label calories = new Label(" Total calories burnt");
    private Label heartRateLabel = new Label(" Total new heart rate");

    private  Label activityRank =new Label(" Activities Rank:\n_________________");
    private  Label firstPlace = new Label("Click the sort button to show Activity rank");
    private  Label secondPlace = new Label("");
    private  Label thirdPlace = new Label("");
    private  Label fourthPlace = new Label("");

    private  Label errorLabel = new Label("Error");

    private String list[] = {"Swimming", "Running", "Kick boxing", "Strength training"};
    private ChoiceBox activity = new ChoiceBox(FXCollections.observableArrayList(list));
    private TextField timeField = new TextField("");

    private String choice= "";

    private Button addRecord = new Button("Add activity:");
    private Button results = new Button(("Show results"));
    private Button sort = new Button("Sort activities ");

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart barChart = new BarChart(xAxis,yAxis);
    private XYChart.Series dataSeries1 = new XYChart.Series();
    Functions fun = new Functions();

    float swimmingCalories, runningCalories, kickBoxingCalories, strengthCalories,totalCaloriesPar,totalCalories=0;
    int time;
    float heartRate,oldHeartRate;



    @Override
        public void start (Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Fitness Tracker");


            GridPane myGrid = new GridPane();
            GridPane myGrid2 = new GridPane();
            //Grid 1 properties
            myGrid.setStyle("-fx-background-color: #666666");
            myGrid.setBackground(new Background(new BackgroundFill(Color.rgb(32,32,32),CornerRadii.EMPTY,Insets.EMPTY)));
            myGrid.setVgap(3.5);
            myGrid.setHgap(2);
            //Adding items to the pane
            myGrid.add(activityChoice, 0, 0, 1, 1);
            myGrid.add(activity, 0, 2, 1, 1);
            myGrid.add(timeDone, 1, 0, 1, 1);
            myGrid.add(timeField, 1, 2, 1, 1);
            myGrid.add(addRecord, 2, 2, 1, 1);
            myGrid.add(sort, 0, 4, 3, 2);
            myGrid.add(results, 0, 7, 1, 1);
            results.setDisable(true);
            //First Grid elements properties
            timeField.setPromptText("Time in minutes");
            activity.setStyle("-fx-background-color: #000000");
            //Labels Properties
            activityChoice.setTextFill(Paint.valueOf("#ffff33"));
            timeDone.setTextFill(Paint.valueOf("#ffff33"));
            //Buttons Properties
            sort.setStyle("-fx-background-color: #000000");
            addRecord.setStyle("-fx-background-color: #000000");
            results.setStyle("-fx-background-color: #000000");
            sort.setTextFill(Paint.valueOf("#ffff33"));
            addRecord.setTextFill(Paint.valueOf("#ffff33"));
            results.setTextFill(Paint.valueOf("#ffff33"));
            sort.setDisable(true);

            //Second Grid
            myGrid2.setBackground(new Background(new BackgroundFill(Color.rgb(32,32,32),CornerRadii.EMPTY,Insets.EMPTY)));
            myGrid2.setVgap(4);
            myGrid2.add(calories, 0, 0, 3, 1);
            myGrid2.add(heartRateLabel, 0, 1, 3, 1);
            myGrid2.add(activityRank,0,2,3,1);
            myGrid2.add(firstPlace,0,3,3,1);
            myGrid2.add(secondPlace,0,4,3,1);
            myGrid2.add(thirdPlace,0,5,3,1);
            myGrid2.add(fourthPlace,0,6,3,1);
            myGrid2.add(barChart,5,2,3,3);
             //Labels properties
            calories.setTextFill(Paint.valueOf("#ffff33"));
            heartRateLabel.setTextFill(Paint.valueOf("#ffff33"));
            activityRank.setTextFill(Paint.valueOf("#ffff33"));
            firstPlace.setTextFill(Paint.valueOf("#ffff33"));
            secondPlace.setTextFill(Paint.valueOf("#ffff33"));
            thirdPlace.setTextFill(Paint.valueOf("#ffff33"));
            fourthPlace.setTextFill(Paint.valueOf("#ffff33"));
        //Fonts adjustments
            activityChoice.setFont(Font.font(14));
            timeDone.setFont(Font.font(14));
            sort.setFont(Font.font(18)); results.setFont(Font.font(18));
            calories.setFont(Font.font(24)); heartRateLabel.setFont(Font.font(24));
            activityRank.setFont(Font.font(26));
            firstPlace.setFont(Font.font(18));secondPlace.setFont(Font.font(18));thirdPlace.setFont(Font.font(18));fourthPlace.setFont(Font.font(18));
        //Bar chart
        xAxis.setLabel("Activity");
        yAxis.setLabel("Calories");
        dataSeries1.setName("Activity");


            primaryStage.setScene(new Scene(myGrid, 525, 185));
            primaryStage.show();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(myGrid2,850,600));
            //For error message
             StackPane errorPane = new StackPane();
             Stage errorStage = new Stage();
             errorStage.setScene(new Scene(errorPane,250,150));

        stage2.setTitle("Fitness Tracker");


            results.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage2.show();
                }
            });
            activity.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    choice=list[newValue.intValue()];

                }
            });
            addRecord.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try{
                    sort.setDisable(false);
                  time =Integer.parseInt(timeField.getText());
                  //Calc total calories
                  totalCalories +=  fun.choiceDependentCaloriesCalculations(choice,time,swimmingCalories,runningCalories,kickBoxingCalories,strengthCalories,totalCaloriesPar);
                    calories.setText("Total Calories burnt = "+Math.round(totalCalories)+" cal");

                    //Calc total heart rate
                   heartRate= fun.choiceDependentHeartCalculations(choice,time);
                   heartRateLabel.setText("Total heart rate = "+heartRate+"beat/min");}
                    catch (Exception e)
                    {
                        errorLabel.setText("Please enter only integer minutes or\nFill the field :D");
                        errorPane.getChildren().add(errorLabel);
                        errorStage.show();
                    }

                }
            });
            //TextField accept only numerical values:
            timeField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                        timeField.setText(oldValue);
                    }
                    }
                });
            sort.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    float[] arrHeart;arrHeart =new float[]{fun.swimIncrease,fun.runIncrease,fun.kickIncrease,fun.strengthIncrease};
                    float[] arr; arr= new float[]{fun.swimCal, fun.runCal,fun.kickCal,fun.strengthCal};
                    Arrays.sort(arr);
                    fun.sortCalories(arr,arrHeart,firstPlace,secondPlace,thirdPlace,fourthPlace);
                    dataSeries1.getData().add(new XYChart.Data("Swimming",fun.swimCal));
                    dataSeries1.getData().add(new XYChart.Data("Running",fun.runCal));
                    dataSeries1.getData().add(new XYChart.Data("KickBoxing",fun.kickCal));
                    dataSeries1.getData().add(new XYChart.Data("Strength",fun.strengthCal));
                    barChart.getData().add(dataSeries1);
                    results.setDisable(false);

                }
            });

        }
        public static void main (String[]args){
            launch(args);
        }
    }

