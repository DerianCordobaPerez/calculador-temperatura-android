package com.pplam.practica_01;

public class Temperature {
    private double temperature;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public String toCelsius() {
        return Double.toString(((this.temperature - 32)*5)/9);
    }

    public String toFarenheit() {
        return Double.toString(((9.0/5.0) * this.temperature) + 32);
    }
}
