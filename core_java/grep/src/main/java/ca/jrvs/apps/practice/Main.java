package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    LambdaStreaImp lsp  = new LambdaStreaImp();
    Stream <String> stream = lsp.toUpperCase("naruto","Haruto","Hashiro");
  //  Stream <String> stream = lsp.createStrStream("naruto","haruto","hashiro ") ;
 //   Stream <String>  filtst = lsp.filter(stream,"uto") ;

    int [] arr = {1,2,3};
   // IntStream intStream = lsp.createIntStream(arr);

    //
     // converting a genereic stream to a generic list
   // List<String> lststr = lsp.toList(stream);
   // List <Integer> lstint = lsp.toList(intStream);

    IntStream intStream = lsp.createIntStream(2,15);
   // DoubleStream dbls = lsp.squareRootIntStream(intStream);
   // dbls.forEach(System.out::println);
   // IntStream oddstream = lsp.getOdd(intStream);

    //oddstream.forEach(System.out::println);
    Consumer <String> fs = lsp.getLambdaPrinter("strar>","end") ;
   // fs.accept("LOLOLOLLOOOOOOL");

   // lsp.printMessages(new String[]{"one", "two", "three"},fs);
   // lsp.printOdd(lsp.createIntStream(0,9), lsp.getLambdaPrinter("odd :","!!"));


    List <Integer> l1 = new ArrayList<>() ;
    List<Integer> l2 = new ArrayList<>();
    List<Integer> l3 = new ArrayList<>();
    List<List<Integer>> ln= new ArrayList<>();
    l1.add(1);
    l1.add(2);
    l1.add(3);
    l2.add(4);
    l2.add(5);
    l2.add(6);
    l3.add(7);
    l3.add(8);
    l3.add(9);
    ln.add(l1);
    ln.add(l2);
    ln.add(l3);

    Stream<Integer> stnn = lsp.flatNestedInt(ln.stream());
    stnn.forEach(System.out::println);
  }

}
