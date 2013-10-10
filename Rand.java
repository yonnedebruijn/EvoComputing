public abstract class Rand {
    protected static final java.util.Random random = new java.util.Random();
    protected double mean, dev;

    public Rand (double mean, double dev){
           this.mean = mean;
            this.dev = dev;
    }

    public abstract double rnd();

    public static class Cauchy extends Rand {

        public Cauchy(double imean, double idev) {
            super(imean, idev);
        }

        @Override public double rnd() {
            return mean + dev * Math.tan(Math.PI * (random.nextDouble() - .5));
        }
    }
}
