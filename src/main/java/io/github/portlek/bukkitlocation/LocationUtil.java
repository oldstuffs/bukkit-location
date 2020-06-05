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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class LocationUtil {

    private final Pattern PATTERN = Pattern.compile("(?<world>[^/]+):(?<x>[\\-0-9.]+),(?<y>[\\-0-9.]+),(?<z>[\\-0-9.]+)(:(?<yaw>[\\-0-9.]+):(?<pitch>[\\-0-9.]+))?");

    @NotNull
    public Location centeredOn(@NotNull final Location location) {
        return LocationUtil.centered(location, 0.1d);
    }

    @NotNull
    public Location centeredIn(@NotNull final Location location) {
        return LocationUtil.centered(location, 0.5d);
    }

    @NotNull
    public World validWorld(@NotNull final Location location) {
        return Optional.ofNullable(location.getWorld()).orElseThrow(() ->
            new IllegalStateException("World of the location cannot be null!"));
    }

    @NotNull
    public Optional<Location> fromKey(@NotNull final String key) {
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

    @NotNull
    public String toKey(@NotNull final Location location) {
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

    @NotNull
    private Location centered(@NotNull final Location location, final double defaultvalue) {
        return new Location(LocationUtil.validWorld(location),
            location.getX() + 0.5d,
            location.getY() + defaultvalue,
            location.getZ() + 0.5d,
            location.getYaw(),
            location.getPitch());
    }

}
