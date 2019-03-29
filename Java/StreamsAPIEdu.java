import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Map;

/**
 *  About streams of JAVA8 ...
 *  
 *    We can transform a collection to a stream with .stream() method
 *  And turn it back to a collection with .collect() method
 * 
 *    Using streams makes manipulating collections easier.
 *  When a collection is turned into a stream, called methods on this 
 *  stream are applied element-wise this enables omitting loops to manipulate
 *  collection data 
 *
 *  Visualization:
 *  
 *  - List to stream
 *  List < [1, 2, 3, 4,.....] > .stream() ---> stream [1-2-3-4-....] .#ElementWiseMethods()
 *  
 *  - Stream to list
 *  stream [1-2-3-4-....] .collect(.toList()) ---> List < [1, 2, 3, 4,.....] >
 * 
 *     Operations on streams are divided in 2 parts
 *     1 - Intermediate operations : These return stream objects so a chain of these 
 *                                  operations can be applied e.g. ;
 *                                  #####.stream().filter().map().......
 *
 *     2 - Terminal operations     : These return non stream objects so calling stream
 *                                  methods beyond these are invalid e.g. ;
 *                                  #####.stream().foreach(); -> no stream beyond foreach
 * 
 *     Intermediate operations are called 'lazy' meaning they wont do actual processing 
 *     until a terminal operation is called on stream. Lazy operations are chained together 
 *     and memorized and when terminal operation gets called these all get executed.
 * 
 *     Order of execution:
 * 
 *     .lazy() -> .lazy() .... -> .lazy()
 *                                .terminal()    
 * 
 *     last lazy op. is executed with terminal op. at the same time.
 * 
 *     Output operations are only handled when a terminal function called at operation pipeline !
 * 
 *  Mert Beşiktepe 2019 ITU
 */
public class StreamsAPIEdu {

    /**
     * Some applications about streams introduced in Java 8
     * 
     * @param args
     */
    public static void main(String[] args) {

        /* 1 - Conversion between streams and collections */

        // can only collect objects. So in case of primitive types you must
        // call boxed() method to wrap them in to wrapper classes like Integer
        List<Integer> listInt = IntStream.range(1, 7).boxed().collect(Collectors.toList()); 

        // print list with lambda expression
        // this is like saying :
        // for each integer in listInt call .println()
        listInt.forEach(integer -> System.out.println(integer));
        
        /* 2 - Filtering elements */

        // filtering is applied based on a rule called 'predicate' 
        // one can also define special predicates seperately.
        // first task is creating a predicate
        List<String> list1 = Arrays.asList("Kelime1","Kelime2","Kelime3");
        
        // turn it to a stream, filter and collect back into antother list this time
        List<String> result = list1.stream()
                              // filter the elements that not ending with "2"
                              .filter(kelime -> !kelime.endsWith("2"))
                              .collect(Collectors.toList());
        
        // again a lambda a expression but this time 
        // with '::' as method reference operator.
        // this is like saying
        // go to System.out class and call println on each element
        result.forEach(System.out::println);
        
        /* 3 - Mapping elements  */

        // map method is for mapping elements onto other elements.

        /* COMMON MISCONSEPTION HERE :

             map() method enables mapping streams to functions element-wise but this is not 
             the correct way to use it. Below code demonstrates this miss-use. 

             map() is a intermediate operation so this means any applied function in it
             will not do any mutation until a terminal operation. So a more correct way is
             as follows;
             
             .map(entry  -> map it to another or design an alteration on element  )
             .forEach(entry -> function);
        */

        // create a list, stream it filter and map the filtered ones to a function.
        List<String> list2 = Arrays.asList("mert","Java","tutorial");

        // stream filter map operations back to back
        // mutated value of stream will be vanished if it is not referenced
        list2.stream()
             .filter(entry -> entry.equals("mert") || entry.equals("tutorial"))
             // map the filtered stream to uppercase function
             .map(entry -> entry.toUpperCase())
             // then print it with for each
             .forEach(System.out::println);

        // No changes applied to object because stream wasn't referenced
        list2.forEach(System.out::println);
        
        // 3.1 mapping to an arbitrary lambda function FALSE WAY
        list2.stream()
             // lambda function defined in "{}"
             .map(kelime -> {
                             // this is redundant only to show we can define such things here.
                             String draft = null;
                             draft = kelime;
                             for (char harf : draft.toCharArray()) {
                                 System.out.format("++++ %c ++++",harf);
                                }
                             return kelime;
                            } )
             // calling a terminal function to see the results
             .forEach(System.out::println);

        // 3.2 manipulating streams of arrays FALSE WAY
        
        // Create a list of arrays then form a HasMap based on entries of list
        List<String[]> list3 = new ArrayList<String[]>();
        String[] entry1 = {"a","b","c"};
        String[] entry2 = {"d","e","f"};
        String[] entry3 = {"g","h","i"};
        list3.add(entry1);
        list3.add(entry2);
        list3.add(entry3);

        // hashmap
        HashMap<String, String[]> map1 = new HashMap<String, String[]>();

        // streamize the list to form hashmap
        list3.stream()
             .map(entry ->  {
                                String key = entry[0];
                                String[] value = {entry[1],entry[2]};
                                map1.put(key, value);
                                // must return a String[]
                                return entry;
                            })
             // calling an empty terminal function to apply changes
             .forEach(entry -> {});

        // print elements
        for (Map.Entry<String,String[]> entry : map1.entrySet()) {
            System.out.println(entry);
        }

        /* 4 - Collectiong to HashMap */

        // We will group the list3 to a hashmap using correct way this time
        // list3 = ArrayList<String[]> --> map2 = HashMap<String,String[]>

        // streamize the list and collect to map 
        Map<String,String[]> map2 = list3.stream()
                                             // collecting
                                             .collect(Collectors.toMap(entry -> entry[0], 
                                                                       entry -> new String[] {entry[1], entry[2]}));
                       
        // print elements
        for (Map.Entry<String,String[]> entry : map2.entrySet()) {
             System.out.println(entry);
        }
        
        
        
    }
}
