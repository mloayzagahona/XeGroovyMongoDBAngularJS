package xe.data;

import java.util.List;

import org.mongodb.morphia.Key;

import xe.domain.Historic;

public interface IHistoricDAO {

	Key<Historic> createHistoric(Historic historic);
	
	List<Historic> findAllHistorics();
}
