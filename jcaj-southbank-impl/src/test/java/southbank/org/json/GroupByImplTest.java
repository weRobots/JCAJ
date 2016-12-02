package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupByImplTest {

	GroupBy groupBy;
	List<Map<String, Object>> mergedResults;

	@Test
	public void group_test() {
		List<GroupResult> groups = groupBy.group(mergedResults);
		Assert.assertEquals(4, groups.size());

		for (GroupResult groupResult : groups) {

			// should be same group value
			for (Map<String, Object> row : groupResult.value()) {
				Assert.assertEquals(groupResult.getGroupedValue(), row.get(groupResult.getGroupedProperty()));
			}

			// x=5.0 should contain 3 elements
			if ((Double) groupResult.getGroupedValue() == 5.0D)
				Assert.assertEquals(3, groupResult.value().size());

			// x=2.0 should contain 3 elements
			if ((Double) groupResult.getGroupedValue() == 2.0D)
				Assert.assertEquals(3, groupResult.value().size());

			// x=7.0 should contain 3 elements
			if ((Double) groupResult.getGroupedValue() == 7.0D)
				Assert.assertEquals(3, groupResult.value().size());

			// x=9.0 should contain 1 elements
			if ((Double) groupResult.getGroupedValue() == 9.0D)
				Assert.assertEquals(1, groupResult.value().size());

		}
	}

	@Before
	public void setUp() {
		groupBy = new GroupByImpl("t1f10", "x");

		Criteria t1f10 = CriteriaImpl.createCriteria("t1f10");
		InnerJoin innerJoin = new InnerJoin("z", "t2f10", "z");

		mergedResults = innerJoin.join(t1f10);
	}
}
