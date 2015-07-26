package fun.config;

import fun.util.ConstructorUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class to setup environment.
 * @author Open Source
 *
 */
public class InitServlet implements ServletContextListener {

  /**
   * Server Startup.
   */
  public InitServlet() {
    super();
    ConstructorUtil.logInstanceCreation(InitServlet.class);
  }

  /**
   * Context destroyed.
   */
  @Override
  public void contextDestroyed(final ServletContextEvent event) {
    //To be filled out when needed.
  }

  /**
   * Context Initialized.
   */
  @Override
  public void contextInitialized(final ServletContextEvent event) {
    //To be filled out when needed.
  }
}
