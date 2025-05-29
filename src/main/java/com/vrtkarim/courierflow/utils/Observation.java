package com.vrtkarim.courierflow.utils;

import com.graphhopper.util.shapes.GHPoint;

import java.util.Objects;

public class Observation {
    private GHPoint point;
    private double accumulatedLinearDistanceToPrevious;

    public Observation(GHPoint p) {
        this.point = p;
    }

    public GHPoint getPoint() {
        return point;
    }

    public double getAccumulatedLinearDistanceToPrevious() {
        return accumulatedLinearDistanceToPrevious;
    }

    public void setAccumulatedLinearDistanceToPrevious(double accumulatedLinearDistanceToPrevious) {
        this.accumulatedLinearDistanceToPrevious = accumulatedLinearDistanceToPrevious;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "point=" + point +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observation that = (Observation) o;
        return Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}