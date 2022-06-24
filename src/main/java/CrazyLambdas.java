import java.math.BigDecimal;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.*;

public class CrazyLambdas {

    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    public static Supplier<String> helloSupplier() {
        return () -> "Hello";
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */

    public static Predicate<String> emptyString = String::isEmpty; //(myString) -> myString.isEmpty();

/*
    public static void main(String[] args) {
        String str = "";
        boolean result = emptyString.test(str);
        System.out.println(result);
    }*/

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */

    public static BiFunction<String, Integer, String> stringMultiplier = (s, n) -> {
        String result = "";
        while (n > 0) {
            result += s + " ";
            n--;
        }
        return result;
    };

/*    public static void main(String[] args) {
        String test = "Test";
        Integer n = 3;
        System.out.println(stringMultiplier.apply(test,n));
    }*/

    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    public static Function<BigDecimal, String> toDollarStringFunction = (amount) -> {
        String result;
        result = "$" + amount;
        return result;
    };

   /* public static void main(String[] args) {
        BigDecimal amount = BigDecimal.valueOf(200);
        System.out.println(toDollarStringFunction.apply(amount));
        System.out.println(amount);
    }*/

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */

    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return (s) -> s.length() >= min && s.length() < max;
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    public static Supplier<Integer> randomIntSupplier() {
        Random random = new Random();
        return random::nextInt; //() -> random.nextInt();
    }

  /*  public static void main(String[] args) {
        Integer myInteger = randomIntSupplier().get();

        System.out.println(myInteger);
    }
*/

    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */

    public static IntUnaryOperator boundedRandomIntSupplier = (bound) -> {
        Random random = new Random();
        return random.nextInt(bound);
    };

    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    public static IntUnaryOperator intSquareOperation = (number) -> {
        return number * number;
    };

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    public static LongBinaryOperator longSumOperation = Long::sum; // (a+b) -> a+b;

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    public static ToIntFunction<String> stringToIntConverter = Integer::parseInt; // (str) -> Integer.parseInt(str);

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */

    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        IntUnaryOperator function = x -> n * x;
        return () -> function;
    }

/*
    public static void main(String[] args) {
        System.out.println(nMultiplyFunctionSupplier(7).get().applyAsInt(4));
    }
*/


    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */

    public static Function<String, String> myTrim = (s) -> {
        String result = s;
        int index = 0;
        while (result.charAt(index) == ' ') {
            result = result.substring(index + 1);
            index++;
        }
        while (result.lastIndexOf(' ') == result.length() - 1) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    };
    //POSIBIL SA FI INTELES ENUNTUL GRESIT :DDD

    public static Function<String, String> strFunction = (s) -> {
        return s.toUpperCase();
    };


    public static UnaryOperator<Function<String, String>> composeWithTrimFunction = (strToStrFunc) -> {
        return strFunction.compose(myTrim);
    };

/*
    public static void main(String[] args) {
        String test = " am sa incerc sa trim-uiesc textul asta   ";
        System.out.println(myTrim.apply(test));
    }
*/

    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Supplier<Thread>}. The thread will be started only
     * when you call supplier method {@link Supplier#get()}
     *
     * @param runnable the code you want to tun in new thread
     * @return a thread supplier
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        return () -> new Thread(runnable);

    }

    /**
     * Returns a {@link Consumer} that accepts {@link Runnable} as a parameter and runs in in a new thread.
     *
     * @return a runnable consumer
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {

        return Thread::new; //(runnable) -> new Thread(runnable)
    };

    /**
     * Returns a {@link Function} that accepts an instance of {@link Runnable} and returns a {@link Supplier} of a
     * started {@link Thread} that is created from a given {@link Runnable}
     *
     * @return a function that transforms runnable into a thread supplier
     */

    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
    return runnable -> Thread::new; // new Thread();
    }

    /**
     * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */

    public static IntUnaryOperator reduceOne = number -> number - 1;
    public static IntPredicate isOdd = number -> number % 2 == 1;

    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction = (intUnaryOp, predicateInt) -> {
        return (number) -> predicateInt.test(number) ? intUnaryOp.applyAsInt(number) : number;
    };

/*        public static void main(String[] args){
        Integer n = 11;
            System.out.println(functionToConditionalFunction.apply(reduceOne,isOdd).applyAsInt(n));
        }*/

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader = (functionsMap, functionName) -> {
        if (functionsMap.containsKey(functionName)) {
            return functionsMap.get(functionName);
        } else {
            return IntUnaryOperator.identity();
        }
    };

/*    public static void main(String[] args) {
        Map<String,IntUnaryOperator> myMap = new HashMap<>();
        myMap.put("Plus1",(number) -> number+1);
        myMap.put("Plus2",(number) -> number+2);

        System.out.println(functionLoader.apply(myMap,"Plus2").applyAsInt(5));;
    }*/

    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE".
     *
     * @return a supplier instance
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier = () -> () -> () -> "WELL DONE";

/*    public static void main(String[] args) {
        System.out.println(trickyWellDoneSupplier.get().get().get());
    }*/
}
