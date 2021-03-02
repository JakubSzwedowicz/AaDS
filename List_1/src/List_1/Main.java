package List_1;

import CodeTogether.CodeTogether;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // Tests are better though
    public static void main(String[] args) {
        MyArrayList.testClass();
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//        CodeTogether<Integer> array = new CodeTogether<>(new Integer[]{1, 2, 3, 4, 5});
//        for(int i : array){
//            System.out.println(i);
//        }

//        Iterator<Integer> it = list.iterator();
//        while(it.hasNext()){
//            int i = it.next();
//            System.out.println(i);
//        }
    }
}
