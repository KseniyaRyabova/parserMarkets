package petrovich.model;

import java.util.ArrayList;

public class Section {
    public int code;
    public String title;
    public ArrayList<Section> sections;


    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Section{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", sections=" + sections +
                '}';
    }
}
