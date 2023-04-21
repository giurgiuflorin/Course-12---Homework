import java.util.ArrayList;
import java.util.List;

public class DaySchedule {

    private EnumDay day;
    private List<String> activities;

    public DaySchedule(EnumDay day) {
        this.day = day;
        this.activities = new ArrayList<>();
    }

    public void addActivity(String activity) {
        activities.add(activity);

    }

    public void removeActivity(String activity) {

        activities.remove(activity);
    }

    public EnumDay getDay() {
        return day;
    }

    public List<String> getActivities() {
        return activities;
    }
}

