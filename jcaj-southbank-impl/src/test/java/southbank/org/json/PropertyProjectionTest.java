package southbank.org.json;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertyProjectionTest {

	List<GroupResult> groups;

	@Test
	public void project_test() {
		Projection propery_t1_x = new PropertyProjection("t1f10.x", "t1.x");
		List<GroupProjectionResult> projectionResult = propery_t1_x.project(groups);

		Assert.assertEquals(groups.size(), projectionResult.size());

		for (GroupProjectionResult groupProjectionResult : projectionResult) {

			// x = 5.0D has 3 elements
			if ((Double) groupProjectionResult.getGroupedValue() == 5.0D) {
				Assert.assertEquals(5.0D, groupProjectionResult.getValue());
			}

			// x = 2.0D has 3 elements
			if ((Double) groupProjectionResult.getGroupedValue() == 2.0D) {
				Assert.assertEquals(2.0D, groupProjectionResult.getValue());
			}

			// x = 7.0D has 3 elements
			if ((Double) groupProjectionResult.getGroupedValue() == 7.0D) {
				Assert.assertEquals(7.0D, groupProjectionResult.getValue());
			}

			// x = 9.0D has 1 elements
			if ((Double) groupProjectionResult.getGroupedValue() == 9.0D) {
				Assert.assertEquals(9.0D, groupProjectionResult.getValue());
			}
		}
	}

	@Before
	public void setUp() {
		GroupBy groupBy = new GroupByImpl("t1f10", "x");

		Criteria t1f10 = CriteriaImpl.createCriteria("t1f10");
		InnerJoin innerJoin = new InnerJoin("z", "t2f10", "z");

		groups = groupBy.group(innerJoin.join(t1f10));
	}
}
