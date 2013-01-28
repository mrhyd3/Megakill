package hyde.megakill.util;

public class Random {
    public static java.util.Random random = new java.util.Random(System.currentTimeMillis());

    public static int getRandomInteger(int maximum) {
        return random.nextInt(maximum);
    }
}
