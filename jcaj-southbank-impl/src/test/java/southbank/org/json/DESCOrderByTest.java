package southbank.org.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DESCOrderByTest {

	List<Map<String, Object>> list;

	@Test
	public void order_test() {
		DESCOrderBy desc = new DESCOrderBy("sum_t2.y");
		List<Map<String, Object>> order = desc.order(list);

		Assert.assertEquals(4, list.size());

		// t1.x=7.0d
		// t1.x=5.0d
		// t1.x=9.0d
		// t1.x=2.0d

		for (int i = 0; i < order.size(); i++) {
			if (i == 0)
				Assert.assertEquals(7.0D, order.get(i).get("t1.x"));
			if (i == 1)
				Assert.assertEquals(5.0D, order.get(i).get("t1.x"));
			if (i == 2)
				Assert.assertEquals(9.0D, order.get(i).get("t1.x"));
			if (i == 3)
				Assert.assertEquals(2.0D, order.get(i).get("t1.x"));
		}
	}

	@Before
	public void setUp() {
		GroupBy groupBy = new GroupByImpl("t1f10", "x");

		Criteria t1f10 = CriteriaImpl.createCriteria("t1f10");
		InnerJoin innerJoin = new InnerJoin("z", "t2f10", "z");

		List<GroupResult> groups = groupBy.group(innerJoin.join(t1f10));

		List<Projection> projections = new ArrayList<Projection>();
		Projection sumt1 = new SUMProjection("t1f10.y", "sum_t1.y");
		Projection sumt2 = new SUMProjection("t2f10.y", "sum_t2.y");
		Projection t1_x = new PropertyProjection("t1f10.x", "t1.x");
		projections.add(t1_x);
		projections.add(sumt1);
		projections.add(sumt2);

		list = new GroupResultProjector().project(groups, projections);
	}

}
