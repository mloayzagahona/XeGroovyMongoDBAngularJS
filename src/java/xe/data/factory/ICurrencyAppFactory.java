package xe.data.factory;

import xe.data.IConversionDAO;
import xe.data.ICurrencyDAO;
import xe.data.IExchangeDAO;
import xe.data.IHistoricDAO;

public interface ICurrencyAppFactory {

	String MongoDB = "mongodb";
	
	String Cassandra = "cassandra";

	ICurrencyDAO createCurrencyDAO();

	IExchangeDAO createExchangeDAO();

	IHistoricDAO createHistoricDAO();

	IConversionDAO createConversionDAO();
}
