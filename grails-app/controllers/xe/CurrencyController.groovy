package xe

import grails.converters.JSON
import groovy.json.JsonSlurper;

import org.bson.types.ObjectId
import org.codehaus.groovy.grails.web.json.JSONObject
import org.mongodb.morphia.Datastore

import xe.data.CurrencyDAO
import xe.data.ExchangeDAO
import xe.data.ICurrencyDAO
import xe.data.IExchangeDAO
import xe.data.MongoFactory
import xe.domain.Exchange;
import xe.exception.ConversionMissingException;
import xe.exception.ExchangeExistentException;
import xe.exception.XEException;

class CurrencyController {
	def interchangeService;

	def listCurr() {
		def list = interchangeService.findAllCurrencies();
		render list as JSON
	}

	def listHis() {
		def list = interchangeService.findAllHistorics();
		render list as JSON
	}

	def listExc() {
		def list = interchangeService.findAllExchanges();
		render list as JSON
	}

	def exchange() {
		def pojo = interchangeService.getExchangeById(params.id);
		render pojo as JSON
	}

	def saveExchange(){
		def json = request.JSON;
		Exchange exchange = getExchange(json, false);
		interchangeService.updateExchange(exchange);
		render "success";
	}

	def addExchange(){
		def json = request.JSON;
		Exchange exchange = getExchange(json, true);
		interchangeService.saveExchange(exchange);
		render "sucess";
	}

	def handleExchangeExistenteException(ExchangeExistentException exc){
		exc.printStackTrace();
		response.status = 500;
		render exc.getMessage();
	}

	def handleConversionMissingException(ConversionMissingException exc){
		exc.printStackTrace();
		response.status = 500;
		render exc.getMessage();
	}

	def conversion(){
		def json = request.JSON;
		println json
		def slurper = new JsonSlurper();
		def result = slurper.parseText(json.toString());
		println result.params.from
		def amountResult = interchangeService.doConversion(result.params.from, result.params.to, result.params.amount);
		render result.params.amount + " " + result.params.from + " = " + amountResult + " " + result.params.to;
	}

	private Exchange getExchange(JSONObject json, boolean isNew){
		def slurper = new JsonSlurper();
		def result = slurper.parseText(json.toString());
		Exchange exchange = new Exchange();
		exchange.setCurrFrom(result.params.currFrom);
		exchange.setCurrTo(result.params.currTo);
		BigDecimal rateFrom = result.params.rateFrom ;
		exchange.setRateFrom(rateFrom.doubleValue());
		exchange.setRateTo((Math.round(1 / rateFrom.doubleValue() * 10000))/10000d);
		if (!isNew){
			exchange.setObjid(result.params.objid);
		}
		return exchange;
	}
}
