package southbank.org.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class GreaterThanTest {

	@Test
	public void apply_test() {

		GreaterThan greaterThan = new GreaterThan("t1f10", "z", 3.0D);

		// {"_id":1.0,"x":5.0,"y":82.0,"z":5.0}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("t1f10._id", 1.0D);
		result.put("t1f10.x", 5.0D);
		result.put("t1f10.y", 82.0D);
		result.put("t1f10.z", 5.0D);

		Assert.assertTrue(greaterThan.apply(result));

		result = new HashMap<String, Object>();
		result.put("_id", 1.0D);
		result.put("x", 5.0D);
		result.put("y", 82.0D);
		result.put("z", 5.0D);

		Assert.assertTrue(greaterThan.apply(result));
	}

}
