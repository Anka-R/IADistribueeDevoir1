import java.util.List;

public class EpsilonDecreasingAgent implements Agent {

    private EpsilonGreedyAgent epsilonGreedyAgent;

    private double initialEpsilon;

    private double alpha;

    public EpsilonDecreasingAgent(double initialEpsilon, double alpha) {
        this.initialEpsilon = initialEpsilon;
        this.alpha = alpha;
        this.epsilonGreedyAgent = new EpsilonGreedyAgent(initialEpsilon);
    }

    @Override
    public String present(String context, List<String> choices) {
        String result = this.epsilonGreedyAgent.present(context, choices);
        epsilonGreedyAgent.setEpsilon(epsilonGreedyAgent.getEpsilon() * this.alpha);
        return result;
    }

    @Override
    public void feedback(double score) {
        this.epsilonGreedyAgent.feedback(score);
    }
}
