package southbank.org.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import southbank.org.json.join.InnerJoin;
import southbank.org.json.projection.ResultsProjector;
import southbank.org.json.projection.Projection;
import southbank.org.json.projection.PropertyProjection;
import southbank.org.json.projection.SUMProjection;

public class GroupResultProjectorTest {

	List<GroupResult> groups;

	@Test
	public void project_test() {
		List<Projection> projections = new ArrayList<Projection>();
		Projection sumt1 = new SUMProjection("t1f10.y", "t1.y");
		Projection sumt2 = new SUMProjection("t2f10.y", "t2.y");
		Projection t1_x = new PropertyProjection("t1f10.x", "t1.x");
		projections.add(t1_x);
		projections.add(sumt1);
		projections.add(sumt2);

		List<Map<String, Object>> list = new ResultsProjector().projectGroup(groups, projections);

		Assert.assertEquals(4, list.size());

		for (Map<String, Object> map : list) {
			Assert.assertNotNull(map.get("t1.x"));
			Assert.assertNotNull(map.get("t1.y"));
			Assert.assertNotNull(map.get("t2.y"));

			// x=5.0D t1.y=82 t2.y={123, +, +}
			if ((Double) map.get("t1.x") == 5.0D) {
				Assert.assertEquals(82.0 * 3, map.get("t1.y"));
				Assert.assertEquals(123.0D + 123.0D + 123.0D, map.get("t2.y"));
			}

			// x=2.0D t1.y=31.0 t2.y={37.199999999999996, +, +}
			if ((Double) map.get("t1.x") == 2.0D) {
				Assert.assertEquals(31.0 * 3, map.get("t1.y"));
				Assert.assertEquals(37.199999999999996D + 37.199999999999996D + 37.199999999999996D, map.get("t2.y"));
			}

			// x=7.0D t1.y=87.0 t2.y={147.9, +, +}
			if ((Double) map.get("t1.x") == 7.0D) {
				Assert.assertEquals(87.0 * 3, map.get("t1.y"));
				Assert.assertEquals(147.9D + 147.9D + 147.9D, map.get("t2.y"));
			}

			// x=9.0D t1.y=65.0 t2.y={123, 123, 123}
			if ((Double) map.get("t1.x") == 9.0D) {
				Assert.assertEquals(65.0, map.get("t1.y"));
				Assert.assertEquals(123.5D, map.get("t2.y"));
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
