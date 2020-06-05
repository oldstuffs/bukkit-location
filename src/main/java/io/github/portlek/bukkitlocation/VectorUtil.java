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

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class VectorUtil {

    @NotNull
    public Vector rotateAroundAxisX(@NotNull final Vector vector, final double angle) {
        final double cos = StrictMath.cos(angle);
        final double sin = StrictMath.sin(angle);
        return vector
            .setY(vector.getY() * cos - vector.getZ() * sin)
            .setZ(vector.getY() * sin + vector.getZ() * cos);
    }

    @NotNull
    public Vector rotateAroundAxisY(@NotNull final Vector vector, final double angle) {
        final double cos = StrictMath.cos(angle);
        final double sin = StrictMath.sin(angle);
        return vector
            .setX(vector.getX() * cos + vector.getZ() * sin)
            .setZ(vector.getX() * -sin + vector.getZ() * cos);
    }

    @NotNull
    public Vector rotateAroundAxisZ(@NotNull final Vector vector, final double angle) {
        final double cos = StrictMath.cos(angle);
        final double sin = StrictMath.sin(angle);
        return vector
            .setX(vector.getX() * cos - vector.getY() * sin)
            .setY(vector.getX() * sin + vector.getY() * cos);
    }

    @NotNull
    public Vector rotateVector(@NotNull final Vector vector, final double angleX, final double angleY,
                               final double angleZ) {
        return VectorUtil.rotateAroundAxisZ(VectorUtil.rotateAroundAxisY(VectorUtil.rotateAroundAxisX(vector, angleX), angleY), angleZ);
    }

    @NotNull
    public Vector rotateVector(@NotNull final Vector vector, @NotNull final Location loc) {
        return VectorUtil.rotateVector(vector, loc.getYaw(), loc.getPitch());
    }

    @NotNull
    public Vector rotateVector(@NotNull final Vector vector, final Float yawDegrees, final Float pitchDegrees) {
        final double yaw = Math.toRadians(-1.0d * (yawDegrees.doubleValue() + 90.0d));
        final double pitch = Math.toRadians(-pitchDegrees.doubleValue());
        final double cosYaw = StrictMath.cos(yaw);
        final double cosPitch = StrictMath.cos(pitch);
        final double sinYaw = StrictMath.sin(yaw);
        final double sinPitch = StrictMath.sin(pitch);
        final double initialX = vector.getX() * cosPitch - vector.getY() * sinPitch;
        final double initialY = vector.getY();
        final double initialZ = vector.getZ();
        final double x = initialX * cosPitch - initialY * sinPitch;
        return new Vector(
            initialZ * sinYaw + x * cosYaw,
            initialX * sinPitch + initialY * cosPitch,
            initialZ * cosYaw - x * sinYaw
        );
    }

}
