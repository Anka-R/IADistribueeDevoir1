import java.util.List;

public class ContextualEpsilonDecreasingAgent implements Agent {

    private ContextualEpsilonGreedyAgent epsilonGreedyAgent;

    private double initialEpsilon;

    private double alpha;

    public ContextualEpsilonDecreasingAgent(double initialEpsilon, double alpha) {
        this.initialEpsilon = initialEpsilon;
        this.alpha = alpha;
        this.epsilonGreedyAgent = new ContextualEpsilonGreedyAgent(initialEpsilon);
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
