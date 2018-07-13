package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import Impl.Parama;

public class Parama2Json {
	public Gson getJSONObject(Parama parameter) {
		Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id",parameter.getId());
        map.put("parameter",parameter.getParama());
        map.put("questionid", parameter.getQuestionId());
        map.put("rank", parameter.getRank());
        gson.toJson(map);
        return gson;
    }

	public static String GsonListStr(Set<Parama> parametes) {
		Gson gson = new Gson();
		return gson.toJson(parametes);
	}
}
