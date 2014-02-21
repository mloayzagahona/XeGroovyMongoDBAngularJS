package xe.data;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import xe.domain.Conversion;

public class ConversionDAO extends BasicDAO<Conversion, ObjectId> implements IConversionDAO {

	public ConversionDAO(Datastore ds) {
		super(ds);
	}
	
	@Override
	public void saveConversion(Conversion conversion) {
		save(conversion);
	}

	@Override
	public List<Conversion> findAllConversions() {
		return find().asList();
	}

}
