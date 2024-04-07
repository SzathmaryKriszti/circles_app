package hu.progmasters.circlesapp.repository.elastic;

import hu.progmasters.circlesapp.domain.elastic.GroupSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GroupSearchRepository extends ElasticsearchRepository<GroupSearch, Long> {
}
