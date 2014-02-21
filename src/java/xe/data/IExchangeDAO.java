package xe.data;

import java.util.List;

import org.mongodb.morphia.Key;

import xe.domain.Exchange;
import xe.exception.XEException;

public interface IExchangeDAO {
	
	List<Exchange> findAllExchanges();
	
	Exchange findExchangeById(String id);
	
	void updateExchange(Exchange exchange);

	Key<Exchange> createExchange(Exchange exchange) throws XEException;
	
	Exchange findExchangeByFromTo(Exchange exchange);
	
	void removeExchange(Exchange exchange);
	
}
