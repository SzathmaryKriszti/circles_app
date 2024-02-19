package hu.progmasters.thefriendzoneapp.domain;

import hu.progmasters.thefriendzoneapp.dto.incoming.EventCreationCommand;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "location")
    private String location;

    @Column(name = "eventDate")
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser eventOwner;

    private boolean isDeleted;

    public Event() {
    }

    public Event(EventCreationCommand command, Group group, AppUser user) {
        this.title = command.getTitle();
        this.description = command.getDescription();
        this.imgUrl = command.getImgUrl();
        this.location = command.getLocation();
        this.eventDate = command.getEventDate();
        this.group = group;
        this.eventOwner = user;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(AppUser eventOwner) {
        this.eventOwner = eventOwner;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
