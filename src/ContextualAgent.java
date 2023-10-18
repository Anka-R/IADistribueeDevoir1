import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ContextualAgent implements Agent {

    Random rand;
    double epsilon;
    private final Map<String, Integer> COUNTS;
    private String lastChoice = "";
    private String lastUserId = "";
    private final Map<Integer, List<String>> MOVIE_LIST;
    private final Map<Integer, Map<String, Double>> USERS_GENRES_RATINGS;

    public ContextualAgent(double epsilon) {
        rand = new Random();
        this.epsilon = epsilon;
        COUNTS = new HashMap<>();
        MOVIE_LIST = new HashMap<>();
        USERS_GENRES_RATINGS = new HashMap<>();

        try {
            Scanner reader = new Scanner(new File("D:\\Mobilité\\UQO\\IA distribuée\\Devoir1\\IADistribueeDevoir1\\src\\movies.csv"));
            Map<String, Double> baseGenresRatings = new HashMap<>();

            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] movieData = line.split(",");

                try {
                    int movieId = Integer.parseInt(movieData[0]);
                    List<String> movieGenres = Arrays.asList(movieData[movieData.length-1].split("\\|"));

                    // Setting base genre rating (all genres with a 2.5 rate)
                    for (String genre : movieGenres) {
                        baseGenresRatings.putIfAbsent(genre, 2.5);
                    }

                    MOVIE_LIST.put(movieId, movieGenres);
                } catch (NumberFormatException e) {
                    // First Line
                }
            }

            reader = new Scanner(new File("D:\\Mobilité\\UQO\\IA distribuée\\Devoir1\\IADistribueeDevoir1\\src\\training.txt"));
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] userData = line.split(",");

                int userId = Integer.parseInt(userData[0]);
                USERS_GENRES_RATINGS.put(userId, baseGenresRatings);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String present(String userId, List<String> choices) {
        double draw = rand.nextDouble();
        int c = COUNTS.getOrDefault(userId, 0);

        if (draw < epsilon || c < 2) { // Minimum 2 explorations
            int number = rand.nextInt(choices.size());
            lastUserId = userId;
            lastChoice = choices.get(number);
            return lastChoice;

        } else {

            String argmax = choices.get(0);
            double vmax = 0;

            for (String movieId : choices) {

                List<String> movieGenres = MOVIE_LIST.get(Integer.valueOf(movieId));

                Map<String, Double> genresRating = USERS_GENRES_RATINGS.get(Integer.valueOf(userId));

                LinkedHashMap<String, Double> sortingGenres = Utils.sortByComparatorDouble(genresRating);
                int moviePoints = getMoviePoints(sortingGenres, movieGenres);

                if (vmax < moviePoints) {
                    vmax = moviePoints;
                    argmax = movieId;
                }

            }

            lastUserId = userId;
            lastChoice = argmax;
            return argmax;
        }
    }

    private static int getMoviePoints(LinkedHashMap<String, Double> sortingGenres, List<String> movieGenres) {
        int genreWeight = 0;
        int moviePoints = 0;
        double precedentRating = -1;

        for(Map.Entry<String,Double> entry : sortingGenres.entrySet()) {
            if (movieGenres.contains(entry.getKey())) {
                moviePoints += genreWeight;
            }

            // Weight does not increase if 2 genres have the same ratings
            if (entry.getValue().compareTo(precedentRating) != 0) {
                genreWeight++;
            }
            precedentRating = entry.getValue();
        }

        moviePoints /= movieGenres.size();
        return moviePoints;
    }

    @Override
    public void feedback(double score) {
        int oldCount = this.COUNTS.getOrDefault(this.lastUserId, 0);

        List<String> lastChoiceGenre = MOVIE_LIST.get(Integer.parseInt(this.lastChoice));
        Map<String, Double> userRatings = USERS_GENRES_RATINGS.get(Integer.parseInt(this.lastUserId));

        for (String genre : lastChoiceGenre) {
            Double oldRating = userRatings.get(genre);
            Double newRating = (oldRating * oldCount + score) / (oldCount + 1);

            userRatings.put(genre, newRating);
        }

        USERS_GENRES_RATINGS.put(Integer.parseInt(this.lastUserId), userRatings);
        this.COUNTS.put(this.lastUserId, oldCount + 1);
    }
}
