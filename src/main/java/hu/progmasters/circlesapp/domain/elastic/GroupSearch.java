package hu.progmasters.circlesapp.domain.elastic;

import org.springframework.data.annotation.Id;


public class GroupSearch {

    @Id
    private Long id;

    private String name;

    public GroupSearch(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
