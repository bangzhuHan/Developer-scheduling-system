package service;

/**
 * 表示员工状态
 */
public class Status {
    private final String Name;
    private Status(String name) {
        Name = name;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
