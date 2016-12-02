package southbank.org.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;

import southbank.org.json.util.TableReader;

public class InnerJoin implements Join {

	private final String propertyL;
	private final String table;
	private final String propertyR;

	public InnerJoin(String propertyL, String table, String propertyR) {
		this.propertyL = propertyL;
		this.table = table;
		this.propertyR = propertyR;
	}

	@Override
	public List<Map<String, Object>> join(Criteria c) {

		TableReader tableReader = new TableReader();
		List<Map<String, Object>> mergeResults = new ArrayList<Map<String, Object>>();

		// get left criteria result set

		// retrieve table results mapping with the lhs and rhs properties
		Map<Object, List<Map<String, Object>>> lhsResults;
		try {
			lhsResults = tableReader.mapToAProperty(c.getTable(), this.propertyL);

			Map<Object, List<Map<String, Object>>> rhsResults = tableReader.mapToAProperty(this.table, this.propertyR);

			// merge starts
			for (Entry<Object, List<Map<String, Object>>> rhsEntry : rhsResults.entrySet()) {

				// check for a matching lhs result
				List<Map<String, Object>> lhsRecords = lhsResults.get(rhsEntry.getKey());

				if (lhsRecords == null || lhsRecords.isEmpty())
					continue;

				for (Map<String, Object> rhsRecord : rhsEntry.getValue()) {
					for (Map<String, Object> lhsRecord : lhsRecords) {
						lhsRecord.putAll(rhsRecord);
						lhsRecord.put(c.getTable() + "." + this.propertyL, rhsEntry.getKey());
						lhsRecord.put(this.table + "." + this.propertyR, rhsEntry.getKey());
						mergeResults.add(lhsRecord);
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mergeResults;
	}

}
