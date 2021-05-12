/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
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

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public final class Cuboid {

  /**
   * the minimum location.
   */
  @NotNull
  private final Location minimumLocation;

  /**
   * the maximum location.
   */
  @NotNull
  private final Location maximumLocation;

  /**
   * the common world.
   */
  @NotNull
  private final World world;

  /**
   * the minimum x.
   */
  private final double minX;

  /**
   * the minimum y.
   */
  private final double minY;

  /**
   * the minimum z.
   */
  private final double minZ;

  /**
   * the maximum x.
   */
  private final double maxX;

  /**
   * the maximum y.
   */
  private final double maxY;

  /**
   * the maximum z.
   */
  private final double maxZ;

  /**
   * ctor.
   *
   * @param minimumLocation the minimum location.
   * @param maximumLocation the maximum location.
   */
  public Cuboid(@NotNull final Location minimumLocation, @NotNull final Location maximumLocation) {
    final World minimumWorld = LocationUtil.validWorld(minimumLocation);
    final World maximumWorld = LocationUtil.validWorld(maximumLocation);
    if (!minimumWorld.equals(maximumWorld)) {
      throw new IllegalStateException(minimumWorld + " and " + maximumWorld + " are not equals!");
    }
    this.minimumLocation = minimumLocation;
    this.maximumLocation = maximumLocation;
    this.world = minimumWorld;
    this.minX = Math.min(minimumLocation.getX(), maximumLocation.getX());
    this.minY = Math.min(minimumLocation.getX(), maximumLocation.getX());
    this.minZ = Math.min(minimumLocation.getX(), maximumLocation.getX());
    this.maxX = Math.max(minimumLocation.getX(), maximumLocation.getX());
    this.maxY = Math.max(minimumLocation.getX(), maximumLocation.getX());
    this.maxZ = Math.max(minimumLocation.getX(), maximumLocation.getX());
  }

  /**
   * obtains the minimum location of {@code this}.
   *
   * @return the minimum location.
   */
  @NotNull
  public Location getMinimumLocation() {
    return this.minimumLocation;
  }

  /**
   * obtains the maximum location of {@code this}.
   *
   * @return the maximum location.
   */
  @NotNull
  public Location getMaximumLocation() {
    return this.maximumLocation;
  }

  /**
   * obtains blocks inside the cuboid.
   *
   * @return the block list within the cuboid.
   */
  @NotNull
  public List<Block> blocks() {
    final List<Block> result = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; ++x) {
      for (double y = this.minY; y <= this.maxY; ++y) {
        for (double z = this.minZ; z <= this.maxZ; ++z) {
          result.add(this.world.getBlockAt(new Location(this.world, x, y, z)));
        }
      }
    }
    return result;
  }

  /**
   * obtains locations inside the cuboid.
   *
   * @return the location list within the cuboid.
   */
  @NotNull
  public List<Location> locations() {
    final List<Location> result = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; ++x) {
      for (double y = this.minY; y <= this.maxY; ++y) {
        for (double z = this.minZ; z <= this.maxZ; ++z) {
          result.add(new Location(this.world, x, y, z));
        }
      }
    }
    return result;
  }

  /**
   * sets all blocks inside the cuboid.
   *
   * @param material the material to set.
   */
  public void set(@NotNull final Material material) {
    this.blocks().forEach(block -> block.setType(material));
  }

  /**
   * obtains center bottom of the cuboid.
   *
   * @return center bottom of the cuboid.
   */
  @NotNull
  public Location centerBottom() {
    return new Location(
      this.world,
      this.minX + (this.maxX - this.minX) / 2.0d,
      this.minY,
      this.minZ + (this.maxZ - this.minZ) / 2.0d);
  }

  /**
   * obtains center of the cuboid.
   *
   * @return center of the cuboid.
   */
  @NotNull
  public Location center() {
    return new Location(
      this.world,
      this.minX + (this.maxX - this.minX) / 2.0d,
      this.minY + (this.maxY - this.minY) / 2.0d,
      this.minZ + (this.maxZ - this.minZ) / 2.0d);
  }

  /**
   * checks if the given location is in the cuboid.
   *
   * @param location the location to check.
   *
   * @return {@code true} if the given location is in the cuboid.
   */
  public boolean isIn(@NotNull final Location location) {
    return location.getX() >= this.minX && location.getX() <= this.maxX &&
      this.minY <= location.getY() && location.getY() <= this.maxY &&
      this.minZ <= location.getZ() && location.getZ() <= this.maxZ;
  }

  /**
   * removes all blocks where are in the cuboid.
   */
  public void removeAll() {
    this.blocks().forEach(block ->
      block.setType(Material.AIR));
  }

  /**
   * obtains a random block list from the given limit.
   *
   * @param limit the limit to choose.
   * @param duplicate the duplicate to check if the block is already in the result list.
   *
   * @return a random block list.
   */
  @NotNull
  public List<Block> randomBlocks(final int limit, final boolean duplicate) {
    return RandomUtil.chooseRandoms(this.blocks(), limit, duplicate);
  }

  /**
   * obtains a random location list from the given limit.
   *
   * @param limit the limit to choose.
   * @param duplicate the duplicate to check if the location is already in the result list.
   *
   * @return a random location list.
   */
  @NotNull
  public List<Location> randomLocations(final int limit, final boolean duplicate) {
    return RandomUtil.chooseRandoms(this.locations(), limit, duplicate);
  }
}
