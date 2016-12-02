package southbank.org.json;

import java.util.List;
import java.util.Map;

public interface Projection<T> {

	public List<Map<String, Object>> project(T results);
}
