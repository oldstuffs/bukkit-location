/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.portlek.bukkitlocation;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link Random}.
 */
public final class RandomUtil {

  /**
   * a random cache.
   */
  public static final Random RANDOM = new SecureRandom();

  /**
   * ctor.
   */
  private RandomUtil() {
  }

  /**
   * chooses objects from the given list with the given limit.
   *
   * @param list the list to choose.
   * @param limit the limit to choose.
   * @param duplicate the duplicate to check if the object is already in the result list.
   * @param <T> the object type.
   *
   * @return a random chosen list.
   */
  @NotNull
  public static <T> List<T> chooseRandoms(@NotNull final List<T> list, final int limit, final boolean duplicate) {
    if (list.size() <= limit && !duplicate) {
      return Collections.emptyList();
    }
    final var things = new ArrayList<T>();
    var limitClone = limit;
    while (limitClone > 0) {
      final var thing = list.get(RandomUtil.RANDOM.nextInt(list.size()));
      if (things.contains(thing) && !duplicate) {
        continue;
      }
      things.add(thing);
      --limitClone;
    }
    return things;
  }
}
