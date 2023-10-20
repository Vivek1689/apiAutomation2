package utils.assertion;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;
import utils.extentReport.ExtentManager;

public class AssertionUtil {

    public static void assertEqual(Object actualObj , Object expectedObj ,  String passMsg , String failMsg){
       try {
           Assert.assertEquals(actualObj, expectedObj);
           ExtentManager.getTest().pass(MarkupHelper.createLabel(passMsg, ExtentColor.GREEN));
       }catch (Exception e){
           ExtentManager.getTest().fail(MarkupHelper.createLabel(failMsg, ExtentColor.RED));
           Assert.fail();
       }

    }

    public static void assertTrue(boolean expectedFlag ,  String passMsg , String failMsg){
        try {
            Assert.assertTrue(expectedFlag);
            ExtentManager.getTest().pass(MarkupHelper.createLabel(passMsg, ExtentColor.GREEN));

        }catch (Exception e){
            ExtentManager.getTest().fail(MarkupHelper.createLabel(failMsg, ExtentColor.RED));
            Assert.fail();
        }

    }

    public static void assertFalse(boolean val ,  String passMsg , String failMsg){
        try {
            Assert.assertFalse(val);
            ExtentManager.getTest().pass(MarkupHelper.createLabel(failMsg, ExtentColor.GREEN));

        }catch (Exception e){
            ExtentManager.getTest().fail(MarkupHelper.createLabel(failMsg, ExtentColor.RED));
            Assert.fail();
        }

    }


}
