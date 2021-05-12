package io.github.portlek.bukkitlocation;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.Location;
import org.bukkit.Material;
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
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 1.0d, 1.0d, 1.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "Couldn't calculate block size correctly!",
      cuboid.blocks().size(),
      new IsEqual<>(8)
    ).affirm();
  }

  @Test
  void center() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "Couldn't parse the center of the cuboid!",
      cuboid.center(),
      new IsEqual<>(new Location(world, 1.0d, 1.0d, 1.0d))
    ).affirm();
  }

  @Test
  void centerBottom() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "Couldn't parse the center bottom of the cuboid!",
      cuboid.centerBottom(),
      new IsEqual<>(new Location(world, 1.0d, 0.0d, 1.0d))
    ).affirm();
  }

  @Test
  void isIn() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "The location is not in the cuboid!",
      cuboid.isIn(new Location(world, 1.0d, 1.0d, 1.0d)),
      new IsTrue()
    ).affirm();
  }

  @Test
  void locations() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 1.0d, 1.0d, 1.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "Couldn't calculate block size correctly!",
      cuboid.locations().size(),
      new IsEqual<>(8)
    ).affirm();
  }

  @Test
  void maxMin() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 1.0d, 1.0d, 1.0d);
    final var cuboid = new Cuboid(min, max);
    new Assertion<>(
      "Couldn't set maximum location",
      cuboid.getMaximumLocation(),
      new IsEqual<>(max)
    ).affirm();
    new Assertion<>(
      "Couldn't set minimum location",
      cuboid.getMinimumLocation(),
      new IsEqual<>(min)
    ).affirm();
  }

  @Test
  void randomBlocks() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    final var blocks = cuboid.randomBlocks(2, false);
    new Assertion<>(
      "Couldn't get blocks correctly!",
      blocks.size(),
      new IsEqual<>(2)
    ).affirm();
  }

  @Test
  void randomLocations() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    final var locations = cuboid.randomLocations(2, false);
    new Assertion<>(
      "Couldn't get locations correctly!",
      locations.size(),
      new IsEqual<>(2)
    ).affirm();
  }

  @Test
  void removeAll() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 2.0d, 2.0d, 2.0d);
    final var cuboid = new Cuboid(min, max);
    cuboid.removeAll();
    new Assertion<>(
      "Couldn't remove block!",
      cuboid.blocks().stream().anyMatch(block ->
        block.getType() != Material.AIR),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void set() {
    final var world = CuboidTest.serverMock.getWorld("world");
    final var min = new Location(world, 0.0d, 0.0d, 0.0d);
    final var max = new Location(world, 1.0d, 1.0d, 1.0d);
    final var cuboid = new Cuboid(min, max);
    cuboid.set(Material.AIR);
    new Assertion<>(
      "Couldn't calculate block size correctly!",
      cuboid.blocks().stream().anyMatch(block ->
        block.getType() != Material.AIR),
      new IsNot<>(new IsTrue())
    ).affirm();
  }
}
