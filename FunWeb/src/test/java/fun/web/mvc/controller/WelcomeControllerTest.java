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
    controller.doGet();
    Assert.assertNotNull("Welcome Controller should not be null", controller);

  }

  /**
   * Test Welcome.
   */
  @Test
  public final void testWelcomeController() {
    Assert.assertNotNull("New WelcomeController should not be null.", new WelcomeController());
  }

}
