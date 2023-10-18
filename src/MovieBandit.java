import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieBandit extends Bandit {

    private Scanner reader;

    public MovieBandit() {
        try {
            reader = new Scanner(new File(Utils.TRAINING_FILE_PATH));
            //reader.useDelimiter(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(Agent agent, int n) {

        while(reader.hasNext() && (n==0 || count < n)) {
            String line = reader.nextLine();
            String[] tokens = line.split(",");
            String uid = tokens[0];
            String[] choices = tokens[1].split("\\|");
            String[] valueList = tokens[2].split("\\|");
            Map<String, Double> valMap = new HashMap<>();

            double best = 0; // get best choice

            for (int i=0; i<choices.length; i++) {
                double v = Double.valueOf(valueList[i]);

                if (v>best) {
                    best = v;
                }

                valMap.put(choices[i],v);
            }

            String agentChoice = agent.present(uid, Arrays.asList(choices));
            double score = valMap.get(agentChoice);
            agent.feedback(score);
            totalScore += score;
            totalRegret += best - score;
            count +=1;

        }


    }

}
