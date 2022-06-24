@FunctionalInterface
public interface MyFirstInterface {

    public int functionalInterface(int number);


//    default int returnNumber(int number){
//        return 20;
//    }

//    private void checkSomething(String value){
//        // ....
//    }

    abstract class Figure{
        public abstract void draw();
    }

    class Circle extends Figure{

        @Override
        public void draw() {
            System.out.println("Drawing a circle5");
        }
    }


}
