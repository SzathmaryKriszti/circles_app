package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Group;

import java.util.List;

public class EventList {

    private Integer eventCount;
    private List<EventListItem> events;

    public EventList(Group group) {
        this.eventCount = group.getEvents().size();
        this.events = group.getEvents().stream().map(EventListItem::new).toList();
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public List<EventListItem> getEvents() {
        return events;
    }

    public void setEvents(List<EventListItem> events) {
        this.events = events;
    }
}
