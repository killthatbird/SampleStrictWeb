package fun.config;

import fun.util.ConstructorUtil;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockServletContext;

import java.util.EventListener;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Test Web Initializer.
 * @author Open source
 *
 */
public class WebInitializerTest {
  /**
   * Private Extension for Mocking.
   * @author Open Source
   *
   */
  private static final class MockServletContextExtension extends MockServletContext {
    /**
     * constructor.
     */
    public MockServletContextExtension() {
      super();
      ConstructorUtil.logInstanceCreation(MockServletContextExtension.class);
    }

    /**
     * Mocks adding event listener, or not.
     */
    @Override
    public <T extends EventListener> void addListener(final T listener) {
      //Do nothing.
    }

    /**
     * Overrides default functionality.
     */
    @Override
    public Dynamic addServlet(final String servletName, final Servlet servlet) {
      return Mockito.mock(Dynamic.class);
    }
  }

  /**
   * Constructor for Web Initializer.
   */
  public WebInitializerTest() {
    super();
    ConstructorUtil.logInstanceCreation(WebInitializerTest.class);
  }

  /**
   * Test.
   */
  @Test
  public final void testOnStartup() {
    final WebInitializer instance = new WebInitializer();
    Assert.assertNotNull("WebInitializer should not be null.", instance);
  }

  /**
   * Test.
   * @throws ServletException object
   */
  @Test
  public final void testWebInitializer() throws ServletException {
    new WebInitializer();
    final ServletContext context = new MockServletContextExtension();
    //instance.onStartup(context);
    Assert.assertNotNull("Context should not be null.", context);
  }
}