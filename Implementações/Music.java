
public class Music {
    private String name;
    private String filePath;

    public Music(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return name;
    }
}