package xe.data;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import xe.domain.Currency;

public class CurrencyDAO extends BasicDAO<Currency, ObjectId> implements ICurrencyDAO {

	public CurrencyDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public List<Currency> findAllCurrencies() {
		return find().asList();
	}
}
