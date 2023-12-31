import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EpsilonGreedyAgent implements Agent {

    Random rand;
    double epsilon;
    private Map<String, Integer> counts;
    private Map<String, Double> avgReward;
    private String lastChoice = "";

    public EpsilonGreedyAgent(double epsilon) {
        rand = new Random();
        this.epsilon = epsilon;
        counts = new HashMap<>();
        avgReward = new HashMap<>();
    }

    @Override
    public String present(String context, List<String> choices) {
        double draw = rand.nextDouble();
        int sumcounts = 0;
        String argmax = choices.get(0);
        double vmax = avgReward.getOrDefault(argmax, 0.0);
        for (String s : choices) {
            int c = counts.getOrDefault(s, 0);
            double v = avgReward.getOrDefault(s, 0.0);
            sumcounts += c;
            if (v > vmax) {
                argmax = s;
                vmax = v;
            }

        }
        if (draw < epsilon || sumcounts < 3) {
            int number = rand.nextInt(choices.size());
            lastChoice = choices.get(number);
            return lastChoice;
        } else {
            lastChoice = argmax;
            return argmax;
        }
    }

    @Override
    public void feedback(double score) {
        // update reward and count for last choice
        int oldCount = this.counts.getOrDefault(this.lastChoice, 0);
        double oldReward = this.avgReward.getOrDefault(this.lastChoice, 0.0);
        this.avgReward.put(this.lastChoice, (oldCount * oldReward + score) / (oldCount + 1));
        this.counts.put(this.lastChoice, oldCount + 1);
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }
}
