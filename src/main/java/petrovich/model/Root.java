package petrovich.model;

public class Root {
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Root{" +
                "data=" + data +
                '}';
    }
}
