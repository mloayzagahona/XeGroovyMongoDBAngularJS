package xe.data;

import java.util.List;

import xe.domain.Currency;

public interface ICurrencyDAO {

	List<Currency> findAllCurrencies();
	
}
