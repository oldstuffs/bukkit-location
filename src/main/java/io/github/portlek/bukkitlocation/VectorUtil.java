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

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link Vector}.
 */
public final class VectorUtil {

  /**
   * ctor.
   */
  private VectorUtil() {
  }

  /**
   * rotates the given vector around x axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisX(@NotNull final Vector vector, final double angle) {
    final var cos = StrictMath.cos(angle);
    final var sin = StrictMath.sin(angle);
    return vector
      .setY(vector.getY() * cos - vector.getZ() * sin)
      .setZ(vector.getY() * sin + vector.getZ() * cos);
  }

  /**
   * rotates the given vector around y axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisY(@NotNull final Vector vector, final double angle) {
    final var cos = StrictMath.cos(angle);
    final var sin = StrictMath.sin(angle);
    return vector
      .setX(vector.getX() * cos + vector.getZ() * sin)
      .setZ(vector.getX() * -sin + vector.getZ() * cos);
  }

  /**
   * rotates the given vector around z axis for the given angle.
   *
   * @param vector the vector to rotate.
   * @param angle the angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateAroundAxisZ(@NotNull final Vector vector, final double angle) {
    final var cos = StrictMath.cos(angle);
    final var sin = StrictMath.sin(angle);
    return vector
      .setX(vector.getX() * cos - vector.getY() * sin)
      .setY(vector.getX() * sin + vector.getY() * cos);
  }

  /**
   * rotates the given vector for the given x, y and z angle.
   *
   * @param vector the vector to rotate.
   * @param angleX the x angle to rotate.
   * @param angleY the y angle to rotate.
   * @param angleZ the z angle to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, final double angleX, final double angleY,
                                    final double angleZ) {
    return VectorUtil.rotateAroundAxisZ(
      VectorUtil.rotateAroundAxisY(
        VectorUtil.rotateAroundAxisX(vector, angleX),
        angleY),
      angleZ);
  }

  /**
   * rotates the given vector around the given location.
   *
   * @param vector the vector to rotate.
   * @param location the location to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, @NotNull final Location location) {
    return VectorUtil.rotateVector(vector, location.getYaw(), location.getPitch());
  }

  /**
   * rotates the given vector around the given yaw and pitch degrees.
   *
   * @param vector the vector to rotate.
   * @param yawDegrees the yaw degree to rotate.
   * @param pitchDegrees the pitch degree to rotate.
   *
   * @return a new rotated vector.
   */
  @NotNull
  public static Vector rotateVector(@NotNull final Vector vector, final double yawDegrees, final double pitchDegrees) {
    final var yaw = Math.toRadians(-1.0d * (yawDegrees + 90.0d));
    final var pitch = Math.toRadians(-pitchDegrees);
    final var cosYaw = StrictMath.cos(yaw);
    final var cosPitch = StrictMath.cos(pitch);
    final var sinYaw = StrictMath.sin(yaw);
    final var sinPitch = StrictMath.sin(pitch);
    final var initialX = vector.getX() * cosPitch - vector.getY() * sinPitch;
    final var initialY = vector.getY();
    final var initialZ = vector.getZ();
    final var x = initialX * cosPitch - initialY * sinPitch;
    return new Vector(
      initialZ * sinYaw + x * cosYaw,
      initialX * sinPitch + initialY * cosPitch,
      initialZ * cosYaw - x * sinYaw);
  }
}
