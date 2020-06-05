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
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class DirectionUtil {

    @NotNull
    public Directions directionOf(@NotNull final Player player) {
        return DirectionUtil.directionOf(player.getLocation());
    }

    @NotNull
    public Directions directionOf(@NotNull final Location location) {
        return DirectionUtil.directionOf(location.getYaw());
    }

    @NotNull
    public Directions directionOf(final float yaw) {
        return DirectionUtil.directionOf(
            Arrays.asList(
                Directions.SOUTH,
                Directions.WEST,
                Directions.NORTH,
                Directions.EAST),
            yaw);
    }

    @NotNull
    public Directions doubleDirectionOf(@NotNull final Player player) {
        return DirectionUtil.doubleDirectionOf(player.getLocation());
    }

    @NotNull
    public Directions doubleDirectionOf(@NotNull final Location location) {
        return DirectionUtil.doubleDirectionOf(location.getYaw());
    }

    @NotNull
    public Directions doubleDirectionOf(final float yaw) {
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

    @NotNull
    private Directions directionOf(@NotNull final List<Directions> directions, final float yaw) {
        return directions.get((int) (yaw + 45.0f) % 360 / 90);
    }

    @NotNull
    private Directions doubleDirectionOf(@NotNull final List<Directions> directions, final float yaw) {
        return directions.get((int) (yaw + 22.5f) % 360 / 45);
    }

}
