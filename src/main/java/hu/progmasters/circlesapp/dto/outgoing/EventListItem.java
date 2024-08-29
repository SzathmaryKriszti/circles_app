package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Event;

public class EventListItem {

    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private String location;
    private String eventDate;
    private Boolean isDeleted;

    public EventListItem(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.imgUrl = event.getImgUrl();
        this.location = event.getLocation();
        this.eventDate = event.getEventDate().toString();
        this.isDeleted = event.isDeleted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
