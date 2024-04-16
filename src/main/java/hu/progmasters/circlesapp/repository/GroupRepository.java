package hu.progmasters.circlesapp.repository;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g " +
            "JOIN g.members m " +
            "LEFT JOIN g.posts p " +
            "LEFT JOIN g.events e " +
            "WHERE m = :user " +
            "GROUP BY g " +
            "ORDER BY g.createdAt DESC"
//            "ORDER BY (COUNT(p) + COUNT(e)) DESC"
           )
    Page<Group> findGroupsJoinedByUser(AppUser user, Pageable pageable);

    @Query("SELECT g FROM Group g WHERE :user NOT MEMBER OF g.members")
    Page<Group> findGroupsNotJoinedByUser(AppUser user, Pageable pageable);
}
