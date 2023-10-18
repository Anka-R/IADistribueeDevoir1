import java.util.List;
import java.util.Random;

public class RandomAgent implements Agent {

    Random rand;

    public RandomAgent() {
        rand = new Random();
    }

    @Override
    public String present(String context, List<String> choices) {
        int number = rand.nextInt(choices.size());
        return choices.get(number);
    }

    @Override
    public void feedback(double score) {
        //do nothing
    }

}
