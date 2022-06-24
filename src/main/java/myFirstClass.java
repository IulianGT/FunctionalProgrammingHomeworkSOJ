import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class myFirstClass implements myFirstInterface {

    private static String name;
    @Override
    public int functionalInterface(int number) {
//        MyFirstInterface myFirstInterface = new MyFirstInterface() {
//            @Override
//            public void functionalInterface(int number) {
//                  int var = 15;
//                  return number * var;
//            }
//        };
        int param = 10;
        final int param2 = param;
        //                    (int n)
        myFirstInterface value = n -> n * 10;
        myFirstInterface value2 = n ->{
            int var = param2;
//          param++; (SHOULD NEVER USE IT LIKE THAT. Param must be final
            return n * var;
        };
        param++;
        System.out.println(value2.functionalInterface(15));

        return 10;
    }

    public static void main(String[] args) {

        //Predicate to dictate true or false statements
        Predicate<Integer> predicate = n -> true;
        Predicate<Integer> predicate1 = n -> {
            return n%2 ==0;
            //retunr nu e abs necesar pt ca e o singura linie.
            //int bigNumber = n * 10;
            //return bigNumber % 7 == 0;
        };
        System.out.println(predicate1.test(6));


        //Consumer is used to do something over an array
        Consumer<Integer> myConsumer;

        List<Integer> myList = List.of(10,5,7);
        myList.forEach(n-> System.out.println(n));

        // Supplier e opus lui Consumer

        Supplier<String> mySupplier = () ->{
            if( name.isEmpty() ){
                return "Empty name";
            }
            else{
                return "NOT empty";
            }
        };

        Function<String, Integer> myFunction = (value) -> Integer.parseInt(value);
//        Optional<String> optionlaString = Optional.ofNullable(myMethod());
//        optionlaString.orElse("ceva");
//        optionlaString.orElseThrow(IllegalArgumentException::new);

    }

    class MyPredicate implements Predicate<Integer>{
        public boolean test(Integer integer){
            int bigNumber = integer * 10;
            return (bigNumber & 7) == 0;
        }
    }
}
