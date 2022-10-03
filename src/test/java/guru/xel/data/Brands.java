package guru.xel.data;

public enum Brands {
    UDILISCHA("Удилища"),
    PRIMANKI("Приманки");
    private final String desc;
    Brands(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}