package xyz.leefly.project.dto.enums;

public enum CompanyNature {

    PERSONAL("个人"),
    STATE("国有"),
    HOLDING("股份制"),
    FOREIGN("外资"),
    ;

    private final String name;

    CompanyNature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
