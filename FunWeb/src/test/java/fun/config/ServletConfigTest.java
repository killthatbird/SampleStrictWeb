package fun.config;

import fun.util.ConstructorUtil;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Test Class for Configuration.
 * @author I have no name
 *
 */
public class ServletConfigTest {

  /**
   * Constructor for Web Initializer.
   */
  public ServletConfigTest() {
    super();
    ConstructorUtil.logInstanceCreation(ServletConfigTest.class);
  }

  /**
   * Test Adding Resource Handlers.
   */
  @Test
  public final void testAddResourceHandlersResourceHandlerRegistry() {
    final ServletConfig config = new ServletConfig();
    final ResourceHandlerRegistry registry = new ResourceHandlerRegistry(
        Mockito.mock(ApplicationContext.class), null);
    config.addResourceHandlers(registry);
    Assert.assertNotNull("Registry should not be null.", registry);
  }

  /**
   * Test Constructor.
   */
  @Test
  public final void testServletConfig() {
    Assert.assertNotNull("Constructor should not produce null.", new ServletConfig());
  }

  /**
   * Test view resolver.
   */
  @Test
  public final void testSetupViewResolver() {
    final ServletConfig config = new ServletConfig();
    config.setupViewResolver();
    Assert.assertNotNull("Setup config should not be null.", config);
  }
}
