package xe.data;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import xe.domain.Exchange;
import xe.domain.Historic;
import xe.exception.ExchangeExistentException;
import xe.exception.IMessagesException;
import xe.exception.XEException;

public class ExchangeDAO extends BasicDAO<Exchange, ObjectId> implements
		IExchangeDAO {

	public ExchangeDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public List<Exchange> findAllExchanges() {
		return find().asList();
	}

	@Override
	public Exchange findExchangeById(String id) {
		Exchange obj = get(new ObjectId(id));
		// obj.setObjid(obj.get.toString());
		return obj;
	}

	@Override
	public void updateExchange(Exchange exchange) {
		Query<Exchange> updateQuery = createQuery().field(Mapper.ID_KEY).equal(
				exchange.getObjid());
		Historic historic = exchange.getHistorics().get(0);
		UpdateOperations<Exchange> ops = createUpdateOperations()
				.set("rateFrom", exchange.getRateFrom())
				.set("rateTo", exchange.getRateTo())
				.set("updatedAt", new Date())
				.add("historics", historic.getObjid());
		update(updateQuery, ops);
	}

	@Override
	public Key<Exchange> createExchange(Exchange exchange) throws XEException {
		if (findExchangeByFromTo(exchange) != null) {
			throw new ExchangeExistentException(
					IMessagesException.EXIST_COMBINATION_MSG,
					IMessagesException.EXIST_COMBINATION_CODE);
		}
		Exchange temp = new Exchange(exchange);
		temp.setCurrFrom(exchange.getCurrTo());
		temp.setCurrTo(exchange.getCurrFrom());
		if (findExchangeByFromTo(temp) != null) {
			throw new ExchangeExistentException(
					IMessagesException.EXIST_COMBINATION_MSG,
					IMessagesException.EXIST_COMBINATION_CODE);
		}
		exchange.setUpdatedAt(new Date());

		return save(exchange);
	}

	@Override
	public Exchange findExchangeByFromTo(Exchange exchange) {
		Query<Exchange> query = createQuery();
		query.and(query.criteria("currFrom").equal(exchange.getCurrFrom()),
				query.criteria("currTo").equal(exchange.getCurrTo()));
		Exchange result = findOne(query);
		return result;
	}

	@Override
	public void removeExchange(Exchange exchange) {
		delete(exchange);
	}

}
