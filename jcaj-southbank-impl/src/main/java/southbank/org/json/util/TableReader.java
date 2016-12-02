package southbank.org.json.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TableReader {

	/**
	 * Read all JSON objects as list of Map.
	 * 
	 * @param table
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Map<String, Object>> fullRead(String table) throws FileNotFoundException, IOException, ParseException {

		JsonParser jsonParser = getJsonParser(getFileInputStream(table));
		List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();

		while (true) {
			JsonToken nextToken = jsonParser.nextToken();

			// break if end
			if (nextToken == null || nextToken == JsonToken.END_ARRAY) {
				break;
			}

			elements.add(jsonParser.readValueAs(new TypeReference<Map<String, Object>>() {
			}));

		}

		return elements;

	}

	/**
	 * 
	 * This will return the given table elements mapped to the value of the
	 * given property.
	 * 
	 * new key will contains the table property in the format of
	 * [table].[property]
	 * 
	 * @param table
	 * @param property
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Map<Object, List<Map<String, Object>>> mapToAProperty(String table, String property)
			throws FileNotFoundException, IOException, ParseException {

		JsonParser jsonParser = getJsonParser(getFileInputStream(table));
		Map<Object, List<Map<String, Object>>> elements = new HashMap<Object, List<Map<String, Object>>>();

		while (true) {
			JsonToken nextToken = jsonParser.nextToken();

			// break if end
			if (nextToken == null || nextToken == JsonToken.END_ARRAY) {
				break;
			}

			// table row
			Map<String, Object> tableRow = jsonParser.readValueAs(new TypeReference<Map<String, Object>>() {
			});

			// property value
			Object value = tableRow.get(property);

			// if value exist need to add to the map
			if (value != null) {

				Map<String, Object> propertyMapper = new HashMap<String, Object>();

				// create a new json without the joint property replacing the
				// key as table.key
				for (Entry<String, Object> entry : tableRow.entrySet()) {
					if (entry.getKey() != property)
						propertyMapper.put(table + "." + entry.getKey(), entry.getValue());
				}

				if (!elements.containsKey(value))
					elements.put(value, new ArrayList<Map<String, Object>>());

				elements.get(value).add(propertyMapper);
			}
		}

		return elements;

	}

	private JsonParser getJsonParser(InputStream in) throws JsonParseException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		final JsonFactory jsonFactory = objectMapper.getFactory();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);

		return jsonFactory.createParser(in);

	}

	private InputStream getFileInputStream(String fileName) {

		return getClass().getResourceAsStream("/" + fileName + ".json");
	}
}
