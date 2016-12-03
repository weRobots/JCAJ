package southbank.org.json.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableReaderTest {

	private TableReader tableReader;

	@Test
	public void fullread_Test() throws FileNotFoundException, IOException, ParseException {
		List<Map<String, Object>> fullRead = tableReader.fullRead("t1f10");
		Assert.assertEquals(10, fullRead.size());

		// check every column with table.column_name format
		for (Map<String, Object> map : fullRead) {
			Assert.assertTrue(map.containsKey("t1f10._id"));
			Assert.assertTrue(map.containsKey("t1f10.x"));
			Assert.assertTrue(map.containsKey("t1f10.y"));
			Assert.assertTrue(map.containsKey("t1f10.z"));
		}

		fullRead = tableReader.fullRead("t1");
		Assert.assertEquals(20000, fullRead.size());
	}

	@Test
	public void mapToAProperty_Test() throws FileNotFoundException, IOException, ParseException {

		// t2f10
		Map<Object, List<Map<String, Object>>> mapToZ = tableReader.mapToAProperty("t2f10", "z");
		Assert.assertEquals(4, mapToZ.size());
		Assert.assertEquals(3, mapToZ.get(1.0).size());
		Assert.assertEquals(1, mapToZ.get(4.0).size());

		// t1f10
		mapToZ = tableReader.mapToAProperty("t1f10", "z");
		Assert.assertEquals(10, mapToZ.size());
		Assert.assertEquals(1, mapToZ.get(1.0).size());
		Assert.assertEquals(1, mapToZ.get(5.0).size());
		Assert.assertEquals(1, mapToZ.get(10.0).size());
	}

	@Before
	public void setUp() {
		tableReader = new TableReader();
	}
}
