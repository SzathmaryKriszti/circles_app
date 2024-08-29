package hu.progmasters.circlesapp.dto.outgoing;

import hu.progmasters.circlesapp.domain.Group;

import java.util.List;

public class MemberList {

    private Integer memberCount;
    private List<MemberListItem> members;

    public MemberList(Group group) {
        this.memberCount = group.getMembers().size();
        this.members = group.getMembers().stream().map(MemberListItem::new).toList();
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public List<MemberListItem> getMembers() {
        return members;
    }

    public void setMembers(List<MemberListItem> members) {
        this.members = members;
    }
}
