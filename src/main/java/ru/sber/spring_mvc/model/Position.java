package ru.sber.spring_mvc.model;

public enum Position {
    JAVA_DEVELOPER("Java-разработчик"),
    REACT_DEVELOPER("React-разработчик"),
    SYSTEM_ANALYST("Системный аналитик"),
    SOFTWARE_TESTER("Тестировщик"),
    DEVOPS_ENGINEER("DevOps-инженер"),
    TEAM_LEADER("Тимлид");

    private String positionTitle;

    Position(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionTitle() {
        return positionTitle;
    }
}
