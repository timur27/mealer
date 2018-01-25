package com.mealer.mealer.Model;

public class ElementListWOF extends ElementList {
    private double prize;
    private double count;
    private String Unit;

    public ElementListWOF(ElementList e) {
        super(e.getName());
    }

    public ElementListWOF() {

    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
