package southbank.org.json;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SUMProjectionTest {

	List<GroupResult> groups;

	@Test
	public void project_test() {
		Projection sum = new SUMProjection("t1f10.y", "t1.y");
		List<GroupProjectionResult> projectionResult = sum.project(groups);

		Assert.assertEquals(groups.size(), projectionResult.size());

		for (GroupProjectionResult groupProjectionResult : projectionResult) {

			// x = 5.0D has 3 elements with t1.y=82.0 merged for 3 times for t2
			// 3 values
			if ((Double) groupProjectionResult.getGroupedValue() == 5.0D) {
				Assert.assertEquals(82.0D * 3, groupProjectionResult.getValue());
			}

			// x = 2.0D has 3 elements with t1.y=31.0 merged for 3 times for t2
			// 3 values
			if ((Double) groupProjectionResult.getGroupedValue() == 2.0D) {
				Assert.assertEquals(31.0D * 3, groupProjectionResult.getValue());
			}

			// x = 7.0D has 3 elements with t1.y=87.0 merged for 3 times for t2
			// 3 values
			if ((Double) groupProjectionResult.getGroupedValue() == 7.0D) {
				Assert.assertEquals(87.0D * 3, groupProjectionResult.getValue());
			}

			// x = 9.0D has 1 elements with t1.y=65.0 merged for 3 times for t2
			// 3 values
			if ((Double) groupProjectionResult.getGroupedValue() == 9.0D) {
				Assert.assertEquals(65.0D, groupProjectionResult.getValue());
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
