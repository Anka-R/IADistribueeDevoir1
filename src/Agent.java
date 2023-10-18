import java.util.List;

public interface Agent {
    String present(String context, List<String> choices);
    void feedback(double score);
}