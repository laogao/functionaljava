package fj.data;

import fj.Effect;
import fj.F;
import static fj.P.p;

import fj.Function;
import fj.P1;
import fj.P2;

import static fj.data.List.list;
import static fj.data.Option.some;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.util.EnumSet.copyOf;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Functions that convert between types from the core Java API.
 *
 * @version %build.number%
 */
public final class Java {
  private Java() {
    throw new UnsupportedOperationException();
  }

  // BEGIN List ->

  /**
   * A function that converts lists to array lists.
   *
   * @return A function that converts lists to array lists.
   */
  public static <A> F<List<A>, ArrayList<A>> List_ArrayList() {
    return new F<List<A>, ArrayList<A>>() {
      public ArrayList<A> f(final List<A> as) {
        return new ArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to bit sets.
   */
  public static final F<List<Boolean>, BitSet> List_BitSet = new F<List<Boolean>, BitSet>() {
    public BitSet f(final List<Boolean> bs) {
      final BitSet s = new BitSet(bs.length());
      bs.zipIndex().foreach(new Effect<P2<Boolean, Integer>>() {
        public void e(final P2<Boolean, Integer> bi) {
          s.set(bi._2(), bi._1());
        }
      });
      return s;
    }
  };

  /**
   * A function that converts lists to array enum sets.
   *
   * @return A function that converts lists to enum sets.
   */
  public static <A extends Enum<A>> F<List<A>, EnumSet<A>> List_EnumSet() {
    return new F<List<A>, EnumSet<A>>() {
      public EnumSet<A> f(final List<A> as) {
        return copyOf(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to hash sets.
   *
   * @return A function that converts lists to hash sets.
   */
  public static <A> F<List<A>, HashSet<A>> List_HashSet() {
    return new F<List<A>, HashSet<A>>() {
      public HashSet<A> f(final List<A> as) {
        return new HashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to linked hash sets.
   *
   * @return A function that converts lists to linked hash sets.
   */
  public static <A> F<List<A>, LinkedHashSet<A>> List_LinkedHashSet() {
    return new F<List<A>, LinkedHashSet<A>>() {
      public LinkedHashSet<A> f(final List<A> as) {
        return new LinkedHashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to linked lists.
   *
   * @return A function that converts lists to linked lists.
   */
  public static <A> F<List<A>, LinkedList<A>> List_LinkedList() {
    return new F<List<A>, LinkedList<A>>() {
      public LinkedList<A> f(final List<A> as) {
        return new LinkedList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to priority queues.
   *
   * @return A function that converts lists to priority queues.
   */
  public static <A> F<List<A>, PriorityQueue<A>> List_PriorityQueue() {
    return new F<List<A>, PriorityQueue<A>>() {
      public PriorityQueue<A> f(final List<A> as) {
        return new PriorityQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to stacks.
   *
   * @return A function that converts lists to stacks.
   */
  public static <A> F<List<A>, Stack<A>> List_Stack() {
    return new F<List<A>, Stack<A>>() {
      public Stack<A> f(final List<A> as) {
        final Stack<A> s = new Stack<A>();
        s.addAll(as.toCollection());
        return s;
      }
    };
  }

  /**
   * A function that converts lists to stacks.
   *
   * @return A function that converts lists to stacks.
   */
  public static <A> F<List<A>, TreeSet<A>> List_TreeSet() {
    return new F<List<A>, TreeSet<A>>() {
      public TreeSet<A> f(final List<A> as) {
        return new TreeSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to vectors.
   *
   * @return A function that converts lists to vectors.
   */
  public static <A> F<List<A>, Vector<A>> List_Vector() {
    return new F<List<A>, Vector<A>>() {
      @SuppressWarnings({"UseOfObsoleteCollectionType"})
      public Vector<A> f(final List<A> as) {
        return new Vector<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to array blocking queue.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts lists to array blocking queue.
   */
  public static <A> F<List<A>, ArrayBlockingQueue<A>> List_ArrayBlockingQueue(final boolean fair) {
    return new F<List<A>, ArrayBlockingQueue<A>>() {
      public ArrayBlockingQueue<A> f(final List<A> as) {
        return new ArrayBlockingQueue<A>(as.length(), fair, as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to concurrent linked queues.
   *
   * @return A function that converts lists to concurrent linked queues.
   */
  public static <A> F<List<A>, ConcurrentLinkedQueue<A>> List_ConcurrentLinkedQueue() {
    return new F<List<A>, ConcurrentLinkedQueue<A>>() {
      public ConcurrentLinkedQueue<A> f(final List<A> as) {
        return new ConcurrentLinkedQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to copy on write array lists.
   *
   * @return A function that converts lists to copy on write array lists.
   */
  public static <A> F<List<A>, CopyOnWriteArrayList<A>> List_CopyOnWriteArrayList() {
    return new F<List<A>, CopyOnWriteArrayList<A>>() {
      public CopyOnWriteArrayList<A> f(final List<A> as) {
        return new CopyOnWriteArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to copy on write array sets.
   *
   * @return A function that converts lists to copy on write array sets.
   */
  public static <A> F<List<A>, CopyOnWriteArraySet<A>> List_CopyOnWriteArraySet() {
    return new F<List<A>, CopyOnWriteArraySet<A>>() {
      public CopyOnWriteArraySet<A> f(final List<A> as) {
        return new CopyOnWriteArraySet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to delay queues.
   *
   * @return A function that converts lists to delay queues.
   */
  public static <A extends Delayed> F<List<A>, DelayQueue<A>> List_DelayQueue() {
    return new F<List<A>, DelayQueue<A>>() {
      public DelayQueue<A> f(final List<A> as) {
        return new DelayQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to linked blocking queues.
   *
   * @return A function that converts lists to linked blocking queues.
   */
  public static <A> F<List<A>, LinkedBlockingQueue<A>> List_LinkedBlockingQueue() {
    return new F<List<A>, LinkedBlockingQueue<A>>() {
      public LinkedBlockingQueue<A> f(final List<A> as) {
        return new LinkedBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to priority blocking queues.
   *
   * @return A function that converts lists to priority blocking queues.
   */
  public static <A> F<List<A>, PriorityBlockingQueue<A>> List_PriorityBlockingQueue() {
    return new F<List<A>, PriorityBlockingQueue<A>>() {
      public PriorityBlockingQueue<A> f(final List<A> as) {
        return new PriorityBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts lists to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts lists to synchronous queues.
   */
  public static <A> F<List<A>, SynchronousQueue<A>> List_SynchronousQueue(final boolean fair) {
    return new F<List<A>, SynchronousQueue<A>>() {
      public SynchronousQueue<A> f(final List<A> as) {
        final SynchronousQueue<A> q = new SynchronousQueue<A>(fair);
        q.addAll(as.toCollection());
        return q;
      }
    };
  }

  // END List ->

  // BEGIN Array ->

  /**
   * A function that converts arrays to array lists.
   *
   * @return A function that converts arrays to array lists.
   */
  public static <A> F<Array<A>, ArrayList<A>> Array_ArrayList() {
    return new F<Array<A>, ArrayList<A>>() {
      public ArrayList<A> f(final Array<A> as) {
        return new ArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to bit sets.
   */
  public static final F<Array<Boolean>, BitSet> Array_BitSet = new F<Array<Boolean>, BitSet>() {
    public BitSet f(final Array<Boolean> bs) {
      final BitSet s = new BitSet(bs.length());

      bs.zipIndex().foreach(new Effect<P2<Boolean, Integer>>() {
        public void e(final P2<Boolean, Integer> bi) {
          s.set(bi._2(), bi._1());
        }
      });
      return s;
    }
  };

  /**
   * A function that converts arrays to enum sets.
   *
   * @return A function that converts arrays to enum sets.
   */
  public static <A extends Enum<A>> F<Array<A>, EnumSet<A>> Array_EnumSet() {
    return new F<Array<A>, EnumSet<A>>() {
      public EnumSet<A> f(final Array<A> as) {
        return copyOf(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to hash sets.
   *
   * @return A function that converts arrays to hash sets.
   */
  public static <A> F<Array<A>, HashSet<A>> Array_HashSet() {
    return new F<Array<A>, HashSet<A>>() {
      public HashSet<A> f(final Array<A> as) {
        return new HashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to linked hash sets.
   *
   * @return A function that converts arrays to linked hash sets.
   */
  public static <A> F<Array<A>, LinkedHashSet<A>> Array_LinkedHashSet() {
    return new F<Array<A>, LinkedHashSet<A>>() {
      public LinkedHashSet<A> f(final Array<A> as) {
        return new LinkedHashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to linked lists.
   *
   * @return A function that converts arrays to linked lists.
   */
  public static <A> F<Array<A>, LinkedList<A>> Array_LinkedList() {
    return new F<Array<A>, LinkedList<A>>() {
      public LinkedList<A> f(final Array<A> as) {
        return new LinkedList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to priority queues.
   *
   * @return A function that converts arrays to priority queues.
   */
  public static <A> F<Array<A>, PriorityQueue<A>> Array_PriorityQueue() {
    return new F<Array<A>, PriorityQueue<A>>() {
      public PriorityQueue<A> f(final Array<A> as) {
        return new PriorityQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to stacks.
   *
   * @return A function that converts arrays to stacks.
   */
  public static <A> F<Array<A>, Stack<A>> Array_Stack() {
    return new F<Array<A>, Stack<A>>() {
      public Stack<A> f(final Array<A> as) {
        final Stack<A> s = new Stack<A>();
        s.addAll(as.toCollection());
        return s;
      }
    };
  }

  /**
   * A function that converts arrays to tree sets.
   *
   * @return A function that converts arrays to tree sets.
   */
  public static <A> F<Array<A>, TreeSet<A>> Array_TreeSet() {
    return new F<Array<A>, TreeSet<A>>() {
      public TreeSet<A> f(final Array<A> as) {
        return new TreeSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to vectors.
   *
   * @return A function that converts arrays to vectors.
   */
  public static <A> F<Array<A>, Vector<A>> Array_Vector() {
    return new F<Array<A>, Vector<A>>() {
      @SuppressWarnings({"UseOfObsoleteCollectionType"})
      public Vector<A> f(final Array<A> as) {
        return new Vector<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts arrays to array blocking queues.
   */
  public static <A> F<Array<A>, ArrayBlockingQueue<A>> Array_ArrayBlockingQueue(final boolean fair) {
    return new F<Array<A>, ArrayBlockingQueue<A>>() {
      public ArrayBlockingQueue<A> f(final Array<A> as) {
        return new ArrayBlockingQueue<A>(as.length(), fair, as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to concurrent linked queues.
   *
   * @return A function that converts arrays to concurrent linked queues.
   */
  public static <A> F<Array<A>, ConcurrentLinkedQueue<A>> Array_ConcurrentLinkedQueue() {
    return new F<Array<A>, ConcurrentLinkedQueue<A>>() {
      public ConcurrentLinkedQueue<A> f(final Array<A> as) {
        return new ConcurrentLinkedQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to copy on write array lists.
   *
   * @return A function that converts arrays to copy on write array lists.
   */
  public static <A> F<Array<A>, CopyOnWriteArrayList<A>> Array_CopyOnWriteArrayList() {
    return new F<Array<A>, CopyOnWriteArrayList<A>>() {
      public CopyOnWriteArrayList<A> f(final Array<A> as) {
        return new CopyOnWriteArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to copy on write array sets.
   *
   * @return A function that converts arrays to copy on write array sets.
   */
  public static <A> F<Array<A>, CopyOnWriteArraySet<A>> Array_CopyOnWriteArraySet() {
    return new F<Array<A>, CopyOnWriteArraySet<A>>() {
      public CopyOnWriteArraySet<A> f(final Array<A> as) {
        return new CopyOnWriteArraySet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to delay queues.
   *
   * @return A function that converts arrays to delay queues.
   */
  public static <A extends Delayed> F<Array<A>, DelayQueue<A>> Array_DelayQueue() {
    return new F<Array<A>, DelayQueue<A>>() {
      public DelayQueue<A> f(final Array<A> as) {
        return new DelayQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to linked blocking queues.
   *
   * @return A function that converts arrays to linked blocking queues.
   */
  public static <A> F<Array<A>, LinkedBlockingQueue<A>> Array_LinkedBlockingQueue() {
    return new F<Array<A>, LinkedBlockingQueue<A>>() {
      public LinkedBlockingQueue<A> f(final Array<A> as) {
        return new LinkedBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to priority blocking queues.
   *
   * @return A function that converts arrays to priority blocking queues.
   */
  public static <A> F<Array<A>, PriorityBlockingQueue<A>> Array_PriorityBlockingQueue() {
    return new F<Array<A>, PriorityBlockingQueue<A>>() {
      public PriorityBlockingQueue<A> f(final Array<A> as) {
        return new PriorityBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts arrays to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts arrays to synchronous queues.
   */
  public static <A> F<Array<A>, SynchronousQueue<A>> Array_SynchronousQueue(final boolean fair) {
    return new F<Array<A>, SynchronousQueue<A>>() {
      public SynchronousQueue<A> f(final Array<A> as) {
        final SynchronousQueue<A> q = new SynchronousQueue<A>(fair);
        q.addAll(as.toCollection());
        return q;
      }
    };
  }

  // END Array ->

  // BEGIN Stream ->

  /**
   * A function that converts streams to iterable.
   *
   * @return A function that converts streams to iterable.
   */
  public static <A> F<Stream<A>, Iterable<A>> Stream_Iterable() {
    return new F<Stream<A>, Iterable<A>>() {
      public Iterable<A> f(final Stream<A> as) {
        return new Iterable<A>() {
          public Iterator<A> iterator() {
            return new Iterator<A>() {
              private Stream<A> x = as;

              public boolean hasNext() {
                return x.isNotEmpty();
              }

              public A next() {
                if (x.isEmpty())
                  throw new NoSuchElementException("Empty iterator");
                else {
                  final A a = x.head();
                  x = x.tail()._1();
                  return a;
                }
              }

              public void remove() {
                throw new UnsupportedOperationException();
              }
            };
          }
        };
      }
    };
  }

  /**
   * A function that converts streams to array lists.
   *
   * @return A function that converts streams to array lists.
   */
  public static <A> F<Stream<A>, ArrayList<A>> Stream_ArrayList() {
    return new F<Stream<A>, ArrayList<A>>() {
      public ArrayList<A> f(final Stream<A> as) {
        return new ArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to bit sets.
   */
  public static final F<Stream<Boolean>, BitSet> Stream_BitSet = new F<Stream<Boolean>, BitSet>() {
    public BitSet f(final Stream<Boolean> bs) {
      final BitSet s = new BitSet(bs.length());
      bs.zipIndex().foreach(new Effect<P2<Boolean, Integer>>() {
        public void e(final P2<Boolean, Integer> bi) {
          s.set(bi._2(), bi._1());
        }
      });
      return s;
    }
  };

  /**
   * A function that converts streams to enum sets.
   *
   * @return A function that converts streams to enum sets.
   */
  public static <A extends Enum<A>> F<Stream<A>, EnumSet<A>> Stream_EnumSet() {
    return new F<Stream<A>, EnumSet<A>>() {
      public EnumSet<A> f(final Stream<A> as) {
        return copyOf(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to hash sets.
   *
   * @return A function that converts streams to hash sets.
   */
  public static <A> F<Stream<A>, HashSet<A>> Stream_HashSet() {
    return new F<Stream<A>, HashSet<A>>() {
      public HashSet<A> f(final Stream<A> as) {
        return new HashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to linked hash sets.
   *
   * @return A function that converts streams to linked hash sets.
   */
  public static <A> F<Stream<A>, LinkedHashSet<A>> Stream_LinkedHashSet() {
    return new F<Stream<A>, LinkedHashSet<A>>() {
      public LinkedHashSet<A> f(final Stream<A> as) {
        return new LinkedHashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to linked lists.
   *
   * @return A function that converts streams to linked lists.
   */
  public static <A> F<Stream<A>, LinkedList<A>> Stream_LinkedList() {
    return new F<Stream<A>, LinkedList<A>>() {
      public LinkedList<A> f(final Stream<A> as) {
        return new LinkedList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to priority queues.
   *
   * @return A function that converts streams to priority queues.
   */
  public static <A> F<Stream<A>, PriorityQueue<A>> Stream_PriorityQueue() {
    return new F<Stream<A>, PriorityQueue<A>>() {
      public PriorityQueue<A> f(final Stream<A> as) {
        return new PriorityQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to stacks.
   *
   * @return A function that converts streams to stacks.
   */
  public static <A> F<Stream<A>, Stack<A>> Stream_Stack() {
    return new F<Stream<A>, Stack<A>>() {
      public Stack<A> f(final Stream<A> as) {
        final Stack<A> s = new Stack<A>();
        s.addAll(as.toCollection());
        return s;
      }
    };
  }

  /**
   * A function that converts streams to tree sets.
   *
   * @return A function that converts streams to tree sets.
   */
  public static <A> F<Stream<A>, TreeSet<A>> Stream_TreeSet() {
    return new F<Stream<A>, TreeSet<A>>() {
      public TreeSet<A> f(final Stream<A> as) {
        return new TreeSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to vectors.
   *
   * @return A function that converts streams to vectors.
   */
  public static <A> F<Stream<A>, Vector<A>> Stream_Vector() {
    return new F<Stream<A>, Vector<A>>() {
      @SuppressWarnings({"UseOfObsoleteCollectionType"})
      public Vector<A> f(final Stream<A> as) {
        return new Vector<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts streams to array blocking queues.
   */
  public static <A> F<Stream<A>, ArrayBlockingQueue<A>> Stream_ArrayBlockingQueue(final boolean fair) {
    return new F<Stream<A>, ArrayBlockingQueue<A>>() {
      public ArrayBlockingQueue<A> f(final Stream<A> as) {
        return new ArrayBlockingQueue<A>(as.length(), fair, as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to concurrent linked queues.
   *
   * @return A function that converts streams to concurrent linked queues.
   */
  public static <A> F<Stream<A>, ConcurrentLinkedQueue<A>> Stream_ConcurrentLinkedQueue() {
    return new F<Stream<A>, ConcurrentLinkedQueue<A>>() {
      public ConcurrentLinkedQueue<A> f(final Stream<A> as) {
        return new ConcurrentLinkedQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to copy on write array lists.
   *
   * @return A function that converts streams to copy on write array lists.
   */
  public static <A> F<Stream<A>, CopyOnWriteArrayList<A>> Stream_CopyOnWriteArrayList() {
    return new F<Stream<A>, CopyOnWriteArrayList<A>>() {
      public CopyOnWriteArrayList<A> f(final Stream<A> as) {
        return new CopyOnWriteArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to copy on write array sets.
   *
   * @return A function that converts streams to copy on write array sets.
   */
  public static <A> F<Stream<A>, CopyOnWriteArraySet<A>> Stream_CopyOnWriteArraySet() {
    return new F<Stream<A>, CopyOnWriteArraySet<A>>() {
      public CopyOnWriteArraySet<A> f(final Stream<A> as) {
        return new CopyOnWriteArraySet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to delay queues.
   *
   * @return A function that converts streams to delay queues.
   */
  public static <A extends Delayed> F<Stream<A>, DelayQueue<A>> Stream_DelayQueue() {
    return new F<Stream<A>, DelayQueue<A>>() {
      public DelayQueue<A> f(final Stream<A> as) {
        return new DelayQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to linked blocking queues.
   *
   * @return A function that converts streams to linked blocking queues.
   */
  public static <A> F<Stream<A>, LinkedBlockingQueue<A>> Stream_LinkedBlockingQueue() {
    return new F<Stream<A>, LinkedBlockingQueue<A>>() {
      public LinkedBlockingQueue<A> f(final Stream<A> as) {
        return new LinkedBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to priority blocking queues.
   *
   * @return A function that converts streams to priority blocking queues.
   */
  public static <A> F<Stream<A>, PriorityBlockingQueue<A>> Stream_PriorityBlockingQueue() {
    return new F<Stream<A>, PriorityBlockingQueue<A>>() {
      public PriorityBlockingQueue<A> f(final Stream<A> as) {
        return new PriorityBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts streams to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts streams to synchronous queues.
   */
  public static <A> F<Stream<A>, SynchronousQueue<A>> Stream_SynchronousQueue(final boolean fair) {
    return new F<Stream<A>, SynchronousQueue<A>>() {
      public SynchronousQueue<A> f(final Stream<A> as) {
        final SynchronousQueue<A> q = new SynchronousQueue<A>(fair);
        q.addAll(as.toCollection());
        return q;
      }
    };
  }

  // END Stream ->

  // BEGIN Option ->

  /**
   * A function that converts options to array lists.
   *
   * @return A function that converts options to array lists.
   */
  public static <A> F<Option<A>, ArrayList<A>> Option_ArrayList() {
    return new F<Option<A>, ArrayList<A>>() {
      public ArrayList<A> f(final Option<A> as) {
        return new ArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to bit sets.
   */
  public static final F<Option<Boolean>, BitSet> Option_BitSet = new F<Option<Boolean>, BitSet>() {
    public BitSet f(final Option<Boolean> bs) {
      final BitSet s = new BitSet(bs.length());

      bs.foreach(new Effect<Boolean>() {
        public void e(final Boolean b) {
          if (b)
            s.set(0);
        }
      });
      return s;
    }
  };

  /**
   * A function that converts options to enum sets.
   *
   * @return A function that converts options to enum sets.
   */
  public static <A extends Enum<A>> F<Option<A>, EnumSet<A>> Option_EnumSet() {
    return new F<Option<A>, EnumSet<A>>() {
      public EnumSet<A> f(final Option<A> as) {
        return copyOf(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to hash sets.
   *
   * @return A function that converts options to hash sets.
   */
  public static <A> F<Option<A>, HashSet<A>> Option_HashSet() {
    return new F<Option<A>, HashSet<A>>() {
      public HashSet<A> f(final Option<A> as) {
        return new HashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to linked hash sets.
   *
   * @return A function that converts options to linked hash sets.
   */
  public static <A> F<Option<A>, LinkedHashSet<A>> Option_LinkedHashSet() {
    return new F<Option<A>, LinkedHashSet<A>>() {
      public LinkedHashSet<A> f(final Option<A> as) {
        return new LinkedHashSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to linked lists.
   *
   * @return A function that converts options to linked lists.
   */
  public static <A> F<Option<A>, LinkedList<A>> Option_LinkedList() {
    return new F<Option<A>, LinkedList<A>>() {
      public LinkedList<A> f(final Option<A> as) {
        return new LinkedList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to priority queues.
   *
   * @return A function that converts options to priority queues.
   */
  public static <A> F<Option<A>, PriorityQueue<A>> Option_PriorityQueue() {
    return new F<Option<A>, PriorityQueue<A>>() {
      public PriorityQueue<A> f(final Option<A> as) {
        return new PriorityQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to stacks.
   *
   * @return A function that converts options to stacks.
   */
  public static <A> F<Option<A>, Stack<A>> Option_Stack() {
    return new F<Option<A>, Stack<A>>() {
      public Stack<A> f(final Option<A> as) {
        final Stack<A> s = new Stack<A>();
        s.addAll(as.toCollection());
        return s;
      }
    };
  }

  /**
   * A function that converts options to tree sets.
   *
   * @return A function that converts options to tree sets.
   */
  public static <A> F<Option<A>, TreeSet<A>> Option_TreeSet() {
    return new F<Option<A>, TreeSet<A>>() {
      public TreeSet<A> f(final Option<A> as) {
        return new TreeSet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to vectors.
   *
   * @return A function that converts options to vectors.
   */
  public static <A> F<Option<A>, Vector<A>> Option_Vector() {
    return new F<Option<A>, Vector<A>>() {
      @SuppressWarnings({"UseOfObsoleteCollectionType"})
      public Vector<A> f(final Option<A> as) {
        return new Vector<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts options to array blocking queues.
   */
  public static <A> F<Option<A>, ArrayBlockingQueue<A>> Option_ArrayBlockingQueue(final boolean fair) {
    return new F<Option<A>, ArrayBlockingQueue<A>>() {
      public ArrayBlockingQueue<A> f(final Option<A> as) {
        return new ArrayBlockingQueue<A>(as.length(), fair, as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to concurrent linked queues.
   *
   * @return A function that converts options to concurrent linked queues.
   */
  public static <A> F<Option<A>, ConcurrentLinkedQueue<A>> Option_ConcurrentLinkedQueue() {
    return new F<Option<A>, ConcurrentLinkedQueue<A>>() {
      public ConcurrentLinkedQueue<A> f(final Option<A> as) {
        return new ConcurrentLinkedQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to copy on write array lists.
   *
   * @return A function that converts options to copy on write array lists.
   */
  public static <A> F<Option<A>, CopyOnWriteArrayList<A>> Option_CopyOnWriteArrayList() {
    return new F<Option<A>, CopyOnWriteArrayList<A>>() {
      public CopyOnWriteArrayList<A> f(final Option<A> as) {
        return new CopyOnWriteArrayList<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to copy on write array sets.
   *
   * @return A function that converts options to copy on write array sets.
   */
  public static <A> F<Option<A>, CopyOnWriteArraySet<A>> Option_CopyOnWriteArraySet() {
    return new F<Option<A>, CopyOnWriteArraySet<A>>() {
      public CopyOnWriteArraySet<A> f(final Option<A> as) {
        return new CopyOnWriteArraySet<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to delay queues.
   *
   * @return A function that converts options to delay queues.
   */
  public static <A extends Delayed> F<Option<A>, DelayQueue<A>> Option_DelayQueue() {
    return new F<Option<A>, DelayQueue<A>>() {
      public DelayQueue<A> f(final Option<A> as) {
        return new DelayQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to linked blocking queues.
   *
   * @return A function that converts options to linked blocking queues.
   */
  public static <A> F<Option<A>, LinkedBlockingQueue<A>> Option_LinkedBlockingQueue() {
    return new F<Option<A>, LinkedBlockingQueue<A>>() {
      public LinkedBlockingQueue<A> f(final Option<A> as) {
        return new LinkedBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to priority blocking queues.
   *
   * @return A function that converts options to priority blocking queues.
   */
  public static <A> F<Option<A>, PriorityBlockingQueue<A>> Option_PriorityBlockingQueue() {
    return new F<Option<A>, PriorityBlockingQueue<A>>() {
      public PriorityBlockingQueue<A> f(final Option<A> as) {
        return new PriorityBlockingQueue<A>(as.toCollection());
      }
    };
  }

  /**
   * A function that converts options to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts options to synchronous queues.
   */
  public static <A> F<Option<A>, SynchronousQueue<A>> Option_SynchronousQueue(final boolean fair) {
    return new F<Option<A>, SynchronousQueue<A>>() {
      public SynchronousQueue<A> f(final Option<A> as) {
        final SynchronousQueue<A> q = new SynchronousQueue<A>(fair);
        q.addAll(as.toCollection());
        return q;
      }
    };
  }

  // END Option ->

  // BEGIN Either ->

  /**
   * A function that converts eithers to array lists.
   *
   * @return A function that converts eithers to array lists.
   */
  public static <A, B> F<Either<A, B>, ArrayList<A>> Either_ArrayListA() {
    return Function.compose(Java.<A>Option_ArrayList(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to array lists.
   *
   * @return A function that converts eithers to array lists.
   */
  public static <A, B> F<Either<A, B>, ArrayList<B>> Either_ArrayListB() {
    return Function.compose(Java.<B>Option_ArrayList(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to bit sets.
   *
   * @return A function that converts eithers to bit sets.
   */
  public static <B> F<Either<Boolean, B>, BitSet> Either_BitSetA() {
    return Function.compose(Option_BitSet, Conversions.<Boolean, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to bit sets.
   *
   * @return A function that converts eithers to bit sets.
   */
  public static <A> F<Either<A, Boolean>, BitSet> Either_BitSetB() {
    return Function.compose(Option_BitSet, Conversions.<A, Boolean>Either_OptionB());
  }

  /**
   * A function that converts eithers to enum sets.
   *
   * @return A function that converts eithers to enum sets.
   */
  public static <A extends Enum<A>, B> F<Either<A, B>, EnumSet<A>> Either_EnumSetA() {
    return Function.compose(Java.<A>Option_EnumSet(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to enum sets.
   *
   * @return A function that converts eithers to enum sets.
   */
  public static <A, B extends Enum<B>> F<Either<A, B>, EnumSet<B>> Either_EnumSetB() {
    return Function.compose(Java.<B>Option_EnumSet(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to hash sets.
   *
   * @return A function that converts eithers to hash sets.
   */
  public static <A, B> F<Either<A, B>, HashSet<A>> Either_HashSetA() {
    return Function.compose(Java.<A>Option_HashSet(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to hash sets.
   *
   * @return A function that converts eithers to hash sets.
   */
  public static <A, B> F<Either<A, B>, HashSet<B>> Either_HashSetB() {
    return Function.compose(Java.<B>Option_HashSet(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to linked hash sets.
   *
   * @return A function that converts eithers to linked hash sets.
   */
  public static <A, B> F<Either<A, B>, LinkedHashSet<A>> Either_LinkedHashSetA() {
    return Function.compose(Java.<A>Option_LinkedHashSet(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to linked hash sets.
   *
   * @return A function that converts eithers to linked hash sets.
   */
  public static <A, B> F<Either<A, B>, LinkedHashSet<B>> Either_LinkedHashSetB() {
    return Function.compose(Java.<B>Option_LinkedHashSet(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to linked lists.
   *
   * @return A function that converts eithers to linked lists.
   */
  public static <A, B> F<Either<A, B>, LinkedList<A>> Either_LinkedListA() {
    return Function.compose(Java.<A>Option_LinkedList(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to priority queues.
   *
   * @return A function that eithers options to priority queues.
   */
  public static <A, B> F<Either<A, B>, PriorityQueue<A>> Option_PriorityQueueA() {
    return Function.compose(Java.<A>Option_PriorityQueue(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to priority queues.
   *
   * @return A function that eithers options to priority queues.
   */
  public static <A, B> F<Either<A, B>, PriorityQueue<B>> Option_PriorityQueueB() {
    return Function.compose(Java.<B>Option_PriorityQueue(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to linked lists.
   *
   * @return A function that converts eithers to linked lists.
   */
  public static <A, B> F<Either<A, B>, LinkedList<B>> Either_LinkedListB() {
    return Function.compose(Java.<B>Option_LinkedList(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to stacks.
   *
   * @return A function that converts eithers to stacks.
   */
  public static <A, B> F<Either<A, B>, Stack<A>> Either_StackA() {
    return Function.compose(Java.<A>Option_Stack(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to stacks.
   *
   * @return A function that converts eithers to stacks.
   */
  public static <A, B> F<Either<A, B>, Stack<B>> Either_StackB() {
    return Function.compose(Java.<B>Option_Stack(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to tree sets.
   *
   * @return A function that converts eithers to tree sets.
   */
  public static <A, B> F<Either<A, B>, TreeSet<A>> Either_TreeSetA() {
    return Function.compose(Java.<A>Option_TreeSet(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to tree sets.
   *
   * @return A function that converts eithers to tree sets.
   */
  public static <A, B> F<Either<A, B>, TreeSet<B>> Either_TreeSetB() {
    return Function.compose(Java.<B>Option_TreeSet(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to vectors.
   *
   * @return A function that converts eithers to vectors.
   */
  public static <A, B> F<Either<A, B>, Vector<A>> Either_VectorA() {
    return Function.compose(Java.<A>Option_Vector(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to vectors.
   *
   * @return A function that converts eithers to vectors.
   */
  public static <A, B> F<Either<A, B>, Vector<B>> Either_VectorB() {
    return Function.compose(Java.<B>Option_Vector(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts eithers to array blocking queues.
   */
  public static <A, B> F<Either<A, B>, ArrayBlockingQueue<A>> Either_ArrayBlockingQueueA(final boolean fair) {
    return Function.compose(Java.<A>Option_ArrayBlockingQueue(fair), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts eithers to array blocking queues.
   */
  public static <A, B> F<Either<A, B>, ArrayBlockingQueue<B>> Either_ArrayBlockingQueueB(final boolean fair) {
    return Function.compose(Java.<B>Option_ArrayBlockingQueue(fair), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to concurrent linked queues.
   *
   * @return A function that converts eithers to concurrent linked queues.
   */
  public static <A, B> F<Either<A, B>, ConcurrentLinkedQueue<A>> Either_ConcurrentLinkedQueueA() {
    return Function.compose(Java.<A>Option_ConcurrentLinkedQueue(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to concurrent linked queues.
   *
   * @return A function that converts eithers to concurrent linked queues.
   */
  public static <A, B> F<Either<A, B>, ConcurrentLinkedQueue<B>> Either_ConcurrentLinkedQueueB() {
    return Function.compose(Java.<B>Option_ConcurrentLinkedQueue(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to copy on write array lists.
   *
   * @return A function that converts eithers to copy on write array lists.
   */
  public static <A, B> F<Either<A, B>, CopyOnWriteArrayList<A>> Either_CopyOnWriteArrayListA() {
    return Function.compose(Java.<A>Option_CopyOnWriteArrayList(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to copy on write array lists.
   *
   * @return A function that converts eithers to copy on write array lists.
   */
  public static <A, B> F<Either<A, B>, CopyOnWriteArrayList<B>> Either_CopyOnWriteArrayListB() {
    return Function.compose(Java.<B>Option_CopyOnWriteArrayList(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to copy on write array sets.
   *
   * @return A function that converts eithers to copy on write array sets.
   */
  public static <A, B> F<Either<A, B>, CopyOnWriteArraySet<A>> Either_CopyOnWriteArraySetA() {
    return Function.compose(Java.<A>Option_CopyOnWriteArraySet(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to copy on write array sets.
   *
   * @return A function that converts eithers to copy on write array sets.
   */
  public static <A, B> F<Either<A, B>, CopyOnWriteArraySet<B>> Either_CopyOnWriteArraySetB() {
    return Function.compose(Java.<B>Option_CopyOnWriteArraySet(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to delay queues.
   *
   * @return A function that converts eithers to delay queues.
   */
  public static <A extends Delayed, B> F<Either<A, B>, DelayQueue<A>> Either_DelayQueueA() {
    return Function.compose(Java.<A>Option_DelayQueue(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to delay queues.
   *
   * @return A function that converts eithers to delay queues.
   */
  public static <A, B extends Delayed> F<Either<A, B>, DelayQueue<B>> Either_DelayQueueB() {
    return Function.compose(Java.<B>Option_DelayQueue(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to linked blocking queues.
   *
   * @return A function that converts eithers to linked blocking queues.
   */
  public static <A, B> F<Either<A, B>, LinkedBlockingQueue<A>> Either_LinkedBlockingQueueA() {
    return Function.compose(Java.<A>Option_LinkedBlockingQueue(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to linked blocking queues.
   *
   * @return A function that converts eithers to linked blocking queues.
   */
  public static <A, B> F<Either<A, B>, LinkedBlockingQueue<B>> Either_LinkedBlockingQueueB() {
    return Function.compose(Java.<B>Option_LinkedBlockingQueue(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to priority blocking queues.
   *
   * @return A function that converts eithers to priority blocking queues.
   */
  public static <A, B> F<Either<A, B>, PriorityBlockingQueue<A>> Either_PriorityBlockingQueueA() {
    return Function.compose(Java.<A>Option_PriorityBlockingQueue(), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to priority blocking queues.
   *
   * @return A function that converts eithers to priority blocking queues.
   */
  public static <A, B> F<Either<A, B>, PriorityBlockingQueue<B>> Either_PriorityBlockingQueueB() {
    return Function.compose(Java.<B>Option_PriorityBlockingQueue(), Conversions.<A, B>Either_OptionB());
  }

  /**
   * A function that converts eithers to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts eithers to synchronous queues.
   */
  public static <A, B> F<Either<A, B>, SynchronousQueue<A>> Either_SynchronousQueueA(final boolean fair) {
    return Function.compose(Java.<A>Option_SynchronousQueue(fair), Conversions.<A, B>Either_OptionA());
  }

  /**
   * A function that converts eithers to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts eithers to synchronous queues.
   */
  public static <A, B> F<Either<A, B>, SynchronousQueue<B>> Either_SynchronousQueueB(final boolean fair) {
    return Function.compose(Java.<B>Option_SynchronousQueue(fair), Conversions.<A, B>Either_OptionB());
  }

  // END Either ->

  // BEGIN String ->

  /**
   * A function that converts strings to array lists.
   */
  public static final F<String, ArrayList<Character>> String_ArrayList =
      Function.compose(Java.<Character>List_ArrayList(), Conversions.String_List);

  /**
   * A function that converts strings to hash sets.
   */
  public static final F<String, HashSet<Character>> String_HashSet =
      Function.compose(Java.<Character>List_HashSet(), Conversions.String_List);

  /**
   * A function that converts strings to linked hash sets.
   */
  public static final F<String, LinkedHashSet<Character>> String_LinkedHashSet =
      Function.compose(Java.<Character>List_LinkedHashSet(), Conversions.String_List);

  /**
   * A function that converts strings to linked lists.
   */
  public static final F<String, LinkedList<Character>> String_LinkedList =
      Function.compose(Java.<Character>List_LinkedList(), Conversions.String_List);

  /**
   * A function that converts strings to priority queues.
   */
  public static final F<String, PriorityQueue<Character>> String_PriorityQueue =
      Function.compose(Java.<Character>List_PriorityQueue(), Conversions.String_List);

  /**
   * A function that converts strings to stacks.
   */
  public static final F<String, Stack<Character>> String_Stack =
      Function.compose(Java.<Character>List_Stack(), Conversions.String_List);

  /**
   * A function that converts strings to tree sets.
   */
  public static final F<String, TreeSet<Character>> String_TreeSet =
      Function.compose(Java.<Character>List_TreeSet(), Conversions.String_List);

  /**
   * A function that converts strings to vectors.
   */
  public static final F<String, Vector<Character>> String_Vector =
      Function.compose(Java.<Character>List_Vector(), Conversions.String_List);

  /**
   * A function that converts strings to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts strings to array blocking queues.
   */
  public static F<String, ArrayBlockingQueue<Character>> String_ArrayBlockingQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_ArrayBlockingQueue(fair), Conversions.String_List);
  }

  /**
   * A function that converts strings to concurrent linked queues.
   */
  public static final F<String, ConcurrentLinkedQueue<Character>> String_ConcurrentLinkedQueue =
      Function.compose(Java.<Character>List_ConcurrentLinkedQueue(), Conversions.String_List);

  /**
   * A function that converts strings to copy on write array lists.
   */
  public static final F<String, CopyOnWriteArrayList<Character>> String_CopyOnWriteArrayList =
      Function.compose(Java.<Character>List_CopyOnWriteArrayList(), Conversions.String_List);

  /**
   * A function that converts strings to copy on write array sets.
   */
  public static final F<String, CopyOnWriteArraySet<Character>> String_CopyOnWriteArraySet =
      Function.compose(Java.<Character>List_CopyOnWriteArraySet(), Conversions.String_List);

  /**
   * A function that converts strings to linked blocking queues.
   */
  public static final F<String, LinkedBlockingQueue<Character>> String_LinkedBlockingQueue =
      Function.compose(Java.<Character>List_LinkedBlockingQueue(), Conversions.String_List);

  /**
   * A function that converts strings to priority blocking queues.
   */
  public static final F<String, PriorityBlockingQueue<Character>> String_PriorityBlockingQueue =
      Function.compose(Java.<Character>List_PriorityBlockingQueue(), Conversions.String_List);

  /**
   * A function that converts strings to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts strings to synchronous queues.
   */
  public static F<String, SynchronousQueue<Character>> String_SynchronousQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_SynchronousQueue(fair), Conversions.String_List);
  }

  // END String ->

  // BEGIN StringBuffer ->

  /**
   * A function that converts string buffers to array lists.
   */
  public static final F<StringBuffer, ArrayList<Character>> StringBuffer_ArrayList =
      Function.compose(Java.<Character>List_ArrayList(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to hash sets.
   */
  public static final F<StringBuffer, HashSet<Character>> StringBuffer_HashSet =
      Function.compose(Java.<Character>List_HashSet(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to linked hash sets.
   */
  public static final F<StringBuffer, LinkedHashSet<Character>> StringBuffer_LinkedHashSet =
      Function.compose(Java.<Character>List_LinkedHashSet(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to linked lists.
   */
  public static final F<StringBuffer, LinkedList<Character>> StringBuffer_LinkedList =
      Function.compose(Java.<Character>List_LinkedList(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to priority queues.
   */
  public static final F<StringBuffer, PriorityQueue<Character>> StringBuffer_PriorityQueue =
      Function.compose(Java.<Character>List_PriorityQueue(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to stacks.
   */
  public static final F<StringBuffer, Stack<Character>> StringBuffer_Stack =
      Function.compose(Java.<Character>List_Stack(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to tree sets.
   */
  public static final F<StringBuffer, TreeSet<Character>> StringBuffer_TreeSet =
      Function.compose(Java.<Character>List_TreeSet(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to vectors.
   */
  public static final F<StringBuffer, Vector<Character>> StringBuffer_Vector =
      Function.compose(Java.<Character>List_Vector(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts string buffers to array blocking queues.
   */
  public static F<StringBuffer, ArrayBlockingQueue<Character>> StringBuffer_ArrayBlockingQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_ArrayBlockingQueue(fair), Conversions.StringBuffer_List);
  }

  /**
   * A function that converts string buffers to concurrent linked queues.
   */
  public static final F<StringBuffer, ConcurrentLinkedQueue<Character>> StringBuffer_ConcurrentLinkedQueue =
      Function.compose(Java.<Character>List_ConcurrentLinkedQueue(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to copy on write array lists.
   */
  public static final F<StringBuffer, CopyOnWriteArrayList<Character>> StringBuffer_CopyOnWriteArrayList =
      Function.compose(Java.<Character>List_CopyOnWriteArrayList(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to copy on write array sets.
   */
  public static final F<StringBuffer, CopyOnWriteArraySet<Character>> StringBuffer_CopyOnWriteArraySet =
      Function.compose(Java.<Character>List_CopyOnWriteArraySet(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to linked blocking queues.
   */
  public static final F<StringBuffer, LinkedBlockingQueue<Character>> StringBuffer_LinkedBlockingQueue =
      Function.compose(Java.<Character>List_LinkedBlockingQueue(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to priority blocking queues.
   */
  public static final F<StringBuffer, PriorityBlockingQueue<Character>> StringBuffer_PriorityBlockingQueue =
      Function.compose(Java.<Character>List_PriorityBlockingQueue(), Conversions.StringBuffer_List);

  /**
   * A function that converts string buffers to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts string buffers to synchronous queues.
   */
  public static F<StringBuffer, SynchronousQueue<Character>> StringBuffer_SynchronousQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_SynchronousQueue(fair), Conversions.StringBuffer_List);
  }

  // END StringBuffer ->

  // BEGIN StringBuilder ->

  /**
   * A function that converts string builders to array lists.
   */
  public static final F<StringBuilder, ArrayList<Character>> StringBuilder_ArrayList =
      Function.compose(Java.<Character>List_ArrayList(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to hash sets.
   */
  public static final F<StringBuilder, HashSet<Character>> StringBuilder_HashSet =
      Function.compose(Java.<Character>List_HashSet(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to linked hash sets.
   */
  public static final F<StringBuilder, LinkedHashSet<Character>> StringBuilder_LinkedHashSet =
      Function.compose(Java.<Character>List_LinkedHashSet(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to linked lists.
   */
  public static final F<StringBuilder, LinkedList<Character>> StringBuilder_LinkedList =
      Function.compose(Java.<Character>List_LinkedList(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to priority queues.
   */
  public static final F<StringBuilder, PriorityQueue<Character>> StringBuilder_PriorityQueue =
      Function.compose(Java.<Character>List_PriorityQueue(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to stacks.
   */
  public static final F<StringBuilder, Stack<Character>> StringBuilder_Stack =
      Function.compose(Java.<Character>List_Stack(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to tree sets.
   */
  public static final F<StringBuilder, TreeSet<Character>> StringBuilder_TreeSet =
      Function.compose(Java.<Character>List_TreeSet(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to vectors.
   */
  public static final F<StringBuilder, Vector<Character>> StringBuilder_Vector =
      Function.compose(Java.<Character>List_Vector(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to array blocking queues.
   *
   * @param fair The argument to pass to the constructor of the array blocking queue.
   * @return A function that converts string builders to array blocking queues.
   */
  public static F<StringBuilder, ArrayBlockingQueue<Character>> StringBuilder_ArrayBlockingQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_ArrayBlockingQueue(fair), Conversions.StringBuilder_List);
  }

  /**
   * A function that converts string builders to concurrent linked queues.
   */
  public static final F<StringBuilder, ConcurrentLinkedQueue<Character>> StringBuilder_ConcurrentLinkedQueue =
      Function.compose(Java.<Character>List_ConcurrentLinkedQueue(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to copy on write array lists.
   */
  public static final F<StringBuilder, CopyOnWriteArrayList<Character>> StringBuilder_CopyOnWriteArrayList =
      Function.compose(Java.<Character>List_CopyOnWriteArrayList(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to copy on write array sets.
   */
  public static final F<StringBuilder, CopyOnWriteArraySet<Character>> StringBuilder_CopyOnWriteArraySet =
      Function.compose(Java.<Character>List_CopyOnWriteArraySet(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to linked blocking queues.
   */
  public static final F<StringBuilder, LinkedBlockingQueue<Character>> StringBuilder_LinkedBlockingQueue =
      Function.compose(Java.<Character>List_LinkedBlockingQueue(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to priority blocking queues.
   */
  public static final F<StringBuilder, PriorityBlockingQueue<Character>> StringBuilder_PriorityBlockingQueue =
      Function.compose(Java.<Character>List_PriorityBlockingQueue(), Conversions.StringBuilder_List);

  /**
   * A function that converts string builders to synchronous queues.
   *
   * @param fair The argument to pass to the constructor of the synchronous queue.
   * @return A function that converts string builders to synchronous queues.
   */
  public static F<StringBuilder, SynchronousQueue<Character>> StringBuilder_SynchronousQueue(final boolean fair) {
    return Function.compose(Java.<Character>List_SynchronousQueue(fair), Conversions.StringBuilder_List);
  }

  // END StringBuffer ->

  // BEGIN ArrayList ->

  /**
   * A function that converts array lists to lists.
   *
   * @return A function that converts array lists to lists.
   */
  public static <A> F<ArrayList<A>, List<A>> ArrayList_List() {
    return new F<ArrayList<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final ArrayList<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END ArrayList ->

  /**
   * A function that converts Java lists to lists.
   *
   * @return A function that converts Java lists to lists.
   */
  public static <A> F<java.util.List<A>, List<A>> JUList_List() {
    return new F<java.util.List<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final java.util.List<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }
  
  // BEGIN BitSet ->

  /**
   * A function that converts bit sets to lists.
   */
  public static final F<BitSet, List<Boolean>> BitSet_List = new F<BitSet, List<Boolean>>() {
    public List<Boolean> f(final BitSet s) {
      return List.unfold(new F<Integer, Option<P2<Boolean, Integer>>>() {
        public Option<P2<Boolean, Integer>> f(final Integer i) {
          return i == s.length() ?
              Option.<P2<Boolean, Integer>>none() :
              some(p(s.get(i), i + 1));
        }
      }, 0);
    }
  };

  // todo

  // END BitSet ->

  // BEGIN EnumSet ->

  /**
   * A function that converts enum sets to lists.
   *
   * @return A function that converts enum sets to lists.
   */
  public static <A extends Enum<A>> F<EnumSet<A>, List<A>> EnumSet_List() {
    return new F<EnumSet<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final EnumSet<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END EnumSet ->

  // BEGIN HashSet ->

  /**
   * A function that converts hash sets to lists.
   *
   * @return A function that converts hash sets to lists.
   */
  public static <A> F<HashSet<A>, List<A>> HashSet_List() {
    return new F<HashSet<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final HashSet<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END HashSet ->

  // BEGIN LinkedHashSet ->

  /**
   * A function that converts linked hash sets to lists.
   *
   * @return A function that converts linked hash sets to lists.
   */
  public static <A> F<LinkedHashSet<A>, List<A>> LinkedHashSet_List() {
    return new F<LinkedHashSet<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final LinkedHashSet<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END LinkedHashSet ->

  // BEGIN Linked List ->

  /**
   * A function that converts linked lists to lists.
   *
   * @return A function that converts linked lists to lists.
   */
  public static <A> F<LinkedList<A>, List<A>> LinkedList_List() {
    return new F<LinkedList<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final LinkedList<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END Linked List ->

  // BEGIN PriorityQueue ->

  /**
   * A function that converts priority queues to lists.
   *
   * @return A function that converts priority queues to lists.
   */
  public static <A> F<PriorityQueue<A>, List<A>> PriorityQueue_List() {
    return new F<PriorityQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final PriorityQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END PriorityQueue ->

  // BEGIN Stack ->

  /**
   * A function that converts stacks to lists.
   *
   * @return A function that converts stacks to lists.
   */
  public static <A> F<Stack<A>, List<A>> Stack_List() {
    return new F<Stack<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final Stack<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END Stack ->

  // BEGIN TreeSet ->

  /**
   * A function that converts tree sets to lists.
   *
   * @return A function that converts tree sets to lists.
   */
  public static <A> F<TreeSet<A>, List<A>> TreeSet_List() {
    return new F<TreeSet<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final TreeSet<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END TreeSet ->

  // BEGIN Vector ->

  /**
   * A function that converts vectors to lists.
   *
   * @return A function that converts vectors to lists.
   */
  public static <A> F<Vector<A>, List<A>> Vector_List() {
    return new F<Vector<A>, List<A>>() {
      @SuppressWarnings({"unchecked", "UseOfObsoleteCollectionType"})
      public List<A> f(final Vector<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END Vector ->

  // BEGIN ArrayBlockingQueue ->

  /**
   * A function that converts array blocking queues to lists.
   *
   * @return A function that converts array blocking queues to lists.
   */
  public static <A> F<ArrayBlockingQueue<A>, List<A>> ArrayBlockingQueue_List() {
    return new F<ArrayBlockingQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final ArrayBlockingQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END ArrayBlockingQueue ->

  // BEGIN ConcurrentLinkedQueue ->

  /**
   * A function that converts concurrent linked queues to lists.
   *
   * @return A function that converts concurrent linked queues to lists.
   */
  public static <A> F<ConcurrentLinkedQueue<A>, List<A>> ConcurrentLinkedQueue_List() {
    return new F<ConcurrentLinkedQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final ConcurrentLinkedQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END ConcurrentLinkedQueue ->

  // BEGIN CopyOnWriteArrayList ->

  /**
   * A function that converts copy on write array lists to lists.
   *
   * @return A function that converts copy on write array lists to lists.
   */
  public static <A> F<CopyOnWriteArrayList<A>, List<A>> CopyOnWriteArrayList_List() {
    return new F<CopyOnWriteArrayList<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final CopyOnWriteArrayList<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END CopyOnWriteArrayList ->

  // BEGIN CopyOnWriteArraySet ->

  /**
   * A function that converts copy on write array sets to lists.
   *
   * @return A function that converts copy on write array sets to lists.
   */
  public static <A> F<CopyOnWriteArraySet<A>, List<A>> CopyOnWriteArraySet_List() {
    return new F<CopyOnWriteArraySet<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final CopyOnWriteArraySet<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END CopyOnWriteArraySet ->

  // BEGIN DelayQueue ->

  /**
   * A function that converts delay queues to lists.
   *
   * @return A function that converts delay queues to lists.
   */
  public static <A extends Delayed> F<DelayQueue<A>, List<A>> DelayQueue_List() {
    return new F<DelayQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final DelayQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END DelayQueue ->

  // BEGIN LinkedBlockingQueue ->

  /**
   * A function that converts linked blocking queues to lists.
   *
   * @return A function that converts linked blocking queues to lists.
   */
  public static <A> F<LinkedBlockingQueue<A>, List<A>> LinkedBlockingQueue_List() {
    return new F<LinkedBlockingQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final LinkedBlockingQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END LinkedBlockingQueue ->

  // BEGIN PriorityBlockingQueue ->

  /**
   * A function that converts priority blocking queues to lists.
   *
   * @return A function that converts priority blocking queues to lists.
   */
  public static <A> F<PriorityBlockingQueue<A>, List<A>> PriorityBlockingQueue_List() {
    return new F<PriorityBlockingQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final PriorityBlockingQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END PriorityBlockingQueue ->

  // BEGIN SynchronousQueue ->

  /**
   * A function that converts synchronous queues to lists.
   *
   * @return A function that converts synchronous queues to lists.
   */
  public static <A> F<SynchronousQueue<A>, List<A>> SynchronousQueue_List() {
    return new F<SynchronousQueue<A>, List<A>>() {
      @SuppressWarnings({"unchecked"})
      public List<A> f(final SynchronousQueue<A> as) {
        return list(as.toArray((A[]) new Object[as.size()]));
      }
    };
  }

  // todo

  // END SynchronousQueue ->

  // BEGIN Callable ->

  public static <A> F<P1<A>, Callable<A>> P1_Callable() {
    return new F<P1<A>, Callable<A>>() {
      public Callable<A> f(final P1<A> a) {
        return new Callable<A>() {
          public A call() {
            return a._1();
          }
        };
      }
    };
  }

// END Callable ->

// BEGIN Future ->

  public static <A> F<Future<A>, P1<Either<Exception, A>>> Future_P1() {
    return new F<Future<A>, P1<Either<Exception, A>>>() {
      public P1<Either<Exception, A>> f(final Future<A> a) {
        return new P1<Either<Exception, A>>() {
          @SuppressWarnings({"OverlyBroadCatchBlock"})
          public Either<Exception, A> _1() {
            Either<Exception, A> r;
            try {
              r = Either.right(a.get());
            }
            catch (Exception e) {
              r = Either.left(e);
            }
            return r;
          }
        };
      }
    };
  }

  // END Future ->
}
