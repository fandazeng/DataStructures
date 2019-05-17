package fanda.zeng.hash;

import java.util.TreeMap;

/**
 * @Description: 哈希表
 * @Author: fanda
 * @Date: 2019/5/17
 */
public class HashTable<K, V> {

    // 合适的素数数组
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    // 每个位置平均存储的元素达到的数量上界，达到即扩容
    private static final int UPPER_TOL = 10;
    // 每个位置平均存储的元素达到的数量下界，达到即缩容
    private static final int LOWER_TOL = 2;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    // 合适的素数
    private int M;
    private int size;

    public HashTable() {
        // 默认取第一个素数
        this.M = capacity[capacityIndex];
        // 创建数组
        hashtable = new TreeMap[M];

        // 创建数组每个位置的 TreeMap
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    /**
     * 哈希函数，转换为对应的索引
     */
    private int hash(K key) {
        //  & 0x7fffffff 是为了去掉符号位，变为正数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加
     */
    public void add(K key, V value) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        if (treeMap.containsKey(key)) {
            treeMap.put(key, value);
        } else {
            treeMap.put(key, value);
            // 维护数量
            size++;
            // 这里将除法运算转为乘法运算，避免出现类型转换等情况
            if (size >= UPPER_TOL * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        // 注意这里，此时hash函数用到的M ，应该是newM
        this.M = newM;
        for (TreeMap<K, V> treeMap : hashtable) {
            for (K k : treeMap.keySet()) {
                newHashTable[hash(k)].put(k, treeMap.get(k));
            }
        }

        this.hashtable = newHashTable;

    }

    /**
     * 删除
     */
    public V remove(K key) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        V ret = null;
        if (treeMap.containsKey(key)) {
            ret = treeMap.remove(key);
            // 维护数量
            size--;
            if (size < LOWER_TOL * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * 修改
     */
    public void set(K key, V value) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        if (!treeMap.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist");
        } else {
            treeMap.put(key, value);
        }
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

}
