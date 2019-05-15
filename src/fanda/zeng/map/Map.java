package fanda.zeng.map;

/**
 * @Description: 映射接口描述
 * @Author: fanda
 * @Date: 2019/5/15
 */
public interface Map<K, V> {

    /**
     * 添加一个键值对，如果 key 存在，则直接更新值
     */
    void add(K key, V value);

    /**
     * 根据键来移除该键值对
     */
    V remove(K key);

    /**
     * 是否有包含该键的键值对
     */
    boolean contains(K key);

    /**
     * 获取 key 对应的 value
     */
    V get(K key);

    /**
     * 修该键值对
     */
    void set(K key, V newValue);

    /**
     * 返回键值对的数量
     */
    int getSize();

    /**
     * 映射是否为空
     */
    boolean isEmpty();
}
