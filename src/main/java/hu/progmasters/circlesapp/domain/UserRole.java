package hu.progmasters.circlesapp.domain;

public enum UserRole {

    ROLE_USER("User"),
    ROLE_ADMIN("Admin");


    private final String displayRole;


    UserRole(String displayRole) {
        this.displayRole = displayRole;

    }

    public String getDisplayRole() {
        return displayRole;
    }
}
