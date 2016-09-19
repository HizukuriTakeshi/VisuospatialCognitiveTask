package createCogitiveTask;

import java.util.List;

public class HogeList {

    private String id;

    private List<Hoge> hoges;

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setHoges(final List<Hoge> hoges) {
        this.hoges = hoges;
    }

    public List<Hoge> getHoges() {
        return hoges;
    }

}
