import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wongleon on 2017/4/25.
 */
public class JavaTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> re = new ArrayList<Integer>();
        re.add(1);
        re.add(2);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer col = it.next();
            System.out.println("outer:"+col);
            Iterator<Integer> inner = re.iterator();
            while (inner.hasNext()) {
                Integer r = inner.next();
                System.out.println("inner:"+r);
                if (col.equals(r)) {
                    it.remove();
                }
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
}
