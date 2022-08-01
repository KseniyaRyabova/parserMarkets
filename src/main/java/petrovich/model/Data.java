package petrovich.model;

import java.util.ArrayList;

public class Data {
    public ArrayList<Section> sections;

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Data{" +
                "sections=" + sections +
                '}';
    }
}
