public class Main {

    public static void main(String[] args) {
        Bandit b = new SimpleBandit();
        Agent a = new RandomAgent();
        /*b.run(a, 1000);
        System.out.println("Random Agent / simple bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new MovieBandit();
        a = new RandomAgent();
        b.run(a, 0); // zero = ignore n
        System.out.println(
                "Random Agent / movie bandit: score=" + b.getScore() + " regret per turn=" + b.getTotalRegret() / b.getCount());

        b = new MovieBandit();
        a = new EpsilonGreedyAgent(0.1);
        b.run(a, 0); // 0 = ignore argument
        System.out.println("EpsilonGreedy Agent / movie bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new SimpleBandit();
        a = new EpsilonGreedyAgent(0.1);
        b.run(a, 1000);
        System.out.println("EpsilonGreedy Agent / simple bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new SimpleBandit();
        a = new EpsilonDecreasingAgent(0.1, 0.9);
        b.run(a, 1000);
        System.out.println("EpsilonDecreasing Agent / simple bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new SimpleBandit();
        a = new EpsilonGreedyAgent(0.1);
        b.run(a, 1000000);
        System.out.println("EpsilonGreedy Agent / simple bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());*/

        b = new SimpleBandit();
        a = new EpsilonDecreasingAgent(0.1, 0.9);
        b.run(a, 100);
        System.out.println("EpsilonDecreasing Agent / simple bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new MovieBandit();
        a = new ContextualEpsilonGreedyAgent(0.1);
        b.run(a, 100);
        System.out.println("Contextual EpsilonGreedy Agent / movie bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

        b = new MovieBandit();
        a = new ContextualEpsilonDecreasingAgent(0.1, 0.9);
        b.run(a, 100);
        System.out.println("Contextual EpsilonDecreasing Agent / movie bandit: score=" + b.getScore() + " regret per turn="
                + b.getTotalRegret() / b.getCount());

    }

}
