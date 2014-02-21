package xe.data;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import xe.domain.Historic;

public class HistoricDAO extends BasicDAO<Historic, ObjectId> implements IHistoricDAO {

	public HistoricDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public Key<Historic> createHistoric(Historic historic) {
		return save(historic);
	}

	@Override
	public List<Historic> findAllHistorics() {
		return getDatastore().createQuery(Historic.class).order("-deletedAt, -updatedAt, -createdAt").asList();
	}
}
