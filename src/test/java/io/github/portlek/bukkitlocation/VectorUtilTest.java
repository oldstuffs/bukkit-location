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
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class VectorUtilTest {

    private static ServerMock serverMock;

    @BeforeAll
    static void setUp() {
        VectorUtilTest.serverMock = MockBukkit.mock();
        MockBukkit.load(FakePlugin.class);
        VectorUtilTest.serverMock.addSimpleWorld("world");
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void rotateAroundAxisX() {
        final Vector vector = VectorUtil.rotateAroundAxisX(new Vector(10, 10, 10), 180.0d);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate around axis X!",
            x,
            new IsEqual<>(10)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis X!",
            y,
            new IsEqual<>(2)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis X!",
            z,
            new IsEqual<>(-8)
        ).affirm();
    }

    @Test
    void rotateAroundAxisY() {
        final Vector vector = VectorUtil.rotateAroundAxisY(new Vector(10, 10, 10), 180.0d);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate around axis Y!",
            x,
            new IsEqual<>(-14)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis Y!",
            y,
            new IsEqual<>(10)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis Y!",
            z,
            new IsEqual<>(-18)
        ).affirm();
    }

    @Test
    void rotateAroundAxisZ() {
        final Vector vector = VectorUtil.rotateAroundAxisZ(new Vector(10, 10, 10), 180.0d);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate around axis Z!",
            x,
            new IsEqual<>(2)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis Z!",
            y,
            new IsEqual<>(-8)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate around axis Z!",
            z,
            new IsEqual<>(10)
        ).affirm();
    }

    @Test
    void rotateVector() {
        final Vector vector = VectorUtil.rotateVector(new Vector(10, 10, 10), 10.0d, 20.0d, 30.0d);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate vector!",
            x,
            new IsEqual<>(-4)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            y,
            new IsEqual<>(2)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            z,
            new IsEqual<>(-1)
        ).affirm();
    }

    @Test
    void testRotateVector() {
        final Vector vector = VectorUtil.rotateVector(new Vector(10, 10, 10), 180.0f, 90.0f);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate vector!",
            x,
            new IsEqual<>(9)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            y,
            new IsEqual<>(-10)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            z,
            new IsEqual<>(-11)
        ).affirm();
    }

    @Test
    void testRotateVector1() {
        final Location location = new Location(VectorUtilTest.serverMock.getWorld("world"), 100.0d, 200.0d, 300.0d);
        final Vector vector = VectorUtil.rotateVector(new Vector(10, 10, 10), location);
        final int x = vector.getBlockX();
        final int y = vector.getBlockY();
        final int z = vector.getBlockZ();
        new Assertion<>(
            "Couldn't rotate vector!",
            x,
            new IsEqual<>(-10)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            y,
            new IsEqual<>(10)
        ).affirm();
        new Assertion<>(
            "Couldn't rotate vector!",
            z,
            new IsEqual<>(10)
        ).affirm();
    }

}
