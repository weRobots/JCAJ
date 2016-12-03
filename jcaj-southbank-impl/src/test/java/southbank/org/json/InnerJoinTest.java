package southbank.org.json;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import southbank.org.json.Criteria;
import southbank.org.json.CriteriaImpl;
import southbank.org.json.join.InnerJoin;

public class InnerJoinTest {

	private InnerJoin innerJoin;

	@Test
	public void join_test() {
		Criteria t1f10 = CriteriaImpl.createCriteria("t1f10");
		innerJoin = new InnerJoin("z", "t2f10", "z");

		List<Map<String, Object>> mergedResults = innerJoin.join(t1f10);

		// full records 10
		Assert.assertEquals(10, mergedResults.size());

		// each result must contains
		for (Map<String, Object> map : mergedResults) {
			Assert.assertEquals(8, map.size());

			// t1 properties
			Assert.assertTrue(map.containsKey("t1f10._id"));
			Assert.assertTrue(map.containsKey("t1f10.x"));
			Assert.assertTrue(map.containsKey("t1f10.y"));
			Assert.assertTrue(map.containsKey("t1f10.z"));
			Assert.assertTrue(map.get("t1f10.z") instanceof Double);

			// t2 properties
			Assert.assertTrue(map.containsKey("t2f10._id"));
			Assert.assertTrue(map.containsKey("t2f10.y"));
			Assert.assertTrue(map.containsKey("t2f10.z"));
			Assert.assertTrue(map.get("t1f10.z") instanceof Double);
			Assert.assertTrue(map.containsKey("t2f10.zz"));

			// keys equal
			Assert.assertEquals(map.get("t1f10.z"), map.get("t1f10.z"));

		}
	}

}
