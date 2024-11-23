public class StreamAPI {
}

package tatha.tuotorial.demo;

        import ch.qos.logback.core.net.SyslogOutputStream;
        import org.hibernate.Length;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.ComponentScan;
        import tatha.tuotorial.demo.entity.Book;
        import tatha.tuotorial.demo.service.*;

        import java.io.PrintStream;
        import java.time.Clock;
        import java.util.*;
        import java.util.function.Function;
        import java.util.stream.Collectors;
        import java.util.stream.IntStream;
        import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Atanu", "Brijesh", "Tathagata", "Sayan", "Anirban");
        String x = names.stream().max(Comparator.comparing(String::length)).get();
        List<String> result = names.stream().filter(name -> name.toLowerCase().startsWith("s")).toList();
        System.out.println("Starts with S " + result);
        List<String> reverse = names.stream().map(s -> new StringBuilder(s).reverse().toString()).toList();
        System.out.println("Reverse is " + reverse);
        List<Integer> number = Arrays.asList(20, 3, 4, 5, 6, 7, 8, 9, 10);
        IntStream.rangeClosed(1,number.toArray().length).mapToObj(i->number.toArray()[number.toArray().length-i]).
                toArray();
        System.out.println("ParallelStream ");
        number.parallelStream().forEach(System.out::println);
        System.out.println("ParallelStream forEachOrdered");
        number.parallelStream().forEachOrdered(System.out::println);
        List<List<Integer>> twoDList = new ArrayList<List<Integer>>();
        twoDList.add(Arrays.asList(1, 2, 3));
        twoDList.add(Arrays.asList(5, 6, 3));
        twoDList.add(Arrays.asList(7, 0, 9));
        System.out.println("Distinct Obj from list");
        twoDList.stream().flatMap(a -> a.stream()).distinct().sorted().forEach(System.out::println);
        System.out.println("Make even list");
        List<Integer> evenList = number.stream().filter(word -> word % 2 == 0).toList();
        number.stream().filter(word -> word % 2 == 0).toList().forEach(System.out::println);
        System.out.println("Even List = " + evenList);
        List<Integer> oddList = number.stream().filter(a -> a % 2 == 1).collect(Collectors.toList());
        System.out.println("Odd List = " + oddList);
        int max;
        max = number.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println("Max Number = " + max);
        Integer arr[] = {6, 1, 3, 1, 5, 6, 4, 2, 5, 5, 5, 5};
        List<Integer> arrLst = Arrays.asList(arr);
        List<Integer> arrLst2 = twoDList.stream().flatMap(a->a.stream()).toList();
//    arrLst.stream().forEach(a->{
//       if(arrLst2.contains(a)){
//          System.out.println(a);
//
//       }
//    });
        arrLst.stream().filter(arrLst2::contains).distinct().toList().forEach(System.out::println);

        System.out.println("Sum of Even no");
        int sumEvn = arrLst.stream().filter(a -> a % 2 == 0).toList().stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum of Even no =" + sumEvn);
        int sumOdd = arrLst.stream().filter(a -> a % 2 == 1).toList().stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum of Odd no =" + sumOdd);
        System.out.println("Compare to sorting Descending");
        arrLst.stream().sorted((a1, a2) -> a2.compareTo(a1)).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("Compare to sorting Ascending");
        arrLst.stream().sorted((a1, a2) -> a1.compareTo(a2)).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("Compare to sorting default");
        arrLst.stream().sorted().distinct().forEach(System.out::println);
        List<Integer> duplicates = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        System.out.println("Duplicate Occurrence frequency");
        Map<Integer, Long> mp = arrLst.stream()
                .filter(i -> Collections.frequency(arrLst, i) > 1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Integer, Long> ent : mp.entrySet()) {
            System.out.println("Key = " + ent.getKey() + " Value = " + ent.getValue());
        }
        System.out.println("Break IV");
        Map<Integer, Long> mp_2 = arrLst.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Integer, Long> ent : mp_2.entrySet()) {
            System.out.println("Key = " + ent.getKey() + " Value = " + ent.getValue());
        }
        mp_2.entrySet().stream().filter(a->a.getValue().getId()==a.getKey().get)
        System.out.println("Reduce");
        int sum = arrLst.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum =" + sum);
        List<Integer> newLst = arrLst.stream().distinct().map(a -> {
            if (a.intValue() > 5) {
                System.out.println("Greater than 5 - " + a.intValue());
            } else {
                System.out.println("Less than 5 - " + a.intValue());
            }
            return a;
        }).toList();
        newLst.stream().toList().forEach(System.out::println);
        System.out.println("Comparable");
        List<Book> bkLst = new ArrayList<Book>();
        bkLst.add(new Book(111, "aNewBook1", "tatha1"));
        bkLst.add(new Book(10, "aNewBook", "tatha"));
        bkLst.add(new Book(1311, "bNewBook300", "tatha3"));
        bkLst.add(new Book(12999, "dNewBook20", "tatha2756565"));

        Collections.sort(bkLst);
        //bkLst.stream().forEach(System.out::println);
        bkLst.stream().filter(a -> a.id % 2 == 1).toList().forEach(System.out::println);
        int total = bkLst.stream().mapToInt(Book::getId).
                reduce(0, (a, b) -> a + b);
        System.out.println("Total Id =" + total);
        System.out.println("Sorted");
        bkLst.stream().sorted().toList().forEach(System.out::println);
        bkLst.stream().sorted(Collections.reverseOrder()).toList().forEach(System.out::println);
        System.out.println("Longest name");
        bkLst.stream().sorted(Comparator.comparing(a -> a.getName().length(), Collections.reverseOrder())).forEach(System.out::println);
        String maxAuthLen = bkLst.stream().reduce((a1, a2) -> a1.getAuthor().length() > a2.getAuthor().length() ?
                a1 : a2).getClass().getName().toString();
        int maxId = bkLst.stream().mapToInt(Book::getId).reduce(0, (a, b) -> Integer.max(a, b));
        //String s = bkLst.stream().max((a,b)-> Integer.parseInt(a.getName().length()>b.getName().length()?a.getName():b.getName()));
        System.out.println("Max Id = " + maxId);
        System.out.println("Longest Author = " + maxAuthLen);
        System.out.println("Concat String");
        bkLst.stream().map(a -> a.getAuthor().concat(" Banerjee")).toList().forEach(System.out::println);
        System.out.println("Starts with A");
        bkLst.stream().filter(a -> a.getName().startsWith("a")).toList().forEach(System.out::println);
        System.out.println("-----------------------------");
        List<String> lst = Arrays.asList("Russia", "India", "China", "Japan","China","india", "", "Ghana");
        List<String> lst2 =  lst.stream().sorted().distinct().filter(a -> a!=null && !a.isEmpty())
                .collect(Collectors.toList());
        lst2.forEach(System.out::println);
        System.out.println("-----------------------------");
        lst2.stream().filter(a->Character.isUpperCase(a.charAt(0))).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------------");
        lst.stream().sorted().distinct().filter(a -> a!=null && !a.isEmpty())
                .collect(Collectors.toList()).stream().filter(a->Character.isUpperCase(a.charAt(0)))
                .toList().forEach(System.out::println);
        lst.stream().max(Comparator.comparing(String::length)).get();

        StringJoiner joiner = new StringJoiner(",", "Prefix-", "-Suffix");
        joiner.add("Sunday");
        joiner.add("Monday");
        joiner.add("Tuesday");
        joiner.add("Wednesday");

        //display output
        System.out.println("Test String Joiner "+joiner.toString());

        //    Parent p1 = new Child1();
//    Child1 c1 = new Child1();
//    Child2 c2 = new Child2();
//
//    p1.func1("X","Y");
//    c1.func1("X","Y");
//    c2.func1("X","Y");
//    System.out.println("Break IV");
//    duplicates.stream().forEach(System.out::println);


        SpringApplication.run(DemoApplication.class, args);
//    fibOfCount(10);
//    printTriangle(10);
    }
    public int returnFive(){
        return 5;
    }
    public int addFive(int x){
        return returnFive()+x;
    }

    public static void fibOfCount(int n){
        int n1 = 0;
        int n2 = 1;
        int n3 = 0;
        int count = n;
        System.out.print(n1+","+n2);
        for(int i=2;i<n;i++) {
            n3 = n1 + n2;
            System.out.print("," + n3 );
            n1 = n2;
            n2 = n3;
        }
    }
    public static void printTriangle(int row){
        System.out.println("");
        for(int i =0; i<=row;i++){
            //System.out.print(" ");
            for(int j=row;j>i;j--){
                System.out.print(" ");
            }

            for(int k =0;k<i;k++){
                System.out.print("* ");
            }
            System.out.println(" ");
        }
    }

}

