import jundl77.izou.addon.izoucecoutputcontroller.CECAddOn;
import org.intellimate.izou.addon.AddOnModel;
import org.intellimate.izou.main.Main;

import java.util.LinkedList;

/**
 * Use this class to debug
 */
public class Debug {
    public static void main(String[] args) {
        LinkedList<AddOnModel> addOns = new LinkedList<>();
        addOns.add(new CECAddOn());
        Main main = new Main(addOns, true);
    }
}
