package com.neo.model;

public class Coordinate {
    private double x;
    private double y;
    private double z;
    private double vx;
    private double vy;
    private double vz;
    private double lt;
    private double rg;
    private double rr;

    public Coordinate(double x, double y, double z, double vx, double vy, double vz, double lt, double rg, double rr) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.lt = lt;
        this.rg = rg;
        this.rr = rr;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getX() {
        return x;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getVz() {
        return vz;
    }

    public double getLt() {
        return lt;
    }

    public double getRg() {
        return rg;
    }

    public double getRr() {
        return rr;
    }
}
