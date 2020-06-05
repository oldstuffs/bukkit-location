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
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class CuboidTest {

    private static ServerMock serverMock;

    @BeforeAll
    static void setUp() {
        CuboidTest.serverMock = MockBukkit.mock();
        MockBukkit.load(FakePlugin.class);
        CuboidTest.serverMock.addSimpleWorld("world");
        CuboidTest.serverMock.addPlayer("player");
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void blocks() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 1.0d, 1.0d, 1.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        new Assertion<>(
            "Couldn't calculate block size correctly!",
            cuboid.blocks().size(),
            new IsEqual<>(8)
        ).affirm();
    }

    @Test
    void set() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 1.0d, 1.0d, 1.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        cuboid.set(Material.AIR);
        new Assertion<>(
            "Couldn't calculate block size correctly!",
            cuboid.blocks().stream().anyMatch(block ->
                block.getType() != Material.AIR),
            new IsNot<>(new IsTrue())
        ).affirm();
    }

    @Test
    void centerBottom() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        new Assertion<>(
            "Couldn't parse the center bottom of the cuboid!",
            cuboid.centerBottom(),
            new IsEqual<>(new Location(world, 1.0d, 0.0d, 1.0d))
        ).affirm();
    }

    @Test
    void center() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        new Assertion<>(
            "Couldn't parse the center of the cuboid!",
            cuboid.center(),
            new IsEqual<>(new Location(world, 1.0d, 1.0d, 1.0d))
        ).affirm();
    }

    @Test
    void isIn() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        new Assertion<>(
            "The location is not in the cuboid!",
            cuboid.isIn(new Location(world, 1.0d, 1.0d, 1.0d)),
            new IsTrue()
        ).affirm();
    }

    @Test
    void removeAll() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        cuboid.removeAll();
        new Assertion<>(
            "Couldn't remove block!",
            cuboid.blocks().stream().anyMatch(block ->
                block.getType() != Material.AIR),
            new IsNot<>(new IsTrue())
        ).affirm();
    }

    @Test
    void randomBlocks() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        final List<Block> blocks = cuboid.randomBlocks(2, false);
        new Assertion<>(
            "Couldn't get blocks correctly!",
            blocks.size(),
            new IsEqual<>(2)
        ).affirm();
    }

    @Test
    void randomLocations() {
        final World world = CuboidTest.serverMock.getWorld("world");
        final Location min = new Location(world, 0.0d, 0.0d, 0.0d);
        final Location max = new Location(world, 2.0d, 2.0d, 2.0d);
        final Cuboid cuboid = new Cuboid(min, max);
        final List<Location> locations = cuboid.randomLocations(2, false);
        new Assertion<>(
            "Couldn't get locations correctly!",
            locations.size(),
            new IsEqual<>(2)
        ).affirm();
    }

}
