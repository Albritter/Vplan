/**
 * Created by albritter on 15.08.16.
 */
public final class Lesson {
    /**
     * 0 = First lesson, 2 =Second, 4= third
     * 1 would be a half lesson
     */
    public final byte start;
    public final byte end;
    public final String teacher;
    public final String longname;
    public final String shortname;
    public final WeekDay day;

    public Lesson(byte start, byte end, String teacher, String longname, String shortname, WeekDay day) {

        this.start = start;
        this.end = end;
        this.teacher = teacher;
        this.longname = longname;
        this.shortname = shortname;
        this.day = day;
    }

    public enum WeekDay {
        MONDAY, THURSDAY, WEDNESDAY, TUESDAY, FRIDAY, SATURDAY, SUNDAY
    }

}
