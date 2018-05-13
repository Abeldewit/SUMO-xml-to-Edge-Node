public class Edge {
    private double speed;
    private String type;
    private int priority;
    private String to, from;
    private String id;

    public Edge(double speed, String type, int priority, String to, String from, String id) {
        this.speed = speed;
        this.type = type;
        this.priority = priority;
        this.to = to;
        this.from = from;
        this.id = id;
    }
}
