package xe.data;

import java.util.Date;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import xe.data.factory.DaoFactory;
import xe.data.factory.ICurrencyAppFactory;
import xe.domain.Currency;
import xe.domain.Exchange;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoFactory {

	private static final Logger LOG = Logger.getLogger(MongoFactory.class.getName());
	private static final MongoFactory INSTANCE = new MongoFactory();

	private final Datastore datastore;
	public static final String DB_NAME = "xe";

	private MongoFactory() {
		try {
			MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
			mongoClient.setWriteConcern(WriteConcern.SAFE);
			datastore = new Morphia().mapPackage(Currency.class.getPackage().getName()).createDatastore(mongoClient, DB_NAME);
			datastore.ensureIndexes();
			LOG.info("Connection to database '" + DB_NAME + "' initialized");
		} catch (Exception e) {
			throw new RuntimeException("Error initializing MongoDB", e);
		}
	}

	public static MongoFactory instance() {
		return INSTANCE;
	}

	public Datastore getDatabase() {
		return datastore;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		ICurrencyAppFactory daoAppFactory = DaoFactory.getFactory(ICurrencyAppFactory.MongoDB);
		IExchangeDAO dao = daoAppFactory.createExchangeDAO();
		Exchange exch = new Exchange();
		exch.setCurrFrom("CAD");
		exch.setCurrTo("MXN");
		exch.setRateFrom(12.4555d);
		exch.setRateTo(0.0823);
		exch.setUpdatedAt(new Date());
		dao.createExchange(exch);
		/*
		 * ICurrencyDAO dao = daoAppFactory.createCurrencyDAO();
		 * System.out.println(dao.findAllCurrencies()); IHistoricDAO hisDao =
		 * daoAppFactory.createHistoricDAO();
		 * System.out.println(hisDao.findAllHistorics());
		 */
		// IConversionDAO cDao = daoAppFactory.createConversionDAO();
		// Conversion conversion = new Conversion();
		// conversion.setCurrFrom("MXN");
		// conversion.setCurrTo("USD");
		// conversion.setRateFrom(13.1222);
		// conversion.setRateTo(Conversion.inverse(13.1222, 4));
		// conversion.setAmount(3400d);
		// conversion.setRateUsed(conversion.getRateFrom());
		// cDao.saveConversion(conversion);
		// IExchangeDAO exDao = new ExchangeDAO(ds);
		// List<Exchange> lstEx= exDao.findAllExchanges();
		// System.out.println(lstEx.get(0).getObjid());
		// Exchange exch = exDao.findById(lstEx.get(0).getObjid().toString());
		// System.out.println(exch.getObjid() + " - " + exch.getRateFrom());
		// double dbl = 11.5412;
		// exch.setRateFrom(dbl);
		// exch.setRateTo((Math.round(1 / dbl * 10000))/10000d);
		// exch.setUpdatedAt(new Date());
		// exDao.updateExchange(exch);
		// System.out.println(exch.getObjid() + " - " + exch.getRateFrom());
		//
		// exch.setId(null);
		// exDao.createExchange(exch);
	}
}
