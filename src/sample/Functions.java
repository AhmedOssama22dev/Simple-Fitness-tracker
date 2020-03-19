package sample;

import javafx.scene.control.Label;

import static java.util.Arrays.sort;

public class Functions {
    float swimCal =0.0000001f;
    float runCal=0.0000002f;
    float kickCal=0.0000003f;
    float strengthCal = 0.000004f;
    float swimIncrease,runIncrease,kickIncrease,strengthIncrease;

    Calculations calc = new Calculations();

    float choiceDependentCaloriesCalculations(String choice, int time, float swimmingCalories, float runningCalories, float kickBoxingCalories, float strengthCalories, float totalCalories) {

        if (choice == "Swimming") {
            swimmingCalories += calc.caloriesCounter(time, 4);
            swimCal += swimmingCalories;
        }
        if (choice == "Running") {
            runningCalories += calc.caloriesCounter(time, 5);
            runCal += runningCalories;
        }
        if (choice == "Kick boxing") {
            kickBoxingCalories += calc.caloriesCounter(time, 3);
            kickCal += kickBoxingCalories;
        }
        if (choice == "Strength training") {
            strengthCalories += calc.caloriesCounter(time, 5);
            strengthCal += strengthCalories;
        }
        return totalCalories += swimmingCalories + runningCalories + kickBoxingCalories + strengthCalories;


    }

    float choiceDependentHeartCalculations(String choice, int time) {
        float heartRate = 80f;
       float oldHeartRate = calc.getOldHeartRate();
        //calc.setOldHeartRate(oldHeartRate);
        if (choice == "Swimming") {
            heartRate = calc.totalHeartRate(time, 0.002f);
            swimIncrease=heartRate-oldHeartRate;
            calc.setOldHeartRate(heartRate);
            oldHeartRate = calc.getOldHeartRate();
        }
       else if (choice == "Running") {
            heartRate = calc.totalHeartRate(time, 0.003f);
            runIncrease=heartRate-oldHeartRate;
            calc.setOldHeartRate(heartRate);
            oldHeartRate = calc.getOldHeartRate();
        }
       else if (choice == "Kick boxing") {
            heartRate = calc.totalHeartRate(time, 0.005f);
            kickIncrease=heartRate-oldHeartRate;
            calc.setOldHeartRate(heartRate);
            oldHeartRate = calc.getOldHeartRate();
        }
       else if (choice == "Strength training") {
            heartRate = calc.totalHeartRate(time, 0.006f);
            strengthIncrease=heartRate-oldHeartRate;
            calc.setOldHeartRate(heartRate);
            oldHeartRate = calc.getOldHeartRate();
        }

        return heartRate;

    }

    void sortCalories(float[] arr,float[] arrHeart, Label firstPlace, Label secondPlace, Label thirdPlace, Label fourthPlace) {
        for (int i = 3; i >= 0; i--) {
            if (i == 3 ) {
                if (arr[3] ==this.swimCal)
                    firstPlace.setText("[1] Swimming\n total calories =" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[0]*100.00)/100.00+"beat/minute");
               else if (arr[3]  == this.runCal)
                    firstPlace.setText("[1] running\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[1]*100.00)/100.00+"beat/minute");
              else  if (arr[3]==this.kickCal)
                    firstPlace.setText("[1] kick boxing\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[2]*100.00)/100.00+"beat/minute");
               else if(arr[3]==this.strengthCal)
                    firstPlace.setText("[1] strength training\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[3]*100.00)/100.00+"beat/minute");
            }
            if (i == 2) {
                if (arr[2] ==this.swimCal)
                    secondPlace.setText("[2] Swimming\n total calories =" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[0]*100.00)/100.00+"beat/minute");
                else if (arr[2] ==this.runCal)
                    secondPlace.setText("[2] running\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[1]*100.00)/100.00+"beat/minute");
               else if (arr[2] ==this.kickCal)
                    secondPlace.setText("[2] kick boxing\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[2]*100.00)/100.00+"beat/minute");
                else if(arr[2]==this.strengthCal)
                    secondPlace.setText("[2] strength training\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[3]*100.00)/100.00+"beat/minute");
            }
            if (i == 1) {
                if (arr[1]==this.swimCal)
                    thirdPlace.setText("[3] Swimming\n total calories =" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[0]*100.00)/100.00+"beat/minute");
                if (arr[1] ==this.runCal)
                    thirdPlace.setText("[3] Running\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[1]*100.00)/100.00+"beat/minute");
                if (arr[1] ==this.kickCal)
                    thirdPlace.setText("[3] Kick boxing\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[2]*100.00)/100.00+"beat/minute");
                if(arr[1]==this.strengthCal)
                    thirdPlace.setText("[3] strength training\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[3]*100.00)/100.00+"beat/minute");
            }
            if (i == 0) {
                if (arr[0] ==this.swimCal)
                    fourthPlace.setText("[4] Swimming\n total calories =" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[0]*100.00)/100.00+"beat/minute");
                if (arr[0] ==this.runCal)
                    fourthPlace.setText("[4] running\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase"+Math.round(arrHeart[1]*100.00)/100.00+"beat/minute");
                if (arr[0] ==this.kickCal)
                    fourthPlace.setText("[4] kick boxing\n total calories=" + Math.round(arr[i])+"cal\nHeart rate increase:"+Math.round(arrHeart[2]*100.00)/100.00+"beat/minute");
                if(arr[0]==this.strengthCal)
                    fourthPlace.setText("[4] strength training\n total calories=" + Math.round(arr[i])+"cal\nHeart increase:"+Math.round(arrHeart[3]*100.00)/100.00+"beat/minute");
            }
        }
    }
}
