package sample;

public class Calculations {
    private int time; //in minutes
    private float oldHeartRate =80f ; //Per minute
    private float newHeartRate;
    private float caloriesBurnt = 0f;

    public void setCaloriesBurnt(float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public float getCaloriesBurnt() {
        return caloriesBurnt;
    }


    public void setNewHeartRate(float newHeartRate) {
        this.newHeartRate = newHeartRate;
    }

    public float getNewHeartRate() {
        return newHeartRate;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public float caloriesCounter(int time,int burn_rate)
    {
        setTime(time);
        float totalCalories = this.time * burn_rate;
        setCaloriesBurnt(totalCalories);
        float caloriesBurnt= getCaloriesBurnt();
        return  caloriesBurnt;
    }
    public  float totalHeartRate(int time,float rateIncrease)
    {
        setTime(time);
        float totalHeartRate =  (this.oldHeartRate + (oldHeartRate*this.time*rateIncrease));
        setNewHeartRate(totalHeartRate);
        float newHeartRate = getNewHeartRate();
        return newHeartRate;
    }

    public float getOldHeartRate() {
        return oldHeartRate;
    }

    public void setOldHeartRate(float oldHeartRate) {
        this.oldHeartRate = oldHeartRate;
    }
}
