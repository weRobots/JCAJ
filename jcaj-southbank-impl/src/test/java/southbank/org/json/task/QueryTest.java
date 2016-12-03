package southbank.org.json.task;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import southbank.org.json.Criteria;
import southbank.org.json.CriteriaImpl;
import southbank.org.json.DESCOrderBy;
import southbank.org.json.GreaterThan;
import southbank.org.json.GroupByImpl;
import southbank.org.json.InnerJoin;
import southbank.org.json.PropertyProjection;
import southbank.org.json.SUMProjection;

public class QueryTest {

	Criteria query;

	@Test
	public void query_test() {

		// SELECT
		query = CriteriaImpl.createCriteria("t1");

		// projections
		query.addProjection(new PropertyProjection("t1.x", "t1.x"));
		query.addProjection(new SUMProjection("t1.y", "sum_t1.y"));
		query.addProjection(new SUMProjection("t2.y", "sum_t2.y"));

		// INNER JOIN
		query.createJoin(new InnerJoin("z", "t2", "z"));

		// WHERE
		query.addRestriction(new GreaterThan("t1", "z", 0.0D));

		// GROUP BY
		query.groupBy(new GroupByImpl("t1", "x"));

		// ORDER BY
		query.orderBy(new DESCOrderBy("sum_t2.y"));

		List<Map<String, Object>> result = query.getResult();

		// save result as a file
		save(result);

		// standard out
		print(result);
	}

	private void print(List<Map<String, Object>> result) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// pretty print
		System.out.println(json);
	}

	private void save(List<Map<String, Object>> result) {
		ObjectMapper mapper = new ObjectMapper();

		try {

			File file = new File("target/result.json");

			if (!file.exists())
				file.createNewFile();

			// write the result
			mapper.writeValue(file, result);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
