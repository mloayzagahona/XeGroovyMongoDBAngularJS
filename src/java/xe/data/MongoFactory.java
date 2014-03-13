package xe.data;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import xe.data.factory.DaoFactory;
import xe.data.factory.ICurrencyAppFactory;
import xe.domain.Currency;
import xe.domain.Exchange;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoFactory {

	private static final Logger LOG = Logger.getLogger(MongoFactory.class
			.getName());
	static {
		Handler ch = new ConsoleHandler();
		LOG.addHandler(ch);
		LOG.setLevel(Level.ALL);
	}

	private static final MongoFactory INSTANCE = new MongoFactory();

	private final Datastore datastore;

	public static final String DB_NAME = "xe"; // remote mongohq

	private MongoFactory() {
		try {
			MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
			// mongodb://<user>:<password>@troup.mongohq.com:10091/app22413124
			// mongodb://<user>:<password>@widmore.mongohq.com:10000/xe
			// MongoClient mongoClient = new MongoClient("widmore.mongohq.com",
			// 10000);
			DB db = mongoClient.getDB(MongoFactory.DB_NAME);
			// db.authenticate("mloayzagahona", "qwerty.01".toCharArray());
			mongoClient.setWriteConcern(WriteConcern.SAFE);
			datastore = new Morphia().mapPackage(
					Currency.class.getPackage().getName()).createDatastore(
					mongoClient, DB_NAME);
			datastore.ensureIndexes();
			// MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
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
	public static void main(String[] args) throws Exception {
		ICurrencyAppFactory daoAppFactory = DaoFactory
				.getFactory(ICurrencyAppFactory.MongoDB);
		IExchangeDAO dao = daoAppFactory.createExchangeDAO();
		IHistoricDAO hdao =daoAppFactory.createHistoricDAO();

//		//create new Exchange
		Exchange exch = new Exchange();
		exch.setCurrFrom("CAD");
		exch.setCurrTo("MXN");
//		exch.setRateFrom(12.4555d);
//		exch.setRateTo(0.0823);
//		exch.setUpdatedAt(new Date());
//
//		//create its historics
//		Historic his = new Historic();
//		his.setCreatedAt(new Date());
//		his.setCurrFrom("CAD");
//		his.setCurrTo("MXN");
//		his.setRateFrom(12.4555d);
//		his.setRateTo(0.0823);
//		Key<Historic> key = hdao.createHistoric(his); //creating history, type create
//		his.setObjid((ObjectId)key.getId());
//
//		List<Historic> lstH = new ArrayList<Historic>();
//		lstH.add(his); 
//		exch.setHistorics(lstH); //linking history to its exchange
//		dao.createExchange(exch); //create exchange

		exch = dao.findExchangeByFromTo(exch);
		System.out.println(exch.getHistorics());

		//updating exchange
//		exch.setRateFrom(13.3346d);
//		exch.setRateTo(0.0750d);
//		exch.setUpdatedAt(new Date());
//
//		his = new Historic();
//		his.setUpdatedAt(exch.getUpdatedAt());
//		his.setCurrFrom("CAD");
//		his.setCurrTo("MXN");
//		his.setRateFrom(13.3346d);
//		his.setRateTo(0.0750d);
//		key = hdao.createHistoric(his); // creating new historic, type update
//		his.setObjid((ObjectId)key.getId());
//		List<Historic> lst = new ArrayList<Historic>();
//		lst.add(his);
//		exch.setHistorics(lst); //linking the new historics
//		dao.updateExchange(exch); //updating exchange

		// ICurrencyDAO dao = daoAppFactory.createCurrencyDAO();
		// System.out.println(dao.findAllCurrencies());
		// IHistoricDAO hisDao = daoAppFactory.createHistoricDAO();
		// System.out.println(hisDao.findAllHistorics());

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
