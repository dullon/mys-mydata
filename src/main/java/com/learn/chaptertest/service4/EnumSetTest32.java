package com.learn.chaptertest.service4;

import java.util.Set;

/**
 * 31 用实例域代替序数。注：永远不要根据枚举的序数导出与它关联的值，而是要将它保存在一个实例域中，此种场景很少见到，除非专门写关于此例的用法。这里不进行赘述。
 * 32 用EnmuSet代替位域
 *这个类实现Set接口，提供了丰富的功能、类型安全性，以及可以从任何其他Set实现中得到的互用性。但是在内部具体的实现上，每个EnumSet就是用单个long来表示，因此它的性能比得上位域的性能。批处理，如removeAll何retainAll，都是利用位算法来实现的，就像手工替位域实现的那样。但是可以避免手工位操作时容易出现的错误以及不大雅观的代码，因为EnumSet替你完成了这项艰巨的工作。
 */
public class EnumSetTest32 {
    public enum Style {BOLD , ITALIC , UNDERLINE , STRIKETHROUGH}
    public void applyStyles(Set<Style> styles) {  }
}
// enumSetTest32.applyStyles(EnumSet.of(Style.BOLD , Style.ITALIC));
// 因为枚举类型要用在集合（Set）中，所以没有理由用位域来表示他。