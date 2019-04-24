package com.learn.chaptertest.service4;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 33 用EnumMap代替序数索引
 */
public class EnumMapTest33 {
    //利用ordinal方法来索引数组的代码。例如下面这个简化的类，表示一种烹饪用的香草：

    static class Herb {
        public enum Type { ANNUAL, PERENNIAL, BIENNIAL }

        private final String name;
        private final Type type;

        Herb(String name, Type type) {
            this.name = name;
            this.type = type;
        }
        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        Herb[] garden = {new Herb("a", Herb.Type.ANNUAL), new Herb("b", Herb.Type.BIENNIAL),new Herb("c",Herb.Type.BIENNIAL)};
        //1.将集合放到一个按照类型的序数进行索引的数组中实现：
        //这种方法可行，但是由于数组与泛型不兼容，需要进行未受检的转换。
        Set<Herb>[] herbsByType = (Set<Herb>[]) new Set[Herb.Type.values().length];//危险性强转
        for(int i=0; i < herbsByType.length; i++)
            herbsByType[i] = new HashSet<Herb>();
        for(Herb h : garden)
            herbsByType[h.type.ordinal()].add(h);
        for(int i=0; i < herbsByType.length; i++)
            System.out.printf("%s: %s%n", Herb.Type.values()[i], herbsByType[i]);
        System.out.println("---------------------------------");
        //2.更好的方法，数组实际上充当从枚举到值的映射，EnumMap提供这样的实现：不存在不安全的类型转换。
        Map<Herb.Type, Set<Herb>> herbByType = new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
        for(Herb.Type t : Herb.Type.values())
            herbByType.put(t, new HashSet<Herb>());
        for(Herb h : garden)
            herbByType.get(h.type).add(h);
        System.out.println(herbByType);
    }
}
//按照序数进行两次索引的数组的数组,EnumMap可以做的更好,不需要平方级大小的数组。
enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID,LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase src;
        private final Phase dst;

        Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }

        private static final Map<Phase, Map<Phase, Transition>> m =
                new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
        static {
            for(Phase p : Phase.values())
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            for(Transition t : Transition.values())
                m.get(t.src).put(t.dst, t);
        }

        public static Transition from(Phase src, Phase dst) {
            return m.get(src).get(dst);
        }
    }
}


