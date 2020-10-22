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
import org.bukkit.entity.Player;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class DirectionUtilTest {

  private static ServerMock serverMock;

  @BeforeAll
  static void setUp() {
    DirectionUtilTest.serverMock = MockBukkit.mock();
    MockBukkit.load(FakePlugin.class);
    DirectionUtilTest.serverMock.addSimpleWorld("world");
    DirectionUtilTest.serverMock.addPlayer("player");
  }

  @AfterAll
  static void tearDown() {
    MockBukkit.unmock();
  }

  @Test
  void directionOf() {
    final Directions directions = DirectionUtil.directionOf(100.0f);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }

  @Test
  void testDirectionOf() {
    final Location location = new Location(DirectionUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d, 100.0f, 50.0f);
    final Directions directions = DirectionUtil.directionOf(location);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }

  @Test
  void testDirectionOf1() {
    final Location location = new Location(DirectionUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d, 100.0f, 50.0f);
    final Player player = DirectionUtilTest.serverMock.getPlayer("player");
    assert player != null;
    player.teleport(location);
    final Directions directions = DirectionUtil.directionOf(player);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }

  @Test
  void doubleDirectionOf() {
    final Directions directions = DirectionUtil.doubleDirectionOf(100.0f);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }

  @Test
  void testDoubleDirectionOf() {
    final Location location = new Location(DirectionUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d, 100.0f, 50.0f);
    final Directions directions = DirectionUtil.doubleDirectionOf(location);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }

  @Test
  void testDoubleDirectionOf1() {
    final Location location = new Location(DirectionUtilTest.serverMock.getWorld("world"), 100.0d, 100.0d, 100.0d, 100.0f, 50.0f);
    final Player player = DirectionUtilTest.serverMock.getPlayer("player");
    assert player != null;
    player.teleport(location);
    final Directions directions = DirectionUtil.doubleDirectionOf(player);
    new Assertion<>(
      "Couldn't get direction correctly!",
      directions,
      new IsEqual<>(Directions.WEST)
    ).affirm();
  }
}