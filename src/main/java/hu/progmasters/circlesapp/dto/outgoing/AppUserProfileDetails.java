package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.AppUser;

public class AppUserProfileDetails {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String role;

    private Integer memberInGroups;

    private Integer ownedGroups;

    private Integer inRelationWithMembers;

    private String imgUrl;

    public AppUserProfileDetails() {
    }

    public AppUserProfileDetails(AppUser appUser) {
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.role = appUser.getRole().getDisplayRole();
        this.memberInGroups = appUser.getGroups().size();
        this.ownedGroups = appUser.getOwnedGroups().size();
        this.inRelationWithMembers = 0; //TODO kiszamolni a kapcsolatot / count relationship
        this.imgUrl = appUser.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getMemberInGroups() {
        return memberInGroups;
    }

    public void setMemberInGroups(Integer memberInGroups) {
        this.memberInGroups = memberInGroups;
    }

    public Integer getOwnedGroups() {
        return ownedGroups;
    }

    public void setOwnedGroups(Integer ownedGroups) {
        this.ownedGroups = ownedGroups;
    }

    public Integer getInRelationWithMembers() {
        return inRelationWithMembers;
    }

    public void setInRelationWithMembers(Integer inRelationWithMembers) {
        this.inRelationWithMembers = inRelationWithMembers;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
