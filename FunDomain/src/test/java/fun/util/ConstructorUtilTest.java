package fun.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test Class.
 *
 * @author Open Source
 *
 */
public class ConstructorUtilTest {

  /**
   * Testing.
   */
  public ConstructorUtilTest() {
    super();
    ConstructorUtil.logInstanceCreation(ConstructorUtilTest.class);
  }

  /**
   * Test Construct method.
   * @throws ReflectiveOperationException exception
   */
  @Test
  public final void testConstruct() throws ReflectiveOperationException {
    final ConstructorUtil util = ConstructorUtil.construct(ConstructorUtil.class);
    Assert.assertNotNull("util should not be null.", util);
    final Object object = ConstructorUtil.construct(Object.class);
    Assert.assertNotNull("test object should not be null.", object);
  }

  /**
   * Test LogInstanceCreation method.
   */
  @Test
  public final void testLogInstanceCreation() {
    ConstructorUtil.logInstanceCreation(ConstructorUtilTest.class);
    ConstructorUtil.logInstanceCreation(null);
    final Logger logger = Logger.getLogger(ConstructorUtil.getName(ConstructorUtil.class));
    ConstructorUtil.setLogLevel(logger, Level.FINEST);
    ConstructorUtil.logInstanceCreation(ConstructorUtilTest.class);
    Assert.assertNotNull("logger should not be null.", logger);
  }

  /**
   * Test no operation.
   */
  @Test
  public final void testNoOp() {
    ConstructorUtil.noOp();
    Assert.assertNotNull(ConstructorUtil.class);
  }

}
