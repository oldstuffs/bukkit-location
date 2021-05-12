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
    final var vector = VectorUtil.rotateAroundAxisX(new Vector(10, 10, 10), 180.0d);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
    final var vector = VectorUtil.rotateAroundAxisY(new Vector(10, 10, 10), 180.0d);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
    final var vector = VectorUtil.rotateAroundAxisZ(new Vector(10, 10, 10), 180.0d);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
    final var vector = VectorUtil.rotateVector(new Vector(10, 10, 10), 10.0d, 20.0d, 30.0d);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
    final var vector = VectorUtil.rotateVector(new Vector(10, 10, 10), 180.0f, 90.0f);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
    final var location = new Location(VectorUtilTest.serverMock.getWorld("world"), 100.0d, 200.0d, 300.0d);
    final var vector = VectorUtil.rotateVector(new Vector(10, 10, 10), location);
    final var x = vector.getBlockX();
    final var y = vector.getBlockY();
    final var z = vector.getBlockZ();
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
