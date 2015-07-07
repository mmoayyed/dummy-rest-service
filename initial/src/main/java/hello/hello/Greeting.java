package hello;

public class Greeting {

    private final long id;
    private final String content;
    private boolean hasInterrupt = true;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getHasInterrupt() {
        return this.hasInterrupt;
    }
}