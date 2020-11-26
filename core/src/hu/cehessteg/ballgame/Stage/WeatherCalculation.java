package hu.cehessteg.ballgame.Stage;

public class WeatherCalculation {
    float time;

    public WeatherCalculation(float time) {
        this.time = time;
    }

    public String getTimeToString()
    {
        return "" + getH() + ":" + getM() + ":" + getS() + "." + getMs();
    }

    public float getTime()
    {
        return time;
    }

    public float getRain(){
        return (float)(Math.sin(time / 30000) < 0 ? 0 : Math.sin(time / 30000));
    }

    public int getS(){
        return ((int)time) % 60;
    }

    public int getH(){
        return (int)time / 3600;
    }

    public int getM(){
        return ((int)time / 60) % 60;
    }

    public int getMs(){
        return ((int)(time * 1000)) % 1000;
    }

    public void step(float delta) {
        time += delta;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
