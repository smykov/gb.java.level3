package lesson1.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // A<-B<-C<-D<-E
        List<String> stringList = new ArrayList<>();

        List<A> aList = new ArrayList<>();
        Collections.addAll(aList, new A(), new D());
        List<B> bList = new ArrayList<>();
        List<C> cList = new ArrayList<>();
        List<D> dList = new ArrayList<>();
        List<E> eList = new ArrayList<>();
//        Collections.addAll(eList, new E(), new A());// error
        Collections.addAll(eList, new E());

        extendsA(aList);
        extendsA(eList);
//        listA(stringList);//error stringList не является наследником А класса

//        listC(aList);// error aList не является наследником класса C и ниже
        extendsC(cList);
        extendsC(eList);

        superC(aList);
        superC(cList);
//        superC(eList);// error aList не является наследником класса C и выше

        extendBAndSuperD(bList, dList);

        extendBTwoArgs(bList, cList);

//        extendBTwoArgs2(bList, cList);//error только одинаковые классы можно
        extendBTwoArgs2(bList, bList);
        extendBTwoArgs2(cList, cList);

        // T == class C
        // ? super T == class A, class B, class C
//        Collections.addAll(cList, new C(), new D(), new E());
//        Collections.addAll(bList, new C(), new D(), new E());
//        Collections.addAll(aList, new C(), new D(), new E());
//        Collections.addAll(cList, new A(), new B());

        // PECS
        // producer extends consumer super
        // полставщик       потребитель

        migrateDate(cList, cList);
        migrateDate(dList, bList);

        Comparator<B> bComparator = null;
        Comparator<D> dComparator = null;

        cList.sort(bComparator);
//        cList.sort(dComparator); // error

        //List<C> cList, c, d ,e могут входить в cList
        //Comparator<B> умеет сравнивать объекты B, C
        //Comparator<D> не умеет сравнивать объекты C

    }
    private static <T> void migrateDate(List<? extends T> from, List<? super T> to) {
        // перенос данных из from в to
        to.addAll(from);
    }
    private static void extendsA(List<? extends A> list) {}
    // C, D, E. все что ниже C включительно
    private static void extendsC(List<? extends C> list) {}
    // C, B, A. все что выше C включительно
    private static void superC(List<? super C> list) {}
    private static void extendBAndSuperD(List<? extends B> first, List<? super D> second){}
    private static void extendBTwoArgs(List<? extends B> first, List<? extends B> second){}
    private static <T extends B> void extendBTwoArgs2(List<T> first, List<T> second){}
}
