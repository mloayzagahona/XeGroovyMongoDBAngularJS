package xe.data;

import java.util.List;

import xe.domain.Conversion;

public interface IConversionDAO {

	void saveConversion(Conversion conversion);
	
	List<Conversion> findAllConversions();
}
