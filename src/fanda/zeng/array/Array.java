package fanda.zeng.array;

/**
 * @Description: 自定义动态数组
 * @Author: fanda
 * @Date: 2019/5/10
 */
public class Array<E> {

    // 类内维护的数组
    private E[] data;
    // 数组内实际存储元素的个数
    private int size;

    /**
     * 构造函数，默认数组的容量为10
     */
    public Array() {
        this(10);
    }

    /**
     * 构造函数，传入数组的容量
     */
    public Array(int capacity) {
        //创建带容量的 Object 数组并强转为E类型的数组
        data = (E[]) new Object[capacity];
    }

    /**
     * 构造函数，传入一个静态数组
     */
    public Array(E[] arr) {
        //创建 Object 数组并强转为E类型的数组，长度为传入的静态数组的大小
        data = (E[]) new Object[arr.length];
        // 把静态数组的值全部复制到 data 数组
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 目前 data 数组的长度为静态数组的大小
        size = arr.length;
    }

    /**
     * 在 index 索引位置插入新元素 e ,，时间复杂度O(n)
     */
    public void add(int index, E e) {
        // 为保证数组的连续性，紧密相联，即索引之间不能有空元素
        // 如果索引小于 0 或 索引大于最后一个元素的下一个元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index>= 0 and index <= size.");
        }

        // 如果此时数组已满，则扩容为2倍
        if (size == data.length) {
            resize(data.length * 2);
        }

        // index 及后面的元素都向后移动一个位置,index位置的元素会复制到index+1的位置
        for (int i = size - 1; i >= index; i--) {
            // 后一个元素等于前一个元素
            data[i + 1] = data[i];
        }
        //在 index 的位置把之前的值覆盖掉，也就是添加了新元素了
        data[index] = e;
        // 维护 size ，此时添加了元素，应当加1
        size++;
    }

    /**
     * 将数组空间的容量变成 newCapacity 的大小，均摊时间复杂度O(1)
     */
    private void resize(int newCapacity) {
        // 创建一个 newCapacity 容量的数组对象
        E[] newData = (E[]) new Object[newCapacity];
        // 把以前的数据复制到新对象中
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 将当前数组对象指向新的数组对象
        this.data = newData;
    }

    /**
     * 向所有元素后添加一个元素，时间复杂度O(1)
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向所有元素前添加一个元素，时间复杂度O(n)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 删除指定 index 索引对应的元素，并返回删除的元素，时间复杂度O(n)
     */
    public E remove(int index) {
        // 注意这个等于的情况
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Demove failed. Require index>= 0 and index < size.");
        }
        // 先拿到需要返回的元素
        E res = data[index];

        // index 后面的元素都向前移动一个位置
        for (int i = index + 1; i < size; i++) {
            // 前一个元素等于后一个元素
            data[i - 1] = data[i];
        }
        // 维护 size ，此时删除了元素，应当减1
        size--;
        // 把最后没用到的对象置null，利于GC释放内存
        data[size] = null;
        // 当前存储的元素的大小为容量的4分1时，缩容为原来的一半
        // 注意，不能当前存储的元素的大小为容量的2分1时来缩容，因为扩容的时候是扩容2倍，在
        // 边界反复添加和删除元素时，会出现复杂度震荡
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        // 返回当前删除的元素
        return res;
    }

    /**
     * 删除最后一个元素，并返回删除的元素，时间复杂度O(1)
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除第一个元素，并返回删除的元素，时间复杂度O(n)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 如果存在元素e ，则删除
     */
    public void removeElement(E e) {
        // 找到元素 e 对应的索引
        int index = find(e);
        // 如果索引存在，则删除
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 修改 index 索引位置的元素为 e ，时间复杂度O(1)
     */
    public void set(int index, E e) {
        // 注意这个等于的情况
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        // 直接修改对应索引元素的值
        data[index] = e;
    }

    /**
     * 获取 index 索引位置的元素，时间复杂度O(1)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 查找数组中是否有元素e，时间复杂度O(n)
     */
    public boolean contain(E e) {
        // 不能用这种方式遍历，因为我们每次删除元素时都调用了如下代码  data[size] = null，所以会出现空指针问题
      /*  for (E datum : data) {
            if (datum.equals(e)) {
                return true;
            }
        }*/

        for (int i = 0; i < size; i++) {
            // 注意这里用的是 equals，不能用 ==
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中对应的元素的索引，如果元素不存在，则返回索引为 -1，时间复杂度O(n)
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回最后一个元素，时间复杂度O(1)
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 返回第一个元素，时间复杂度O(1)
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取数组中元素的个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // 交换对应元素索引的值
    public void swap(int i, int j) {
        // 判断索引越界的情况
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {

        Array<Integer> scores = new Array<>();
        for (int i = 0; i < 10; i++) {
            scores.addLast(i);
        }

        // 增
        scores.addFirst(-1);
        System.out.println(scores.toString() + "\n");
        scores.addLast(200);
        System.out.println(scores.toString() + "\n");
        scores.add(10, 100);
        System.out.println(scores.toString() + "\n");

        // 删
        scores.removeFirst();
        System.out.println(scores.toString() + "\n");
        scores.removeLast();
        System.out.println(scores.toString() + "\n");
        scores.remove(5);
        System.out.println(scores.toString() + "\n");
        scores.removeElement(6);
        System.out.println(scores.toString() + "\n");

        //改
        scores.set(0, 99);
        System.out.println(scores.toString() + "\n");

        // 查
        System.out.println(scores.getFirst() + " 、" + scores.getLast() + " 、" +
                scores.get(5) + " 、" + scores.contain(6) + " 、" + scores.find(60));
    }
}
