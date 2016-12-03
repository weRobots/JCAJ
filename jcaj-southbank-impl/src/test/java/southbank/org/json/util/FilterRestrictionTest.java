package southbank.org.json.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import southbank.org.json.restriction.GreaterThan;
import southbank.org.json.restriction.Restriction;

public class FilterRestrictionTest {

	@Test
	public void filter_test() {

		RestrictionFilter filter = new RestrictionFilter();
		TableReader reader = new TableReader();
		List<Restriction> restrictions = new ArrayList<Restriction>();
		restrictions.add(new GreaterThan("t1f10", "z", 2.0D));

		List<Map<String, Object>> results = reader.fullRead("t1f10");
		results = filter.filter(results, restrictions);
		Assert.assertEquals(8, results.size());
	}
}
