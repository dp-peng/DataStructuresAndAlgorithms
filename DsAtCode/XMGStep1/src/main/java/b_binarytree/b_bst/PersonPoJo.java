package b_binarytree.b_bst;

/**
 * ========================
 * module_name:[]
 * module_desc:
 *
 * @author: dp
 * @date: 2022/12/10
 * @since: 1.0
 * ========================
 */
public class PersonPoJo implements Comparable{

    public int age;
    public String name;

    public PersonPoJo(int age, String name) {
        this.age = age;
        this.name = name;
    }


    @Override
    public int compareTo(Object o) {
        return this.age - ((PersonPoJo)o).age;
    }

    @Override
    public String toString() {
        return age + "";
    }
}
