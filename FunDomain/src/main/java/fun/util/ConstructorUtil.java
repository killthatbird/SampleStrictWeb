package fun.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility Class for constructors.
 *
 * @author I have no name
 *
 */
public final class ConstructorUtil {
  /**
   * Logger for this class.
   */
  private static final Logger LOG = Logger.getLogger(ConstructorUtil.class.getName());

  private ConstructorUtil() {

  }

  /**
   * Call default private or public constructor.
   *
   * @param clazz class
   * @return new instance
   * @throws ReflectiveOperationException reflection exception
   */
  public static <T> T construct(final Class<T> clazz) throws ReflectiveOperationException {
    final Constructor<T> constructor = getDeclaredConstructor(clazz);
    final boolean isPrivate = Modifier.isPrivate(getModifiers(constructor));
    if (isPrivate) {
      setAccessible(constructor, true);
    }
    return getNewInstance(constructor);
  }

  private static <T> Constructor<T> getDeclaredConstructor(final Class<T> clazz)
      throws NoSuchMethodException, SecurityException {
    return clazz.getDeclaredConstructor();
  }

  private static <T> int getModifiers(final Constructor<T> constructor) {
    return constructor.getModifiers();

  }

  /**
   * Get name of a class.
   * @param clazz class
   * @return class name
   */
  public static String getName(final Class<?> clazz) {
    return clazz.getName();
  }

  private static <T> T getNewInstance(final Constructor<T> constructor)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    return constructor.newInstance();
  }

  /**
   * Logs instance creation at the finest level.
   *
   * @param clazz class
   */
  public static void logInstanceCreation(final Class<?> clazz) {
    if (clazz != null && LOG.isLoggable(Level.FINEST)) {
      LOG.finest(clazz.getName() + "instance created.");
    }
  }

  private static void setAccessible(final Constructor<?> constructor, final boolean value) {
    constructor.setAccessible(value);
  }

  /**
   * Sets Log level.
   * @param logger logger
   */
  public static void setLogLevel(final Logger logger, final Level level) {
    logger.setLevel(level);
  }
}
