package io.github.portlek.bukkitlocation;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import java.util.Optional;
import org.bukkit.Location;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

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
  void centeredIn() {
    final var location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
    final var expected = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.5d, 100.5d, 100.5d);
    final var centeredIn = LocationUtil.centeredIn(location);
    new Assertion<>(
      "Couldn't calculate the centered in of the location!",
      centeredIn,
      new IsEqual<>(expected)
    ).affirm();
  }

  @Test
  void centeredOn() {
    final var location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
    final var expected = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.5d, 100.1d, 100.5d);
    final var centeredOn = LocationUtil.centeredOn(location);
    new Assertion<>(
      "Couldn't calculate the centered on of the location!",
      centeredOn,
      new IsEqual<>(expected)
    ).affirm();
  }

  @Test
  void doesNotMatch() {
    final var location = LocationUtil.fromKey("test");
    new Assertion<>(
      "Something went wrong",
      location.isEmpty(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Something went wrong",
      location.isPresent(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void fromKey() {
    final var expected = Optional.of(new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d));
    final var key = "world/100_0,100_0,100_0";
    final var optional = LocationUtil.fromKey(key);
    new Assertion<>(
      "Couldn't parse the key into the location!",
      optional,
      new IsEqual<>(expected)
    ).affirm();
  }

  @Test
  void toKey() {
    final var location = new Location(LocationUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d);
    final var expected = "world/100_00,100_00,100_00";
    new Assertion<>(
      "Couldn't parse the key into the location!",
      LocationUtil.toKey(location),
      new IsEqual<>(expected)
    ).affirm();
  }

  @Test
  void validWorld() {
    final var expected = LocationUtilTest.serverMock.getWorld("world");
    final var location = new Location(expected, 100.0d, 100.0d, 100.0d);
    new Assertion<>(
      "The location's world is not valid!",
      LocationUtil.validWorld(location),
      new IsEqual<>(expected)
    ).affirm();
  }
}
