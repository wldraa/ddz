package cn.wldraa.game.poker.util;

import java.util.*;

/**
 * @author zhangqian
 */
public class MapUtils {

    public static <K, V> Map<K, List<V>> groupBy(Collection<V> data, GetValuer<K, V> getValuer) {
        Map<K, List<V>> result = new HashMap<K, List<V>>();
        for (V value : data) {
            K key = getValuer.getValue(value);
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList<V>());
            }
            result.get(key).add(value);
        }
        return result;
    }

    public static <K, V> Map<K, V> indexBy(Collection<V> data, GetValuer<K, V> getValuer) {
        Map<K, V> result = new HashMap<K, V>();
        for (V value : data) {
            result.put(getValuer.getValue(value), value);
        }
        return result;
    }

    public interface GetValuer<K, V> {
        K getValue(V data);
    }
}
