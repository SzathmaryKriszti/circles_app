package hu.progmasters.circlesapp.domain;

public enum UserRole {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");


    private final String displayRole;


    UserRole(String displayRole) {
        this.displayRole = displayRole;

    }

    public String getDisplayRole() {
        return displayRole;
    }
}
