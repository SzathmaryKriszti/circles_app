package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.AppUser;

public class MemberListItem {

    private String name;
    private String imgUrl;

    public MemberListItem(AppUser appUser) {
        this.name = appUser.getName();
        this.imgUrl = appUser.getImgUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
