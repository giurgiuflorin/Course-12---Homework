import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DailyPlanner {

    private HashMap<EnumDay, DaySchedule> dailyPlanner;

    public DailyPlanner() {
        dailyPlanner = new HashMap<>();
    }

    public void addActivity(EnumDay day, String activity) {
        if (activity == null) {
            throw new NoActivityException("A null value activity was given to this method!");
        }
        if (!dailyPlanner.containsKey(day)) {
            dailyPlanner.put(day, new DaySchedule(day));
        }

        dailyPlanner.get(day).addActivity(activity);
    }

    public void removeActivity(EnumDay day, String activity) {
        if (!dailyPlanner.containsKey(day) && !dailyPlanner.containsValue(activity)) {
            throw new NoActivityException("Activity does not exist, " +
                    "therefore it cannot be deleted!");
        }
        dailyPlanner.get(day).removeActivity(activity);
    }

    public List<String> getActivities(EnumDay day) {
        if (!dailyPlanner.containsKey(day)) {
            throw new NoActivityException("Day [" + day + "] is not on the list");
        }

        return dailyPlanner.get(day).getActivities();
    }

    public Map<EnumDay, List<String>> endPlanning(EnumDay day) throws NoActivitiesForDaysException {

        if (dailyPlanner.get(day).getActivities() == null) {
            throw new NoActivitiesForDaysException("No activities for this day ->"
                    + day);
        }

        Map<EnumDay, List<String>> endPlanningMap = new HashMap<>();
        endPlanningMap.put(day, dailyPlanner.get(day).getActivities());
        return endPlanningMap;
    }


    public void commandLineSwitch(DailyPlanner dailyPlanner) throws NoActivitiesForDaysException {

        Scanner scanner = new Scanner(System.in);
        int command;

        do {
            printCommandLine();
            EnumDay[] values = EnumDay.values();
            command = scanner.nextInt();


            switch (command) {
                case 1:
                    System.out.println("You chose option 1(add an activity).\nday: ");
                    Scanner scanner1 = new Scanner(System.in);
                    String day = scanner1.nextLine().toUpperCase().substring(0, 1);
                    System.out.println("activity: ");
                    String activity = scanner1.nextLine().toLowerCase().trim();
                    addActivity(fromScanner(day), activity);
                    System.out.println(activity + " has been added");
                    break;
                case 2:
                    System.out.println("You chose option 2(remove an activity).\nday: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String day2 = scanner2.nextLine().toUpperCase().substring(0, 1);
                    System.out.println("activity: ");
                    String activity2 = scanner.nextLine().toLowerCase().trim();
                    removeActivity(fromScanner(day2), activity2);
                    System.out.println(activity2 + " has been removed");
                    break;
                case 3:
                    System.out.println("You chose option 3(list all activities).\nday: ");
                    Scanner scanner3 = new Scanner(System.in);
                    String day3 = scanner3.nextLine().toUpperCase().substring(0, 1);
                    System.out.println(getActivities(fromScanner(day3)));
                    break;
                case 4:
                    System.out.println("You chose option 4(end planning)\nday: ");
                    Scanner scanner4 = new Scanner(System.in);
                    String day4 = scanner4.nextLine().toUpperCase().substring(0, 1);
                    endPlanning(fromScanner(day4));
                    System.out.println("DailyPlanner finished! Here is your planning: "
                                        + getActivities((fromScanner(day4))));
                    break;
                default:
                    System.out.println("End of application");
                    break;
            }
        } while (command != 4);
    }

    public static EnumDay fromScanner(String symbol) {
        EnumDay[] values = EnumDay.values();
        for (EnumDay enumDay : values) {
            if (enumDay.getSymbol().equals(symbol)) {
                return enumDay;
            }
        }
        return null;
    }

    public static void printCommandLine() {
        System.out.println("Please choose an option:\n" +
                "1) add an activity\n2) remove activity" +
                "\n3) list all activities\n4) end planning");
    }
}
