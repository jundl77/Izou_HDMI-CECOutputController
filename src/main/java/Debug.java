import intellimate.izou.addon.AddOn;
import intellimate.izou.main.Main;

import java.util.LinkedList;

/**
 * Use this class to debug
 */
public class Debug {
    public static void main(String[] args) {
        LinkedList<AddOn> addOns = new LinkedList<>();

        //add AddOns like this:
        //addOns.add(addOn);

        Main main = new Main(addOns);
    }
}
