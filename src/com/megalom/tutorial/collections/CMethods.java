package com.megalom.tutorial.collections;

import java.util.*;

public class CMethods {
    private static final int ELEMENT_COUNT = 100000;
    public void listTut(){
        List<String> strings = new ArrayList<>();
        ArrayList<String> str2 = new ArrayList<>();
        
        strings.add("3");
        strings.add("5");
        strings.add("2");
        strings.add("4");
        strings.add("2");
        System.out.println("0 element = " + strings.get(0));
        System.out.println("Foreach:");
        int n=0;
        for(String str:strings){
            System.out.println(n+":"+str);
            n++;
        }
        System.out.println("Sorted array list:");
        n=0;
        Collections.sort(strings);
        for(String str:strings){
            System.out.println(n+":"+str);
            n++;
        }
        System.out.println("Binary search for \"3\" after(important) sort:");
        int bs_index = Collections.binarySearch(strings,"3");
        System.out.println("Element index is "+bs_index+" = "+strings.get(bs_index));

        System.out.println("List to Array:");
        String[] tmp_str=new String[strings.size()];
        tmp_str = strings.toArray(tmp_str);
        for(int i=0;i<tmp_str.length;i++){
            System.out.println(tmp_str[i]);
        }
        System.out.println("And back:");
        List<String> strings2 = Arrays.asList(tmp_str);
        for(String tmp:strings2){
            System.out.println(tmp);
        }
        System.out.println("ArrayList comparsion: string == string2:" + strings.equals(strings2));

        System.out.println("Iteration, and deleting element called \"2\":");
        Iterator iter = strings.iterator();
        while(iter.hasNext()){
            String s = (String)iter.next();
            System.out.println(s);
            if(s.compareToIgnoreCase ("2")==0)
                iter.remove();
        }
        System.out.println("After deleting: ");
        iter = strings.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
    public void linkedListTut(){
        List<Integer> integerLinkedList = new LinkedList<>();
        List<Integer> integerArrayList = new ArrayList<>();
        System.out.println("Array list time:");
        measureTime(integerArrayList);
        System.out.println("Linked list time:");
        measureTime(integerLinkedList);
    }

    public void hashMapTutorial(){
        Map<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1,"один");
        hashMap.put(2,"два");
        hashMap.put(3,"три");
        System.out.println(hashMap);
        hashMap.put(3,"другое значение ключа три");
        System.out.println(hashMap);
        System.out.println("Значение 1:"+hashMap.get(1));
        System.out.println("Значение 9:"+hashMap.get(9));

        for(Map.Entry<Integer,String> entry : hashMap.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        Map<String,String> translations = new HashMap<>();
        translations.put("кошка", "cat");
        translations.put("собака", "dog");
        translations.put("слон", "elephant");

        for(Map.Entry<String,String> entry:translations.entrySet()){
            System.out.println(entry.getKey()+" : " + entry.getValue());
        }

    }
    public void hashMapTutorial2() {
        Map<Integer, String> hashMap = new HashMap<>(); // Внутри не гарантируется порядок
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(); // Внутри гарантируется порядок - в каком
        // порядке ключ/значение были добавлены, в таком порядке они и вернутся
        Map<Integer, String> treeMap = new TreeMap<>(); // гарантирует что пары ключ/значение
        // будут отсортированы по ключу (Естественный порядок)
        System.out.println("hashMap:");
        testMap(hashMap);
        System.out.println("linkedHashMap:");
        testMap(linkedHashMap);
        System.out.println("treeMap:");
        testMap(treeMap);

    }

    public void setTest(){
        Set<String> hashSet = new HashSet<>(); //нет порядка
        Set<String> linkHashSet = new LinkedHashSet<>();// порядок ввода
        Set<String> treeSet = new TreeSet<>(); //сортированый порядок
        System.out.println("HashSet:");
        hashSet.add("Mike");
        hashSet.add("Mike");
        hashSet.add("Bob");
        hashSet.add("Pit");
        hashSet.add("Tom");
        hashSet.add("Deny");

        for(String name:hashSet){
            System.out.println(name);
        }
        System.out.println("LinkedHashSet:");
        linkHashSet.add("Mike");
        linkHashSet.add("Mike");
        linkHashSet.add("Bob");
        linkHashSet.add("Pit");
        linkHashSet.add("Tom");
        linkHashSet.add("Deny");

        for(String name:linkHashSet){
            System.out.println(name);
        }

        System.out.println("TreeSet:");
        treeSet.add("Mike");
        treeSet.add("Mike");
        treeSet.add("Bob");
        treeSet.add("Pit");
        treeSet.add("Tom");
        treeSet.add("Deny");

        for(String name:treeSet){
            System.out.println(name);
        }

        System.out.println(hashSet.contains("Bob"));
        System.out.println(hashSet.contains("Rob"));
        System.out.println(hashSet.isEmpty());

        System.out.println(hashSet);

    }

    public void setOperationsTest(){
        Set<String> hashSet1 = new HashSet<>();
        Set<String> hashSet2 = new HashSet<>();

        hashSet1.add("Mike");
        hashSet1.add("Bob");
        hashSet1.add("Tom");
        hashSet1.add("Jerry");

        hashSet2.add("Tom");
        hashSet2.add("Jerry");
        hashSet2.add("Tony");
        hashSet2.add("Rob");

        System.out.println("Операции над множествами Set:");
        System.out.println(hashSet1);
        System.out.println(hashSet2);

        System.out.println("Объединение:");
        Set<String> union = new HashSet<>(hashSet1);
        union.addAll(hashSet2);
        System.out.println(union);

        System.out.println("Пресечение:");
        Set<String> intersection = new HashSet<>(hashSet1);
        intersection.retainAll(hashSet2);
        System.out.println(intersection);

        System.out.println("Иселючение:");
        Set<String> difference = new HashSet<>(hashSet1);
        difference.removeAll(hashSet2);
        System.out.println(difference);

    }

    public void hashCodeTest(){
        HashCodeTut hashCodeTut = new HashCodeTut();
        hashCodeTut.start();
    }


    private static void testMap(Map<Integer,String> map){
        map.put(39, "Коля");
        map.put(82, "Вася");
        map.put(56, "Петя");
        map.put(14, "Саша");
        map.put(1500, "Алеша");
        map.put(0, "Женя");
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    private static void measureTime(List<Integer> list){
        addOperation(list);
        list.clear();
        addFirstOperation(list);
        getOperation(list);

    }

    private static void addOperation(List<Integer> list){
        long start = System.currentTimeMillis();
        for(int i = 0;i<ELEMENT_COUNT;i++){
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Время добавления в конец "+ELEMENT_COUNT+" элементов: "+(end-start) +" мс");
    }

    private static void getOperation(List<Integer> list){
        long start = System.currentTimeMillis();
        for(int i = 0;i<ELEMENT_COUNT;i++){
            list.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Время получения "+ELEMENT_COUNT+" элементов: "+(end-start) +" мс");
    }

    private static void addFirstOperation(List<Integer> list){
        long start = System.currentTimeMillis();
        for(int i = 0;i<ELEMENT_COUNT;i++){
            list.add(0,i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Время добавления в начало "+ELEMENT_COUNT+" элементов: "+(end-start) +" мс");
    }



}
