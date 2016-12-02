package southbank.org.json;

import java.util.Map;

public interface Restriction {

	public boolean apply(Map<String, Object> result);
}
