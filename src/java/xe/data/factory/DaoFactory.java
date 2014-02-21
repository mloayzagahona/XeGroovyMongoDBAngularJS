package xe.data.factory;

public class DaoFactory {
	
	private static ICurrencyAppFactory abstractFactory = null;

	public static ICurrencyAppFactory getFactory(String type) {
		System.out.println("Into Dao FActory");
		if (type.equalsIgnoreCase(ICurrencyAppFactory.MongoDB)) {
			abstractFactory = new MongoDBDAOFactory();
		} else {
			// use else block if you want to implement in other persistence engine.
			// here code for other type of DAO Factory, maybe Cassandra or PostgreSQL
			throw new IllegalStateException();
		}
		return abstractFactory;
	}

}
