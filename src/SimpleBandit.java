import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimpleBandit extends Bandit {

    private Map<String, Map<Integer, Double>> outcomes;
    private Random rand;

    public SimpleBandit() {
        outcomes = new HashMap<>();
        outcomes.put("A", Map.of(1, 0.7, 4, 0.1, 0, 0.2));
        outcomes.put("B", Map.of(6, 0.25, 3, 0.25, 0, 0.5));
        outcomes.put("C", Map.of(9, 0.3, 3, 0.1, 1, 0.15, 0, 0.65));
        outcomes.put("D", Map.of(11, 0.04, 2, 0.16, 1, 0.15, 0, 0.65));

        rand = new Random();
    }

    public void run(Agent agent, int n) {
        count = 0;
        totalScore = 0;
        List<String> choices = new ArrayList<>(outcomes.keySet());

        double maxExpectation = 0;

        for (String o : choices) {
            Map<Integer, Double> vals = outcomes.get(o);
            double sum = 0;
            for (int k : vals.keySet()) {
                sum += k * vals.get(k);
            }
            if (sum > maxExpectation)
                maxExpectation = sum;
        }

        for (int i = 0; i < n; i++) {
            count++;
            String choice = agent.present("0", choices); // context is not used
            Map<Integer, Double> possibleScores = outcomes.get(choice);
            int score = chooseRandomWithWeights(possibleScores);
            agent.feedback(score);
            totalScore += score;
        }

        totalRegret = count * maxExpectation - totalScore;

    }

    private int chooseRandomWithWeights(Map<Integer, Double> choices) {
        double randomValue = rand.nextDouble();
        double cumulativeWeight = 0.0;

        for (Integer o : choices.keySet()) {
            cumulativeWeight += choices.get(o); // associated probability
            if (randomValue <= cumulativeWeight) {
                return o;
            }
        }
        return 0; // should not happen because cumulative weight ends up at 1.
    }

}
