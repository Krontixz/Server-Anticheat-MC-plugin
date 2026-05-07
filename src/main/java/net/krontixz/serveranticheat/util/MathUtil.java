package net.krontixz.serveranticheat.util;

public class MathUtil {
    public static double getDistance(double x1, double z1, double x2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(z1 - z2, 2));
    }

    public static double getVerticalDistance(double y1, double y2) {
        return Math.abs(y1 - y2);
    }
}
