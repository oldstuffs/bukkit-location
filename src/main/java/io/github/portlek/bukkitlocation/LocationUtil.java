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

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link Location}.
 */
public final class LocationUtil {

  /**
   * the location pattern which looks like WORLD/X_X/Y_Y/Z_Z/YAW_YAW/PITCH_PITCH
   */
  private static final Pattern PATTERN =
    Pattern.compile("(?<world>[^/]+):(?<x>[\\-0-9.]+),(?<y>[\\-0-9.]+),(?<z>[\\-0-9.]+)(:(?<yaw>[\\-0-9.]+):(?<pitch>[\\-0-9.]+))?");

  /**
   * ctor.
   */
  private LocationUtil() {
  }

  /**
   * calculates the center bottom of the given location.
   *
   * @param location the location to calculate.
   *
   * @return location of the center from the given location.
   */
  @NotNull
  public static Location centeredOn(@NotNull final Location location) {
    return LocationUtil.centered(location, 0.1d);
  }

  /**
   * calculates the center of the given location.
   *
   * @param location the location to calculate.
   *
   * @return location of the center from the given location.
   */
  @NotNull
  public static Location centeredIn(@NotNull final Location location) {
    return LocationUtil.centered(location, 0.5d);
  }

  /**
   * gets the world of the given location.
   *
   * @param location the location to get.
   *
   * @return world of the location.
   *
   * @throws IllegalStateException if the given location has not a world.
   */
  @NotNull
  public static World validWorld(@NotNull final Location location) {
    return Optional.ofNullable(location.getWorld()).orElseThrow(() ->
      new IllegalStateException("World of the location cannot be null!"));
  }

  /**
   * converts the given key into a {@link Location}.
   *
   * @param key the key to convert.
   *
   * @return a {@link Location} instance.
   */
  @NotNull
  public static Optional<Location> fromKey(@NotNull final String key) {
    final Matcher match = LocationUtil.PATTERN.matcher(key
      .replace("_", ".")
      .replace("/", ":"));
    if (match.matches()) {
      final World world = Bukkit.getWorld(match.group("world"));
      final double x = NumberConversions.toDouble(match.group("x"));
      final double y = NumberConversions.toDouble(match.group("y"));
      final double z = NumberConversions.toDouble(match.group("z"));
      final Float yaw = Optional.ofNullable(match.group("yaw"))
        .map(NumberConversions::toFloat)
        .orElse(0.0f);
      final Float pitch = Optional.ofNullable(match.group("pitch"))
        .map(NumberConversions::toFloat)
        .orElse(0.0f);
      return Optional.of(new Location(world, x, y, z, yaw, pitch));
    }
    return Optional.empty();
  }

  /**
   * converts the given location into a {@link String}.
   *
   * @param location the location to convert.
   *
   * @return a {@link String} instance.
   */
  @NotNull
  public static String toKey(@NotNull final Location location) {
    String s = LocationUtil.validWorld(location).getName() + ':';
    s += String.format(
      Locale.ENGLISH,
      "%.2f,%.2f,%.2f",
      location.getX(), location.getY(), location.getZ());
    if (location.getYaw() != 0.0f || location.getPitch() != 0.0f) {
      s += String.format(
        Locale.ENGLISH,
        ":%.2f:%.2f",
        location.getYaw(), location.getPitch());
    }
    return s.replace(":", "/")
      .replace(".", "_");
  }

  /**
   * calculates the center of the given location.
   *
   * @param location the location to calculate.
   * @param yPlus the y plus to increase Y value of the centered location.
   *
   * @return a {@link Location} instance.
   */
  @NotNull
  private static Location centered(@NotNull final Location location, final double yPlus) {
    return new Location(LocationUtil.validWorld(location),
      location.getX() + 0.5d,
      location.getY() + yPlus,
      location.getZ() + 0.5d,
      location.getYaw(),
      location.getPitch());
  }
}
