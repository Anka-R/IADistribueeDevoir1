public abstract class Bandit {

    protected double totalScore = 0;
    protected double totalRegret = 0;
    protected int count = 0;

    public double getScore() {

        return totalScore;
    }

    public double getCount() {
        return count;
    }

    public double getTotalRegret() {
        return totalRegret;
    }

    public abstract void run(Agent a, int n);

}
