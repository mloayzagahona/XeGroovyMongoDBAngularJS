package xe.service

import grails.transaction.Transactional
import xe.data.IConversionDAO
import xe.data.ICurrencyDAO
import xe.data.IExchangeDAO
import xe.data.IHistoricDAO
import xe.data.factory.DaoFactory
import xe.data.factory.ICurrencyAppFactory
import xe.domain.Conversion
import xe.domain.Exchange
import xe.domain.Historic
import xe.exception.ConversionMissingException;
import xe.exception.ExchangeExistentException;
import xe.exception.IMessagesException;
import xe.exception.XEException;


class InterchangeService {

	ICurrencyAppFactory daoAppFactory;
	ICurrencyDAO currencyDao;
	IExchangeDAO exchangeDao;
	IHistoricDAO historicDao;
	IConversionDAO conversionDao;

	def initialize(){
		daoAppFactory = DaoFactory.getFactory(ICurrencyAppFactory.MongoDB);
		currencyDao = daoAppFactory.createCurrencyDAO();
		exchangeDao = daoAppFactory.createExchangeDAO();
		historicDao = daoAppFactory.createHistoricDAO();
		conversionDao = daoAppFactory.createConversionDAO();
	}

	def findAllCurrencies() {
		println "finding all currencies ...";
		return currencyDao.findAllCurrencies();
	}

	def findAllExchanges() {
		println "finding all exchanges ...";
		return exchangeDao.findAllExchanges();
	}

	def getExchangeById(String id){
		println "finding exchange by id ...";
		return exchangeDao.findExchangeById(id);
	}

	@Transactional
	def updateExchange(Exchange exchange){
		println "updating exchange ...";
		exchangeDao.updateExchange(exchange);
		Historic historic = new Historic(exchange);
		historic.setUpdatedAt(new Date());
		historicDao.createHistoric(historic)
	}

	@Transactional
	def saveExchange(Exchange exchange) throws ExchangeExistentException {
		println "creating a exchange ...";
		exchangeDao.createExchange(exchange);
		Historic historic = new Historic(exchange);
		historic.setCreatedAt(new Date());
		historicDao.createHistoric(historic)
	}

	@Transactional
	def removeExchange(Exchange exchange){
		println "removing exchange ...";
		exchangeDao.removeExchange(exchange);
		Historic historic = new Historic(exchange);
		historic.setDeletedAt(new Date());
		historicDao.createHistoric(historic)
	}

	def findAllHistorics() {
		println "finding all historics records ...";
		return historicDao.findAllHistorics();
	}

	//@Transactional
	def doConversion(String from, String to, double amount) throws ConversionMissingException{
		println "doing conversions ...";
		Exchange exchange = new Exchange();
		boolean isFromTo = true;
		double result = 0d;
		exchange.setCurrFrom(from);
		exchange.setCurrTo(to);
		exchange = exchangeDao.findExchangeByToFrom(exchange);
		if (exchange == null){
			exchange = new Exchange();
			exchange.setCurrFrom(to);
			exchange.setCurrTo(from);
			exchange= exchangeDao.findExchangeByToFrom(exchange);
			if (exchange == null){
				ConversionMissingException exception = new ConversionMissingException(
						IMessagesException.CONVERSION_ERROR_MSG, IMessagesException.CONVERSION_ERROR_CODE);
				throw exception;
			}
			isFromTo = false;
		}
		Conversion conversion = new Conversion(exchange);
		conversion.setAmount(amount);
		if (isFromTo){
			conversion.setRateUsed(exchange.getRateFrom());
			result = exchange.getRateFrom() * amount;
		}else{
			conversion.setRateUsed(exchange.getRateTo());
			result = exchange.getRateTo() * amount;
		}
		conversion.setUpdatedAt(new Date());
		conversionDao.saveConversion(conversion);
		result = result != null && result > 0 ? Math.round(result * Math.pow(10,4)) / Math.pow(10,4) : 0; 
		return result;
	}

	static void main(String[] args){
		println "Hello World";
		InterchangeService service = new InterchangeService();
		service.initialize();
		Exchange exchange = new Exchange();
		exchange.setCurrFrom("MXN");
		exchange.setCurrTo("USD");
		def str = service.doConversion("USD", "MXN", 100);
		println str;
		str = service.doConversion("MXN", "USD", 100);
		println str;
	}
}

