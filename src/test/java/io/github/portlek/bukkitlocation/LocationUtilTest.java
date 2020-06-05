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

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.World;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class LocationUtilTest {

    private static ServerMock serverMock;

    @BeforeAll
    static void setUp() {
        LocationUtilTest.serverMock = MockBukkit.mock();
        MockBukkit.load(FakePlugin.class);
        LocationUtilTest.serverMock.addSimpleWorld("world");
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void centeredOn() {
        final Location location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
        final Location expected = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.5d, 100.1d, 100.5d);
        final Location centeredOn = LocationUtil.centeredOn(location);
        new Assertion<>(
            "Couldn't calculate the centered on of the location!",
            centeredOn,
            new IsEqual<>(expected)
        ).affirm();
    }

    @Test
    void centeredIn() {
        final Location location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
        final Location expected = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.5d, 100.5d, 100.5d);
        final Location centeredIn = LocationUtil.centeredIn(location);
        new Assertion<>(
            "Couldn't calculate the centered in of the location!",
            centeredIn,
            new IsEqual<>(expected)
        ).affirm();
    }

    @Test
    void validWorld() {
        final World expected = LocationUtilTest.serverMock.getWorld("world");
        final Location location = new Location(expected, 100.0d, 100.0d, 100.0d);
        new Assertion<>(
            "The location's world is not valid!",
            LocationUtil.validWorld(location),
            new IsEqual<>(expected)
        ).affirm();
    }

    @Test
    void fromKey() {
        final Optional<Location> expected = Optional.of(new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d));
        final String key = "world/100_0,100_0,100_0";
        final Optional<Location> optional = LocationUtil.fromKey(key);
        new Assertion<>(
            "Couldn't parse the key into the location!",
            optional,
            new IsEqual<>(expected)
        ).affirm();
    }

    @Test
    void toKey() {
        final Location location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
        final String expected = "world/100_00,100_00,100_00";
        new Assertion<>(
            "Couldn't parse the key into the location!",
            LocationUtil.toKey(location),
            new IsEqual<>(expected)
        ).affirm();
    }

}
