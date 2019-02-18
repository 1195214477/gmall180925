package com.atguigu.exer.ticket;

@FunctionalInterface
interface Foo
{
    //public void sayHello();

    public int add(int x,int y);

    public default int div(int x,int y){
        return x/y;
    }

    public static int mul(int x,int y){
        return x*y;
    }
}

public class LambdaExpressDemo
{
    public static void main(String[] args)
    {
/*        Foo foo = new Foo() {
            @Override
            public int add(int x, int y) {
                return 0;
            }*/

/*            @Override
            public void sayHello() {
                System.out.println("********hello 0925");
            }
        };
        foo.sayHello();*/

/*        foo = () -> {System.out.println("********hello 0925 lambda Express");};
        foo.sayHello();*/

        Foo foo = (int x,int y) ->
        {
            System.out.println("come in");
            return x + y;
        };
        System.out.println(foo.add(3,5));
        System.out.println(foo.div(10,5));
        System.out.println(Foo.mul(2,2));
    };
}
