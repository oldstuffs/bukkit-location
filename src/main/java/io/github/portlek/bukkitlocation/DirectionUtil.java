/*
 * MIT License
 *
 * Copyright (c) 2020 Hasan Demirtaş
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

import java.util.Arrays;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link Directions}.
 */
public final class DirectionUtil {

  /**
   * ctor.
   */
  private DirectionUtil() {
  }

  /**
   * obtains a {@link Directions} from the given player.
   *
   * @param player the player to obtain.
   *
   * @return direction of the given player.
   */
  @NotNull
  public static Directions directionOf(@NotNull final Player player) {
    return DirectionUtil.directionOf(player.getLocation());
  }

  /**
   * obtains a {@link Directions} from the given location.
   *
   * @param location the location to obtain.
   *
   * @return direction of the given location.
   */
  @NotNull
  public static Directions directionOf(@NotNull final Location location) {
    return DirectionUtil.directionOf(location.getYaw());
  }

  /**
   * obtains a {@link Directions} from the given yaw.
   *
   * @param yaw the yaw to obtain.
   *
   * @return direction of the given yaw.
   */
  @NotNull
  public static Directions directionOf(final float yaw) {
    return DirectionUtil.directionOf(
      Arrays.asList(
        Directions.SOUTH,
        Directions.WEST,
        Directions.NORTH,
        Directions.EAST),
      yaw);
  }

  /**
   * obtains a double {@link Directions} from the given player.
   *
   * @param player the player to obtain.
   *
   * @return direction of the given player.
   */
  @NotNull
  public static Directions doubleDirectionOf(@NotNull final Player player) {
    return DirectionUtil.doubleDirectionOf(player.getLocation());
  }

  /**
   * obtains a double {@link Directions} from the given location.
   *
   * @param location the location to obtain.
   *
   * @return direction of the given location.
   */
  @NotNull
  public static Directions doubleDirectionOf(@NotNull final Location location) {
    return DirectionUtil.doubleDirectionOf(location.getYaw());
  }

  /**
   * obtains a double {@link Directions} from the given yaw.
   *
   * @param yaw the yaw to obtain.
   *
   * @return direction of the given yaw.
   */
  @NotNull
  public static Directions doubleDirectionOf(final float yaw) {
    return DirectionUtil.doubleDirectionOf(
      Arrays.asList(
        Directions.SOUTH,
        Directions.SOUTHWEST,
        Directions.WEST,
        Directions.NORTHWEST,
        Directions.NORTH,
        Directions.NORTHEAST,
        Directions.EAST,
        Directions.SOUTHEAST),
      yaw);
  }

  /**
   * obtains a {@link Directions} from the given direction list with the given yaw.
   *
   * @param directions the directions to obtain.
   * @param yaw the yaw to obtain.
   *
   * @return direction of the yaw from the direction list.
   */
  @NotNull
  private static Directions directionOf(@NotNull final List<Directions> directions, final float yaw) {
    return directions.get((int) (yaw + 45.0f) % 360 / 90);
  }

  /**
   * obtains a double {@link Directions} from the given direction list with the given yaw.
   *
   * @param directions the directions to obtain.
   * @param yaw the yaw to obtain.
   *
   * @return direction of the yaw from the direction list.
   */
  @NotNull
  private static Directions doubleDirectionOf(@NotNull final List<Directions> directions, final float yaw) {
    return directions.get((int) (yaw + 22.5f) % 360 / 45);
  }
}
