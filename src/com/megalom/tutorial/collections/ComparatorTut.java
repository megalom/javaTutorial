package com.megalom.tutorial.collections;

import java.util.*;

public class ComparatorTut {
    public static void start(){
        System.out.println("Sorted with Comparator<String>:");
        List<String> strings = new ArrayList<>();
        strings.add("Marie Avgeropoluos");
        strings.add("Marie Abraham");
        strings.add("Marie Abryham");
        strings.add("Marie Abrnham");
        strings.add("Emilia Clark");
        strings.add("Lindsey Morgan");
        strings.add("Lindsey Striling");
        strings.add("Alycia Debnam Carter");
        strings.add("Choku Modo");
        strings.add("Li Hun");
        strings.sort(new StringLengthAndAlphabet());
        for(int i=0;i<strings.size();i++)
            System.out.println(strings.get(i));

        List<ComparablePerson> comparablePeople=new ArrayList<>();
        Set<ComparablePerson> comparablePersonSet= new TreeSet<>();
        addPersons(comparablePeople);
        addPersons(comparablePersonSet);
        System.out.println("List:");
        showCollection(comparablePeople);//System.out.println(comparablePeople);
        System.out.println("Set:");
        showCollection(comparablePersonSet);//System.out.println(comparablePersonSet);



    }

    public  static void addPersons(Collection collection){
        collection.add(new ComparablePerson(0,"Lindsey"));
        collection.add(new ComparablePerson(5,"Morgan"));
        collection.add(new ComparablePerson(3,"Emilia"));
        collection.add(new ComparablePerson(4,"Clarke"));
        collection.add(new ComparablePerson(2,"Tracey"));
        collection.add(new ComparablePerson(7,"Alysia"));
    }

    public static void showCollection(Collection collection){
        for(Object col:collection){
            System.out.println(col);
        }
    }

    public static class StringLengthAndAlphabet implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if(o1.length()>o2.length())
                return 1;
            else if(o1.length()<o2.length())
                return -1;
            else {
                for (int i = 0; i < o1.length(); i++) {
                    if (o1.charAt(i) > o2.charAt(i))
                        return 1;
                    else if(o1.charAt(i) < o2.charAt(i))
                        return -1;
                }
            }
            return 0;
        }
    }
}

