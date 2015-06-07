package fun.web.mvc.controller;

import fun.util.ConstructorUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * Welcome Controller Test.
 * @author I have no name
 *
 */
public class WelcomeControllerTest {
  /**
   * Welcome Controller.
   */
  public WelcomeControllerTest() {
    super();
    ConstructorUtil.logInstanceCreation(WelcomeControllerTest.class);
  }

  /**
   * Test Do Get.
   */
  @Test
  public final void testDoGet() {
    final WelcomeController controller = new WelcomeController();
    final String retval = controller.doGet();
    Assert.assertNotNull("Do Get Retval should not be null", retval);

  }

  /**
   * Test Welcome.
   */
  @Test
  public final void testWelcomeController() {
    Assert.assertNotNull("New WelcomeController should not be null.", new WelcomeController());
  }

}
