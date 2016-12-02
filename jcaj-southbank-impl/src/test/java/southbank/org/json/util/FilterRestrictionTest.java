package southbank.org.json.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import southbank.org.json.GreaterThan;
import southbank.org.json.Restriction;

public class FilterRestrictionTest {

	@Test
	public void filter_test() {

		RestrictionFilter filter = new RestrictionFilter();
		TableReader reader = new TableReader();
		List<Restriction> restrictions = new ArrayList<Restriction>();
		restrictions.add(new GreaterThan("t1f10", "z", 2.0D));

		try {
			List<Map<String, Object>> results = reader.fullRead("t1f10");
			results = filter.filter(results, restrictions);
			Assert.assertEquals(8, results.size());

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
