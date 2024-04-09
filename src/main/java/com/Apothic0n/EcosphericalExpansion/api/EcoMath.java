package com.Apothic0n.EcosphericalExpansion.api;

public class EcoMath {

    public static float invLerp(float value, float scale, float min, float max) {
        return (value - min) / (max - min)*scale;
    }

    public static double getMiddleDouble(double origin, double target) {
        double min = Math.min(origin, target);
        double max = Math.max(origin, target);
        return ((max-min)/2)+min;
    }

    public static int mid(int x, int y) {
        return x/2 + y/2 + (x%2 + y%2)/2;
    }

    public static int booleanToInt(boolean bool) {
        if (bool == true) {
            return 1;
        } else {
            return 0;
        }
    }

    public static double progressBetweenInts(int min, int max, int value) {
        return (double) value / (max + min);
    }

    public static float getClosenessToNight(double timeOfDay) {
        if (timeOfDay > 0.305 && timeOfDay < 0.694) {
            return 0.0F;
        } else {
            if (timeOfDay < 0.305) {
                return (float) (0.305-timeOfDay)*5;
            } else {
                return (float) (timeOfDay-0.694)*5;
            }
        }
    }
}
