package top.stanwang.exp1;

import org.junit.Assert;
import org.junit.Test;
import top.stanwang.exp1.Main;

public class MainTest {
    @Test
    public void test(){
        Assert.assertTrue(Main.takeOut(0));
//        Assert.assertTrue(Main.takeOut(2));
        Assert.assertTrue(Main.takeOut(20));
        Assert.assertTrue(Main.takeOut(50));
        Assert.assertFalse(Main.takeOut(100));
    }
}
