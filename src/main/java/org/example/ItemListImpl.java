package org.example;

import org.example.exceptions.ItemListIndexOutOfBoundsException;
import org.example.exceptions.ItemListIsNullPointerException;
import org.example.exceptions.ItemListNotFoundException;

import java.util.Arrays;
import java.util.Objects;

public class ItemListImpl implements ItemList{

    private Integer[] itemList;
    private int size;

    public ItemListImpl(int sizeCurrent) {
        this.itemList = new Integer[sizeCurrent];
        size = 0;
    }

    private void checkElementItemList (int index){
        if (index < 0 || index >= size) {
            throw new ItemListIndexOutOfBoundsException();
        }
    }

    private void resize(int sizeItemList) {
        int newSize = size * 2;
        newSize = Math.max(newSize, sizeItemList);
        Integer[] newItemList = new Integer[newSize];
        System.arraycopy(itemList,0,newItemList,0,size);
        itemList = newItemList;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.

    @Override
    public Integer add(Integer item) {
        if (size == itemList.length) {
            resize(size + 1);
        }
        itemList[size] = item;
        size++;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.

    @Override
    public Integer add(int index, Integer item) {
        if (size == itemList.length) {
            resize(size + 1);
        }
        for (int i = size; i > index; i--){
            itemList[i] = itemList[i - 1];
        }
        itemList[index] = item;
        size++;
        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public Integer set(int index, Integer item) {
        checkElementItemList(index);
        itemList[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ItemListNotFoundException();
        }
        return remove(index);
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(int index) {
        checkElementItemList(index);
        Integer result = itemList[index];
        for (int i = index + 1; i < size; i++) {
            itemList[i - 1] = itemList[i];
        }
        size--;
        return result;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(Integer item) {
        sort();
        return search(item);
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i <size; i++) {
            if (itemList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0 ; i--) {
            if (itemList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public Integer get(int index) {
        checkElementItemList(index);
        return itemList[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(ItemList otherList) {
        if (otherList == null) {
            throw new ItemListIsNullPointerException();
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(itemList[i], otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return size;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        Arrays.fill(itemList,0,size,null);
        size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public Integer[] toArray() {
        Integer[] newItemList = new Integer[size];
        System.arraycopy(itemList,0,newItemList,0,size);
        return newItemList;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(itemList[i]);
        }
        result.append(" ]");
        return result.toString();
    }

    private void sort() {
        int in = 0;
        int out = 0;
        for (out = 1; out < size; out++) {
            Integer tmp = itemList[out];
            in = out;
            while (in > 0 && itemList[in - 1] >= tmp) {
                itemList[in] = itemList[in - 1];
                in--;
            }
            itemList[in] = tmp;
        }
    }

    private boolean search(Integer item) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (itemList[mid].compareTo(item) == 0) {
                return true;
            } else if (itemList[mid].compareTo(item) < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
