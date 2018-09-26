package xyz.leefly.project.dto.enums;

public enum CompanyStatus {

    NORMAL(0, "正常"),
    CLOSED(1, "关闭")
    ;

    private int code;
    private final String name;

    CompanyStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
