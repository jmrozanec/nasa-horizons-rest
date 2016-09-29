package com.neo.model;

import javax.persistence.*;

@Entity
@Table(name = "neos")
public class Neo {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    private String name;
    @Column(name = "epoc")
    private int epoch;
    private double a;
    private double e;
    private double i;
    private double w;
    private double node;
    private double m;
    private double qp;
    private double qa;
    private double h;
    private double moid;
    private int ref;
    @Column(name = "class")
    private String classification;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEpoch() {
        return epoch;
    }

    public double getA() {
        return a;
    }

    public double getE() {
        return e;
    }

    public double getI() {
        return i;
    }

    public double getW() {
        return w;
    }

    public double getNode() {
        return node;
    }

    public double getM() {
        return m;
    }

    public double getQp() {
        return qp;
    }

    public double getQa() {
        return qa;
    }

    public double getH() {
        return h;
    }

    public double getMoid() {
        return moid;
    }

    public int getRef() {
        return ref;
    }

    public String getClassification() {
        return classification;
    }
    //TODO add is hazardous
}
