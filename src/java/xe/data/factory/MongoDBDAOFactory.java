package xe.data.factory;

import org.mongodb.morphia.Datastore;

import xe.data.ConversionDAO;
import xe.data.CurrencyDAO;
import xe.data.ExchangeDAO;
import xe.data.HistoricDAO;
import xe.data.IConversionDAO;
import xe.data.ICurrencyDAO;
import xe.data.IExchangeDAO;
import xe.data.IHistoricDAO;
import xe.data.MongoFactory;

public class MongoDBDAOFactory implements ICurrencyAppFactory {

	Datastore ds = MongoFactory.instance().getDatabase();

	@Override
	public ICurrencyDAO createCurrencyDAO() {
		return new CurrencyDAO(ds);
	}

	@Override
	public IExchangeDAO createExchangeDAO() {
		return new ExchangeDAO(ds);
	}

	@Override
	public IHistoricDAO createHistoricDAO() {
		return new HistoricDAO(ds);
	}

	@Override
	public IConversionDAO createConversionDAO(){
		return new ConversionDAO(ds);
	}
}
